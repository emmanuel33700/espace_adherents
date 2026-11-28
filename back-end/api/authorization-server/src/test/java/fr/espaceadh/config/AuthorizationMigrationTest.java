package fr.espaceadh.config;

import fr.espaceadh.dao.AuthorizationDao;
import fr.espaceadh.dto.Authorization;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = AuthorizationServerApplication.class, properties = {
        "spring.profiles.active=migration-test", "oauth2.issuer=http://localhost/authorization-server",
        "oauth2.desktop.clientid=desktop", "oauth2.desktop.clientsecret=desktop-secret",
        "oauth2.desktop.accesstoken.validity=3600", "oauth2.desktop.refreshtoken.validity=36000",
        "oauth2.ress-adh.clientid=resource", "oauth2.ress-adh.clientsecret=resource-secret",
        "oauth2.ress-adh.accesstoken.validity=3600", "oauth2.ress-adh.refreshtoken.validity=36000",
        "jks.keystore=../mytest.jks", "jks.alias=mytest", "jks.keypass=mypass"
})
@AutoConfigureMockMvc
class AuthorizationMigrationTest {
    @Autowired MockMvc mvc;
    @Autowired JwtDecoder decoder;
    @Autowired ObjectMapper json;
    @Autowired org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder;
    @MockitoBean DataSource dataSource;
    @MockitoBean UserDetailsService users;
    @MockitoBean AuthorizationDao dao;

    @BeforeEach
    void user() {
        when(users.loadUserByUsername("member@example.org")).thenReturn(User.withUsername("member@example.org")
                .password(encoder.encode("member-password")).authorities("ADMIN", "CONSEIL").build());
        Authorization member = new Authorization();
        member.setIdAdherent("37");
        when(dao.getAuthorisation("member@example.org")).thenReturn(member);
    }

    @Test
    void passwordGrantIssuesSignedJwtAndRefreshesIt() throws Exception {
        var response = mvc.perform(post("/oauth/token").with(httpBasic("desktop", "desktop-secret"))
                        .param("grant_type", "password").param("username", "member@example.org")
                        .param("password", "member-password").param("scope", "ress-adherent-read"))
                .andExpect(status().isOk()).andExpect(jsonPath("idAdherent").value("37"))
                .andExpect(jsonPath("authorities").value("ADMIN CONSEIL"))
                .andExpect(jsonPath("refresh_token").isNotEmpty())
                .andReturn().getResponse();
        var body = json.readTree(response.getContentAsString());
        var jwt = decoder.decode(body.get("access_token").asText());
        assertThat(jwt.getClaimAsString("user_name")).isEqualTo("member@example.org");
        assertThat(jwt.getClaimAsString("idAdherent")).isEqualTo("37");
        assertThat(jwt.getClaimAsString("authorities")).contains("ADMIN", "CONSEIL");
        assertThat(jwt.getClaimAsStringList("scope")).containsExactly("ress-adherent-read");
        mvc.perform(post("/oauth/token").with(httpBasic("desktop", "desktop-secret"))
                        .param("grant_type", "refresh_token").param("refresh_token", body.get("refresh_token").asText()))
                .andExpect(status().isOk()).andExpect(jsonPath("access_token").isNotEmpty())
                .andExpect(jsonPath("idAdherent").value("37"));
    }

    @Test
    void clientCredentialsGrantKeepsItsScopes() throws Exception {
        var response = mvc.perform(post("/oauth/token").with(httpBasic("resource", "resource-secret"))
                        .param("grant_type", "client_credentials"))
                .andExpect(status().isOk()).andReturn().getResponse();
        var jwt = decoder.decode(json.readTree(response.getContentAsString()).get("access_token").asText());
        assertThat(jwt.getSubject()).isEqualTo("resource");
        assertThat(jwt.getClaimAsStringList("scope")).contains("ress-autorization-admin");
        assertThat(jwt.getClaims()).doesNotContainKey("idAdherent");
    }

    @Test
    void rejectsWrongPasswordUnauthorizedClientAndScopes() throws Exception {
        mvc.perform(post("/oauth/token").with(httpBasic("desktop", "wrong-secret"))
                        .param("grant_type", "password").param("username", "member@example.org").param("password", "member-password"))
                .andExpect(status().isUnauthorized()).andExpect(jsonPath("error").value("invalid_client"));
        mvc.perform(post("/oauth/token").with(httpBasic("desktop", "desktop-secret"))
                        .param("grant_type", "password").param("username", "member@example.org").param("password", "wrong"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("error").value("invalid_grant"));
        mvc.perform(post("/oauth/token").with(httpBasic("resource", "resource-secret"))
                        .param("grant_type", "password").param("username", "member@example.org").param("password", "member-password"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("error").value("unauthorized_client"));
        mvc.perform(post("/oauth/token").with(httpBasic("desktop", "desktop-secret"))
                        .param("grant_type", "password").param("username", "member@example.org")
                        .param("password", "member-password").param("scope", "ress-autorization-admin"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("error").value("invalid_scope"));
    }

    @Test
    void publishesOnlyThePublicSigningKey() throws Exception {
        mvc.perform(get("/oauth/token_key")).andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(org.hamcrest.Matchers.containsString("BEGIN PUBLIC KEY")));
        mvc.perform(get("/oauth/jwks")).andExpect(status().isOk())
                .andExpect(jsonPath("$.keys[0].n").exists()).andExpect(jsonPath("$.keys[0].d").doesNotExist());
    }

    @Test
    void historicalDeleteRevokesTheCurrentAuthorization() throws Exception {
        var response = mvc.perform(post("/oauth/token").with(httpBasic("desktop", "desktop-secret"))
                        .param("grant_type", "password").param("username", "member@example.org").param("password", "member-password"))
                .andExpect(status().isOk()).andReturn().getResponse();
        var body = json.readTree(response.getContentAsString());
        mvc.perform(delete("/oauth/token").header("Authorization", "Bearer " + body.get("access_token").asText()))
                .andExpect(status().isOk());
        mvc.perform(post("/oauth/token").with(httpBasic("desktop", "desktop-secret"))
                        .param("grant_type", "refresh_token").param("refresh_token", body.get("refresh_token").asText()))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("error").value("invalid_grant"));
    }

    @Test
    void revocationInvalidatesRefreshToken() throws Exception {
        var response = mvc.perform(post("/oauth/token").with(httpBasic("desktop", "desktop-secret"))
                        .param("grant_type", "password").param("username", "member@example.org").param("password", "member-password"))
                .andExpect(status().isOk()).andReturn().getResponse();
        String refresh = json.readTree(response.getContentAsString()).get("refresh_token").asText();
        mvc.perform(post("/oauth/revoke").with(httpBasic("desktop", "desktop-secret"))
                        .param("token", refresh).param("token_type_hint", "refresh_token"))
                .andExpect(status().isOk());
        mvc.perform(post("/oauth/token").with(httpBasic("desktop", "desktop-secret"))
                        .param("grant_type", "refresh_token").param("refresh_token", refresh))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("error").value("invalid_grant"));
    }
}
