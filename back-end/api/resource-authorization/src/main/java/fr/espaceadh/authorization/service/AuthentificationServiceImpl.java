/*
 * Copyright (C) 2019 emmanuel33700 https://github.com/emmanuel33700/espace_adherents
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
package fr.espaceadh.authorization.service;

import fr.espaceadh.authorization.clientapi.adherents.JSON;
import fr.espaceadh.authorization.dao.AuthoritiesDao;
import fr.espaceadh.authorization.dao.userDao;
import fr.espaceadh.authorization.dto.AuthoritiesDto;
import fr.espaceadh.authorization.dto.RolesEnum;
import fr.espaceadh.authorization.dto.UserDto;
import fr.espaceadh.authorization.model.Adherent;
import fr.espaceadh.lib.mail.GestionMail;
import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;
import fr.espaceadh.lib.mail.dto.TemplateMailEnum;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
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
public class AuthentificationServiceImpl implements AuthentificationService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AuthentificationServiceImpl.class);
    
    @Autowired
    private GestionMail getionMail;
    
    @Autowired
    protected userDao userDao;
    
    @Autowired
    protected AuthoritiesDao authoritiesDao;
    
    @Autowired
    private Environment env;
    
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int creerUser(UserDto usersDto) {
        
        Adherent adh = this.verifierIdAdherent(usersDto);
        
        if (adh == null) {
            LOGGER.error("erreur technique sur appel du WS ressource-adherent");
            return 99;
        }
        else if ( ! adh.getEmail().equalsIgnoreCase(usersDto.getUsername())){
            LOGGER.error("tentative de fraude sur la création de compte l'id {} ne corespond pas au login ", usersDto.getIdAdherent(), usersDto.getUsername());
            return 2;
        }
                
        // création du compte dans la BD d'authorisation
        UUID uuid = UUID.randomUUID();
        usersDto.setCleeModification(uuid.toString());
        usersDto.setDateModifcationClee(new Date());
        usersDto.setDateCreation(new Date());
        usersDto.setEnabled(false);
        
        userDao.creationUser(usersDto);
        
        // création de l'authorité BD d'authorisation
        AuthoritiesDto authoritiesDto = new AuthoritiesDto();
        authoritiesDto.setUsername(usersDto.getUsername());
        List<RolesEnum> roles = new ArrayList<>();
        roles.add(RolesEnum.ADHERENT); // Par defaut création du role adhérent
        authoritiesDto.setRoles(roles);
        authoritiesDao.creationAutorities(authoritiesDto);
        
        //envoie du mail de validation du compte
        MailInDto mailIn = new MailInDto();

        
        Collection<String> messageTo = new ArrayList<>();
        messageTo.add(usersDto.getUsername());
        mailIn.setMessageTo(messageTo);
        
        
        /* type de template */
        mailIn.setTemplateMailEnum(TemplateMailEnum.DEMANDE_VALIDATION_MAIL);
        
        /* variables associées au tempalte **/
        HashMap<String, String> templateVariables = new HashMap<>();
        templateVariables.put("adh_prenom", adh.getPrenom());
        templateVariables.put("confirmation_link", env.getProperty("validationmail.url")
                .concat("?token=").concat(uuid.toString()
                .concat("&id=").concat(Long.toString(adh.getId()))));
        mailIn.setTemplateVariables(templateVariables);
        
                /** Sujet du mail **/
        mailIn.setSujetMail("Finalisation de votre inscription");
        
        MailOutDto mailOut = getionMail.sendMail(mailIn);
        
        if (mailOut.getStatutEnvoi().endsWith("success")) return 0;
        else return 99;
    }

    
    @Override
    public boolean validationCreationUser(int idUser, String cleeValidation) {
        return userDao.validationUtilisateur(idUser, cleeValidation);
    }

    /**
     * Récupérer token d'accès oauth2
     * @return 
     */
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
     * 
     * @param usersDto
     * @return 
     */
    private Adherent  verifierIdAdherent(UserDto usersDto) {
        
        Adherent adh = null;
        try {
            // vérification dans la BD adhérent si l'utilsateur à été créé
            OAuth2AccessToken token = this.recupererToken();
            
            LOGGER.info("TOKEN {}",token);
            
            //https://www.baeldung.com/java-http-request
            
            
            StringBuilder bearer = new StringBuilder();
            bearer.append("Bearer ");
            bearer.append(token.toString());
            
            
            
            StringBuilder urlstring = new StringBuilder();
            urlstring.append(env.getProperty("api.ress.adherents.url"));
            urlstring.append(usersDto.getIdAdherent());
            
            
            
            URL url = new URL(urlstring.toString());
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
            adh = json.deserialize(content.toString(), Adherent.class);

            
            
        } catch (MalformedURLException ex) {
            LOGGER.error("MalformedURLException {}", ex.getMessage());
        } catch (IOException ex) {
            LOGGER.error("MalformedURLException {}", ex.getMessage());
        }
        
        return adh;
        
    }

    /**
     * Modifier les roles d'un utilisateur
     * @param idUser
     * @param rolesEnum
     * @return 
     */
    @Override
    public boolean modifierRolesUtilisateur(int idUser, List<RolesEnum> rolesEnum ) {
        
        /** on recherche l'utilisateur rematif à l'id **/
        UserDto  user = this.userDao.lectureUtilisateur(idUser);
        
        /** suppression des autorities **/
        authoritiesDao.suppressionAutorities(user.getUsername());
        
        AuthoritiesDto authoritiesDto = new AuthoritiesDto();
        authoritiesDto.setUsername(user.getUsername());
        authoritiesDto.setRoles(rolesEnum);
        
        authoritiesDao.creationAutorities(authoritiesDto);
        
        return true;
    }

    /**
     * Recuperer les authorities
     * @param idUser
     * @return 
     */
    @Override
    public AuthoritiesDto recupererAuthorities(int idUser) {
        /** on recherche l'utilisateur rematif à l'id **/
        UserDto  user = this.userDao.lectureUtilisateur(idUser);

        return this.authoritiesDao.recupererAutorities(user.getUsername());
    }



    
}
