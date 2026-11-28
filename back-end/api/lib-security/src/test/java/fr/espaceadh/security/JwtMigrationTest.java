package fr.espaceadh.security;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jwt.Jwt;
import static org.assertj.core.api.Assertions.assertThat;

class JwtMigrationTest {
    private final LegacyJwtAuthenticationConverter converter = new LegacyJwtAuthenticationConverter();

    @Test
    void readsHistoricalIdentityRolesAndScopes() {
        Jwt jwt = Jwt.withTokenValue("test").header("alg", "RS256").subject("client")
                .claim("user_name", "member@example.org").claim("idAdherent", "37")
                .claim("scope", List.of("ress-autorization-read", "ress-adherent-write"))
                .claim("authorities", "ADMIN CONSEIL ").build();
        var authentication = converter.convert(jwt);
        assertThat(authentication.getName()).isEqualTo("member@example.org");
        assertThat(authentication.getAuthorities()).extracting("authority")
                .containsExactlyInAnyOrder("ADMIN", "CONSEIL", "SCOPE_ress-autorization-read", "SCOPE_ress-adherent-write");
        assertThat(authentication.getDetails()).isEqualTo(jwt.getClaims());
    }

    @Test
    void readsNewRoleArraysAndClientCredentialsSubject() {
        Jwt jwt = Jwt.withTokenValue("test").header("alg", "RS256").subject("ress-adherents")
                .claim("scope", "ress-autorization-admin").claim("authorities", List.of("ADMIN")).build();
        var authentication = converter.convert(jwt);
        assertThat(authentication.getName()).isEqualTo("ress-adherents");
        assertThat(authentication.getAuthorities()).extracting("authority")
                .containsExactlyInAnyOrder("ADMIN", "SCOPE_ress-autorization-admin");
    }
}
