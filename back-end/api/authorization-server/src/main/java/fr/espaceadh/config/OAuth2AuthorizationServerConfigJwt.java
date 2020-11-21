package fr.espaceadh.config;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfigJwt extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private Environment env;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2AuthorizationServerConfigJwt.class);  
    
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception { // @formatter:off
        
        clients.inMemory()
                .withClient(env.getProperty("oauth2.desktop.clientid"))
                .secret(passwordEncoder().encode(env.getProperty("oauth2.desktop.clientsecret")))
                .authorizedGrantTypes("password", "refresh_token", "authorization_code")
                .autoApprove(true)
                .scopes("ress-autorization-read", "ress-autorization-write", "ress-autorization-del",
                        "ress-adherent-admin", "ress-adherent-read", "ress-adherent-write", "ress-adherent-del",
                        "ress-communication-read")
                .accessTokenValiditySeconds(env.getProperty("oauth2.desktop.accesstoken.validity", Integer.class))       
                .refreshTokenValiditySeconds(env.getProperty("oauth2.desktop.refreshtoken.validity", Integer.class)) 
                .redirectUris("http://www.example.com","http://localhost:8089/")
                
                .and()
                .withClient(env.getProperty("oauth2.ress-adh.clientid"))
                .secret(passwordEncoder().encode(env.getProperty("oauth2.ress-adh.clientsecret")))
                .authorizedGrantTypes("client_credentials")
                .scopes("ress-autorization-admin", "ress-adherent-read-client-credentials")
                .accessTokenValiditySeconds(env.getProperty("oauth2.ress-adh.accesstoken.validity", Integer.class))       
                .refreshTokenValiditySeconds(env.getProperty("oauth2.ress-adh.refreshtoken.validity", Integer.class))             
                ;
                                                                                                                                                                                                                                                                                                                                                                                                
    } // @formatter:on

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        
 
        return defaultTokenServices;
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain).authenticationManager(authenticationManager);
        
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
       // converter.setSigningKey("123");
        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(env.getProperty("jks.keystore")), env.getProperty("jks.keypass").toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(env.getProperty("jks.alias")));
        
        return converter;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
