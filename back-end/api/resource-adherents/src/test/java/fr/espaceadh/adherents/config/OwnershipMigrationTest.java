package fr.espaceadh.adherents.config;

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.service.AdherentService;
import fr.espaceadh.security.LegacyJwtAuthenticationConverter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig(OwnershipMigrationTest.Config.class)
class OwnershipMigrationTest {
    @Configuration
    @Import(MethodSecurityConfig.class)
    static class Config {
        @Bean AdherentService adherentService() { return mock(AdherentService.class); }
        @Bean ProtectedData protectedData() { return new ProtectedData(); }
    }

    public static class ProtectedData {
        @PreAuthorize("hasAuthority('SCOPE_ress-adherent-read') and (isDansGroupe('CONSEIL') or isProprietraireDonnee(#idadh))")
        public String read(Long idadh) { return "allowed"; }
    }

    @Autowired ProtectedData data;
    @Autowired AdherentService service;

    @BeforeEach
    void member() {
        AdherentDto member = new AdherentDto();
        member.setEmail("member@example.org");
        when(service.recupererAdherent(37L)).thenReturn(member);
    }

    @AfterEach
    void clear() {
        SecurityContextHolder.clearContext();
        reset(service);
    }

    @Test
    void jwtOwnerCanReadTheirData() {
        authenticate("member@example.org", "ress-adherent-read", "ADHERENT");
        assertThat(data.read(37L)).isEqualTo("allowed");
    }

    @Test
    void foreignIdentityOrMissingScopeCannotRead() {
        authenticate("other@example.org", "ress-adherent-read", "ADHERENT");
        assertThatThrownBy(() -> data.read(37L)).isInstanceOf(AccessDeniedException.class);
        authenticate("member@example.org", "ress-adherent-write", "ADHERENT");
        assertThatThrownBy(() -> data.read(37L)).isInstanceOf(AccessDeniedException.class);
    }

    @Test
    void conseilKeepsItsDelegatedAccess() {
        authenticate("conseil@example.org", "ress-adherent-read", "CONSEIL");
        assertThat(data.read(37L)).isEqualTo("allowed");
    }

    private void authenticate(String username, String scope, String role) {
        Jwt jwt = Jwt.withTokenValue("test").header("alg", "RS256").subject(username)
                .claim("scope", scope).claim("authorities", List.of(role)).build();
        SecurityContextHolder.getContext().setAuthentication(new LegacyJwtAuthenticationConverter().convert(jwt));
    }
}
