package fr.espaceadh.config;

import fr.espaceadh.dao.AuthorizationDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

public class CustomTokenEnhancer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    private final AuthorizationDao authorizationDao;

    public CustomTokenEnhancer(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    @Override
    public void customize(JwtEncodingContext context) {
        if (!OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) return;
        context.getClaims().claim("organization", "JALLE ASTRO");
        if (!AuthorizationGrantType.CLIENT_CREDENTIALS.equals(context.getAuthorizationGrantType())) {
            String username = context.getPrincipal().getName();
            context.getClaims().claim("user_name", username)
                    .claim("idAdherent", authorizationDao.getAuthorisation(username).getIdAdherent())
                    .claim("authorities", context.getPrincipal().getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(java.util.stream.Collectors.joining(" ")));
        }
    }
}
