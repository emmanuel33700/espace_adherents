package fr.espaceadh.security;

import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.client.endpoint.OAuth2ClientCredentialsGrantRequest;
import org.springframework.security.oauth2.client.endpoint.RestClientClientCredentialsTokenResponseClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

/** Client credentials for calls between the existing APIs. */
public final class ClientCredentialsTokens {
    private ClientCredentialsTokens() { }

    public static OAuth2AccessToken obtain(Environment env) {
        ClientRegistration registration = ClientRegistration.withRegistrationId("ress-adherents")
                .clientId(env.getRequiredProperty("oauth2.ress-adh.clientid"))
                .clientSecret(env.getRequiredProperty("oauth2.ress-adh.clientsecret"))
                .tokenUri(env.getRequiredProperty("oauth2.url"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();
        return new RestClientClientCredentialsTokenResponseClient()
                .getTokenResponse(new OAuth2ClientCredentialsGrantRequest(registration)).getAccessToken();
    }
}
