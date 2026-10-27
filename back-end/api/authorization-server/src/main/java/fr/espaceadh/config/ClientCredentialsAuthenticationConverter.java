package fr.espaceadh.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientCredentialsAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2ClientCredentialsAuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationConverter;

/** Preserve the historical default scopes for existing service clients. */
public class ClientCredentialsAuthenticationConverter implements AuthenticationConverter {
    private final OAuth2ClientCredentialsAuthenticationConverter delegate = new OAuth2ClientCredentialsAuthenticationConverter();

    @Override
    public Authentication convert(HttpServletRequest request) {
        var grant = (OAuth2ClientCredentialsAuthenticationToken) delegate.convert(request);
        if (grant != null && request.getParameter("scope") == null
                && grant.getPrincipal() instanceof OAuth2ClientAuthenticationToken client
                && client.isAuthenticated() && client.getRegisteredClient() != null) {
            return new OAuth2ClientCredentialsAuthenticationToken(client,
                    client.getRegisteredClient().getScopes(), grant.getAdditionalParameters());
        }
        return grant;
    }
}
