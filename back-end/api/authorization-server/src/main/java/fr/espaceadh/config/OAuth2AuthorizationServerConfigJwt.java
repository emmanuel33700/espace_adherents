package fr.espaceadh.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.Set;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import fr.espaceadh.dao.AuthorizationDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.JwtGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2AccessTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2AccessTokenResponseAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.http.MediaType;

@Configuration
public class OAuth2AuthorizationServerConfigJwt {
    static final Set<String> DESKTOP_SCOPES = Set.of(
            "ress-autorization-read", "ress-autorization-write", "ress-autorization-del",
            "ress-adherent-admin", "ress-adherent-read", "ress-adherent-write", "ress-adherent-del",
            "ress-adhesion-admin", "ress-adhesion-read", "ress-adhesion-write", "ress-adhesion-del",
            "ress-communication-read");

    @Bean
    @Order(1)
    SecurityFilterChain authorizationSecurity(HttpSecurity http, AuthenticationManager manager,
            OAuth2AuthorizationService authorizations, OAuth2TokenGenerator<?> tokens) throws Exception {
        var server = OAuth2AuthorizationServerConfigurer.authorizationServer();
        var responseHandler = new OAuth2AccessTokenResponseAuthenticationSuccessHandler();
        responseHandler.setAccessTokenResponseCustomizer(new LegacyTokenResponseCustomizer(authorizations));
        return http.securityMatcher(server.getEndpointsMatcher())
                .cors(Customizer.withDefaults())
                .with(server, config -> config.tokenEndpoint(endpoint -> endpoint
                        .accessTokenRequestConverter(new PasswordGrantAuthenticationConverter())
                        .accessTokenRequestConverter(new ClientCredentialsAuthenticationConverter())
                        .accessTokenResponseHandler(responseHandler)
                        .authenticationProvider(new PasswordGrantAuthenticationProvider(manager, authorizations, tokens))))
                .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
                .exceptionHandling(exceptions -> exceptions.defaultAuthenticationEntryPointFor(
                        new LoginUrlAuthenticationEntryPoint("/login"), new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
                .build();
    }

    @Bean
    RegisteredClientRepository registeredClientRepository(Environment env, BCryptPasswordEncoder encoder) {
        RegisteredClient desktop = RegisteredClient.withId("desktop")
                .clientId(env.getRequiredProperty("oauth2.desktop.clientid"))
                .clientSecret(encoder.encode(env.getRequiredProperty("oauth2.desktop.clientsecret")))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(PasswordGrantAuthenticationToken.GRANT_TYPE)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://www.example.com").redirectUri("http://localhost:8089/")
                .scopes(scopes -> scopes.addAll(DESKTOP_SCOPES))
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
                .tokenSettings(tokenSettings(env, "oauth2.desktop")).build();
        RegisteredClient resource = RegisteredClient.withId("ress-adherents")
                .clientId(env.getRequiredProperty("oauth2.ress-adh.clientid"))
                .clientSecret(encoder.encode(env.getRequiredProperty("oauth2.ress-adh.clientsecret")))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("ress-autorization-admin").scope("ress-adherent-read-client-credentials")
                .tokenSettings(tokenSettings(env, "oauth2.ress-adh")).build();
        return new InMemoryRegisteredClientRepository(desktop, resource);
    }

    private TokenSettings tokenSettings(Environment env, String prefix) {
        return TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofSeconds(env.getRequiredProperty(prefix + ".accesstoken.validity", Long.class)))
                .refreshTokenTimeToLive(Duration.ofSeconds(env.getRequiredProperty(prefix + ".refreshtoken.validity", Long.class)))
                .build();
    }

    @Bean
    OAuth2AuthorizationService authorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }

    @Bean
    AuthorizationServerSettings authorizationServerSettings(Environment env) {
        return AuthorizationServerSettings.builder().issuer(env.getRequiredProperty("oauth2.issuer"))
                .authorizationEndpoint("/oauth/authorize").tokenEndpoint("/oauth/token")
                .tokenIntrospectionEndpoint("/oauth/check_token").tokenRevocationEndpoint("/oauth/revoke")
                .jwkSetEndpoint("/oauth/jwks").build();
    }

    @Bean
    JWKSource<SecurityContext> jwkSource(Environment env) throws Exception {
        KeyStore store = KeyStore.getInstance("JKS");
        char[] password = env.getRequiredProperty("jks.keypass").toCharArray();
        Path keystore = Path.of(env.getRequiredProperty("jks.keystore"));
        if (!keystore.isAbsolute()) {
            Path envFile = Path.of(env.getProperty("ENV_FILE", ".env")).toAbsolutePath();
            keystore = envFile.getParent().resolve(keystore).normalize();
        }
        try (var input = Files.newInputStream(keystore)) {
            store.load(input, password);
        }
        String alias = env.getRequiredProperty("jks.alias");
        var certificate = store.getCertificate(alias);
        if (certificate == null || !store.isKeyEntry(alias)) {
            throw new IllegalStateException("Aucune cle privee avec certificat pour l'alias '" + alias
                    + "' dans le keystore " + keystore + ". Verifier JKS_KEYSTORE et JKS_ALIAS.");
        }
        RSAKey key = new RSAKey.Builder((RSAPublicKey) certificate.getPublicKey())
                .privateKey((RSAPrivateKey) store.getKey(alias, password)).keyID(alias).build();
        return (selector, context) -> selector.select(new JWKSet(key));
    }

    @Bean
    JwtDecoder jwtDecoder(JWKSource<SecurityContext> keys) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(keys);
    }

    @Bean
    OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> keys, AuthorizationDao dao) {
        JwtGenerator jwt = new JwtGenerator(new NimbusJwtEncoder(keys));
        jwt.setJwtCustomizer(new CustomTokenEnhancer(dao));
        return new DelegatingOAuth2TokenGenerator(jwt, new OAuth2AccessTokenGenerator(), new OAuth2RefreshTokenGenerator());
    }
}
