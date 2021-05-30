/*
 * Copyright (C) 2021 emmanuel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.espaceadh.test;

import fr.espaceadh.adherents.clientapi.authorization.JSON;
import fr.espaceadh.adherents.clientapi.authorization.Roles;
import fr.espaceadh.adherents.service.AdherentServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author emmanuel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
  @TestPropertySource("classpath:application-dev.properties")
public class ClientResourceAuthorizationTest {
    
        @Autowired
    private Environment env;
    
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ClientResourceAuthorizationTest.class);
    
        @Configuration
    @ComponentScan({"",})
    public static class SpringConfig {

    }
        
        
        public void testRecupurerAuthority(){
        recupererAutorityAdherent(37);
    }


    public void testChangerUserName(){
        OAuth2AccessToken token = this.recupererToken();
        
        LOGGER.info(token.toString());
        this.changerUserName(37, "toto.toto@hotmail.fr", token.getValue());
        
    }    
    
        /**
     * Récupérer l'autority la plus élevé de l'adhérent
     * @param idAdh
     * @return 
     */
    private int recupererAutorityAdherent(long idAdh){
        try {
            //Récuparation de l'accessToken
            OAuth2AccessToken token = this.recupererToken();
            
            LOGGER.info("TOKEN {}",token);
            
            StringBuilder bearer = new StringBuilder();
            bearer.append("Bearer ");
            bearer.append(token.toString());
            

            String urlString = env.getProperty("api.ress.authorization.url");
            String urlStringWithIdClient = urlString.replace("$", String.valueOf(idAdh));
            
            LOGGER.info("URL {}", urlStringWithIdClient);
            
            URL url = new URL(urlStringWithIdClient);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Authorization", bearer.toString());
            int status = con.getResponseCode();
            
            LOGGER.info("code Statut {}",status);
            
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            
            LOGGER.info("Reponse {}", content.toString());
            
            con.disconnect();
            
            JSON json = new JSON();
            Roles roles = json.deserialize(content.toString(), Roles.class);
            
            
            int roleMax = 0;
            for(Roles.RolesEnum role : roles.getRoles()) {
                if (role == Roles.RolesEnum.ADHERENT & roleMax <1){
                    roleMax = 1;
                }
                else if (role == Roles.RolesEnum.CONSEIL & roleMax <2) {
                     roleMax = 2;
                }
                else if (role == Roles.RolesEnum.BUREAU & roleMax <3){
                    roleMax = 3;
                }
                else if (role == Roles.RolesEnum.ADMIN & roleMax <4) {
                     roleMax = 4;
                }
            }
            
            LOGGER.info("Role max {}", roleMax);
            
        } catch (MalformedURLException ex) {
             LOGGER.info("ERROR", ex);
        } catch (ProtocolException ex) {
            LOGGER.info("ERROR", ex);
        } catch (IOException ex) {
            LOGGER.info("ERROR", ex);
        }
        
        return 1;
        
    }
    
    /**
     * 
     * @param idAdh
     * @param email
     * @param token
     * @return 
     */
    private boolean changerUserName (long idAdh, String email, String token){
        try {
            StringBuilder bearer = new StringBuilder();
            bearer.append("Bearer ");
            bearer.append(token);

            
            StringBuilder urlstring = new StringBuilder();
            urlstring.append(env.getProperty("api.ress.authorization.put.url"));
            urlstring.append(idAdh);
            
            LOGGER.info("URL de la ressource {}", urlstring.toString());
            
            
            URL url = new URL(urlstring.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Authorization", bearer.toString());
            con.setDoOutput(true);
            
            
            StringBuilder jsonInputString = new StringBuilder();
            jsonInputString.append(" { ");
            jsonInputString.append(" \"idAdh\":");
            jsonInputString.append(idAdh);
            jsonInputString.append(" , ");
            jsonInputString.append(" \"login\":");
            jsonInputString.append(" \"");
            jsonInputString.append(email);
            jsonInputString.append("\"");
            jsonInputString.append(" } ");
            
             LOGGER.info("Data envoyé {}", jsonInputString.toString());
             
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(jsonInputString.toString());
            osw.flush();
            osw.close();

            final int status = con.getResponseCode();
            
            con.disconnect();
            
            LOGGER.info("code Statut {}",status);
            
            
            if (status == 200) return true;
            else {
                LOGGER.error("Error de l'API de modification du username {} ", con.getResponseCode());
            }
            return false;
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(AdherentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AdherentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    private OAuth2AccessToken recupererToken() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setClientSecret(env.getProperty("oauth2.ress-adh.clientsecret"));
        resourceDetails.setClientId(env.getProperty("oauth2.ress-adh.clientid"));
        resourceDetails.setAccessTokenUri(env.getProperty("oauth2.url"));
       // resourceDetails.setScope("ress-autorization-admin");

        OAuth2RestTemplate oAuthRestTemplate = new OAuth2RestTemplate(resourceDetails);

        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );

        return  oAuthRestTemplate.getAccessToken();
    }
    
}
