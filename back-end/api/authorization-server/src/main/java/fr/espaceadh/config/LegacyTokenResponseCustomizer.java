package fr.espaceadh.config;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;

/** Keep the response fields read by the existing frontend, including after a refresh. */
public final class LegacyTokenResponseCustomizer implements Consumer<OAuth2AccessTokenAuthenticationContext> {
    private final OAuth2AuthorizationService authorizations;

    public LegacyTokenResponseCustomizer(OAuth2AuthorizationService authorizations) {
        this.authorizations = authorizations;
    }

    @Override
    public void accept(OAuth2AccessTokenAuthenticationContext context) {
        OAuth2AccessTokenAuthenticationToken result = context.getAuthentication();
        var authorization = authorizations.findByToken(result.getAccessToken().getTokenValue(), OAuth2TokenType.ACCESS_TOKEN);
        var additional = new HashMap<String, Object>(result.getAdditionalParameters());
        if (authorization != null && authorization.getAccessToken() != null) {
            var claims = authorization.getAccessToken().getClaims();
            if (claims != null) {
                for (String name : List.of("idAdherent", "authorities", "organization", "user_name")) {
                    if (claims.containsKey(name)) additional.put(name, claims.get(name));
                }
            }
        }
        context.getAccessTokenResponse().additionalParameters(additional);
    }
}
