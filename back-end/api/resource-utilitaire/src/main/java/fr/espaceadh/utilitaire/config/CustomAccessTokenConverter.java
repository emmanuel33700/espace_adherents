package fr.espaceadh.utilitaire.config;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAccessTokenConverter.class);  
    
    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        LOGGER.info("********************* claims {}", claims);
        
        OAuth2Authentication authentication = super.extractAuthentication(claims);
        authentication.setDetails(claims);
        return authentication;
    }

}
