package fr.espaceadh.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RevokeTokenEndpoint {
    private final OAuth2AuthorizationService authorizations;

    public RevokeTokenEndpoint(OAuth2AuthorizationService authorizations) {
        this.authorizations = authorizations;
    }

    @DeleteMapping("/oauth/token")
    public void revokeToken(@AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) return;
        var authorization = authorizations.findByToken(jwt.getTokenValue(), OAuth2TokenType.ACCESS_TOKEN);
        if (authorization != null) authorizations.remove(authorization);
    }
}
