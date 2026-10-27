package fr.espaceadh.config;

import java.security.Principal;
import java.util.Map;
import java.util.Set;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

public class PasswordGrantAuthenticationProvider implements AuthenticationProvider {
    private final AuthenticationManager users;
    private final OAuth2AuthorizationService authorizations;
    private final OAuth2TokenGenerator<?> tokens;

    public PasswordGrantAuthenticationProvider(AuthenticationManager users,
            OAuth2AuthorizationService authorizations, OAuth2TokenGenerator<?> tokens) {
        this.users = users;
        this.authorizations = authorizations;
        this.tokens = tokens;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        PasswordGrantAuthenticationToken grant = (PasswordGrantAuthenticationToken) authentication;
        try {
            if (!(grant.getPrincipal() instanceof OAuth2ClientAuthenticationToken client) || !client.isAuthenticated()) {
                throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
            }
            var registration = client.getRegisteredClient();
            if (registration == null || !registration.getAuthorizationGrantTypes().contains(grant.getGrantType())) {
                throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
            }
            Set<String> scopes = grant.getScopes().isEmpty() ? registration.getScopes() : grant.getScopes();
            if (!registration.getScopes().containsAll(scopes)) {
                throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_SCOPE);
            }
            Authentication user;
            try {
                user = users.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(grant.getUsername(), grant.getPassword()));
            } catch (AuthenticationException ex) {
                throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_GRANT);
            }
            user = UsernamePasswordAuthenticationToken.authenticated(user.getName(), null, user.getAuthorities());
            var context = DefaultOAuth2TokenContext.builder().registeredClient(registration).principal(user)
                    .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                    .authorizationGrantType(grant.getGrantType()).authorizationGrant(grant).authorizedScopes(scopes);
            OAuth2Token generated = tokens.generate(context.tokenType(OAuth2TokenType.ACCESS_TOKEN).build());
            if (generated == null) throw new OAuth2AuthenticationException(OAuth2ErrorCodes.SERVER_ERROR);
            OAuth2AccessToken access = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                    generated.getTokenValue(), generated.getIssuedAt(), generated.getExpiresAt(), scopes);
            var authorization = OAuth2Authorization.withRegisteredClient(registration).principalName(user.getName())
                    .authorizationGrantType(grant.getGrantType()).authorizedScopes(scopes)
                    .attribute(Principal.class.getName(), user);
            authorization.token(access, metadata -> {
                if (generated instanceof ClaimAccessor claims) {
                    metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, claims.getClaims());
                }
            });
            OAuth2RefreshToken refresh = null;
            if (registration.getAuthorizationGrantTypes().contains(org.springframework.security.oauth2.core.AuthorizationGrantType.REFRESH_TOKEN)) {
                OAuth2Token generatedRefresh = tokens.generate(context.tokenType(OAuth2TokenType.REFRESH_TOKEN).build());
                if (!(generatedRefresh instanceof OAuth2RefreshToken)) {
                    throw new OAuth2AuthenticationException(OAuth2ErrorCodes.SERVER_ERROR);
                }
                refresh = (OAuth2RefreshToken) generatedRefresh;
                authorization.refreshToken(refresh);
            }
            authorizations.save(authorization.build());
            return new OAuth2AccessTokenAuthenticationToken(registration, client, access, refresh, Map.of());
        } finally {
            grant.eraseCredentials();
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PasswordGrantAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
