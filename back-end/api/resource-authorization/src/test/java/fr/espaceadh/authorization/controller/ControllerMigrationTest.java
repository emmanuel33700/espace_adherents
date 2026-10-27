package fr.espaceadh.authorization.controller;

import fr.espaceadh.authorization.config.CustomAccessTokenConverter;
import fr.espaceadh.authorization.config.MethodSecurityConfig;
import fr.espaceadh.authorization.config.OAuth2ResourceServerConfigJwt;
import fr.espaceadh.authorization.service.AuthentificationService;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ControllerMigrationTest.Application.class, properties = "spring.profiles.active=migration-test")
@AutoConfigureMockMvc
class ControllerMigrationTest {
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @Import({AuthentificationApiController.class, MethodSecurityConfig.class,
            OAuth2ResourceServerConfigJwt.class, CustomAccessTokenConverter.class})
    static class Application { }

    @Autowired MockMvc mvc;
    @MockitoBean AuthentificationService service;
    @MockitoBean JwtDecoder decoder;

    @BeforeEach
    void activate() {
        when(service.activerAuthentification(37)).thenReturn(true);
    }

    @Test
    void acceptsAdminGroupOrAdminScope() throws Exception {
        when(decoder.decode("admin")).thenReturn(jwt("admin", "ress-autorization-read", List.of("ADMIN")));
        when(decoder.decode("client")).thenReturn(jwt("client", "ress-autorization-admin", List.of()));
        for (String token : List.of("admin", "client")) {
            mvc.perform(put("/authentification/37/activation").header("Authorization", "Bearer " + token)
                            .contentType("application/json").content("{\"statutActivation\":true}"))
                    .andExpect(status().isOk());
        }
        verify(service, times(2)).activerAuthentification(37);
    }

    @Test
    void refusesInsufficientPermissionsAndAnonymousAccess() throws Exception {
        when(decoder.decode("member")).thenReturn(jwt("member", "ress-autorization-read", List.of("ADHERENT")));
        mvc.perform(put("/authentification/37/activation").header("Authorization", "Bearer member")
                        .contentType("application/json").content("{\"statutActivation\":true}"))
                .andExpect(status().isForbidden());
        mvc.perform(put("/authentification/37/activation")
                        .contentType("application/json").content("{\"statutActivation\":true}"))
                .andExpect(status().isUnauthorized());
        verifyNoInteractions(service);
    }

    @Test
    void openApiDescriptionLoadsWithJakartaModels() throws Exception {
        mvc.perform(get("/v3/api-docs")).andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/authentification/{idadh}/activation']").exists());
    }

    private Jwt jwt(String value, String scope, List<String> roles) {
        return Jwt.withTokenValue(value).header("alg", "RS256").subject("member@example.org")
                .issuedAt(Instant.now()).expiresAt(Instant.now().plusSeconds(600))
                .claim("scope", scope).claim("authorities", roles).build();
    }
}
