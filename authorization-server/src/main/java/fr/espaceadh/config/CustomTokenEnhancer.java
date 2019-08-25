package fr.espaceadh.config;


import fr.espaceadh.dao.AuthorizationDao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
    
    @Autowired
    private AuthorizationDao authorizationDao;
    
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();

        /** récupération de l'id de la personne **/
        Authentication userAuthentication = authentication.getUserAuthentication();
        if (userAuthentication != null) {
            Object principal = authentication.getUserAuthentication().getPrincipal();
            String username = "";
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            final String idAdherent = authorizationDao.getAuthorisation(username).getIdAdherent();
            additionalInfo.put("idAdherent", idAdherent);
        }
        
        /** récupération des authorities **/
        Collection<GrantedAuthority> authorities =  authentication.getAuthorities();
        StringBuilder stringAuthorities = new StringBuilder();
        authorities.forEach((authoritie) -> {
            stringAuthorities.append(authoritie.getAuthority());
            stringAuthorities.append(" ");
        });
        
        additionalInfo.put("authorities", stringAuthorities.toString());
        additionalInfo.put("organization", "JALLE ASTRO");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
