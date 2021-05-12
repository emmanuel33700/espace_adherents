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
package fr.espaceadh.adherents.service;

import fr.espaceadh.adherents.clientapi.authorization.JSON;
import fr.espaceadh.adherents.clientapi.authorization.Roles;
import fr.espaceadh.adherents.dao.AdherentEvenementsDAO;
import fr.espaceadh.adherents.dao.AdherentsDAO;
import fr.espaceadh.adherents.dto.AdherentEvenementDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emmanuel
 */
@Service
@Transactional(readOnly = true)
public class AdherentEvenementsServiceImpl implements AdherentEvenementsService{

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AdherentEvenementsServiceImpl.class);
    
    @Autowired
    private Environment env;
    
    @Autowired
    private AdherentEvenementsDAO adherentEvenementsDAO;

            
    /**
     * Recupérer les évenmenets pour un adhérents en fonction des ces droits
     * @param idAdh
     * @return 
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<AdherentEvenementDto> getLstEvenement(long idAdh) {
        return this.getLstEvenement(idAdh, null, null);
    }

    /**
     * Recupérer les évenmenets pour un adhérents en fonction des ces droits des une plage de recherche sur date
     * @param idAdh
     * @param dateDebut
     * @param datefin
     * @return 
     */
    @Override
    public Collection<AdherentEvenementDto> getLstEvenement(long idAdh, Date dateDebut, Date datefin) {
        int typeAuthority = this.recupererAutorityAdherent(idAdh);
        return this.adherentEvenementsDAO.getLstEvenement(typeAuthority, idAdh, dateDebut, datefin); 
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
            
            return roleMax;
            
            
        } catch (MalformedURLException ex) {
             LOGGER.info("ERROR MalformedURLException ", ex);
        } catch (ProtocolException ex) {
            LOGGER.info("ERROR ProtocolException", ex);
        } catch (IOException ex) {
            LOGGER.info("ERROR IOException", ex);
        }
        
        return 0;
        
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

    /**
     * REcuperer le détail d'un évènement pour un adhérent
     * @param idAdh
     * @param idEvenement
     * @return 
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AdherentEvenementDto getEvenement(long idAdh, long idEvenement) {
        int typeAuthority = this.recupererAutorityAdherent(idAdh);
        return this.adherentEvenementsDAO.evenement(typeAuthority, idAdh, idEvenement);
    }

    /**
     * Mise à jour d'une participation à un évènement
     * @param idAdh
     * @param idEvenement
     * @param typeParticipation
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean updateParticipationEvenement(long idAdh, long idEvenement, int typeParticipation) {
        this.adherentEvenementsDAO.deleteParticipationEvenement(idEvenement, idAdh);
        
        //Si l'adhérent participe ou ne participe pas
        if (typeParticipation != 3 ){
            boolean participeEvenement = true;
            if (typeParticipation == 2) participeEvenement = false;
            return this.adherentEvenementsDAO.creationParticipationEvenement(idEvenement, idAdh, participeEvenement);
        }
        return true;
        
    }
    
}
