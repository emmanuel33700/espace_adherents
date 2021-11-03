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
        usersDto.setEnabled(true);
        
        userDao.creationUser(usersDto);
        
        // création de l'authorité BD d'authorisation
        AuthoritiesDto authoritiesDto = new AuthoritiesDto();
        authoritiesDto.setUsername(usersDto.getUsername());
        List<RolesEnum> roles = new ArrayList<>();
        roles.add(RolesEnum.ADHERENT); // Par defaut création du role adhérent
        authoritiesDto.setRoles(roles);
        boolean result = authoritiesDao.creationAutorities(authoritiesDto);
        
        if (result) return 0;
        return 99;
 
    }

    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AuthoritiesDto recupererAuthorities(int idUser) {
        /** on recherche l'utilisateur rematif à l'id **/
        UserDto  user = this.userDao.lectureUtilisateur(idUser);
        
        if(user == null) {
            LOGGER.info("Aucun utilisateur récupéré via l'ID {} ", idUser);
            AuthoritiesDto dto = new AuthoritiesDto();
            dto.setRoles(new ArrayList<>());
            return dto;
        }

        return this.authoritiesDao.recupererAutorities(user.getUsername());
    }

    /**
     *  Modifier le username d'un utilisateur
     * @param idUser
     * @param username
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean modifierInformationUtilisateur(int idUser, String username) {
        final UserDto userOld = this.userDao.lectureUtilisateur(idUser);
        
        
        AuthoritiesDto authoritoesDto = this.authoritiesDao.recupererAutorities(userOld.getUsername());
        
        this.authoritiesDao.suppressionAutorities(userOld.getUsername());
        
        this.userDao.modifierUserNameUtilisateur(idUser, username);
        
        authoritoesDto.setUsername(username);
        this.authoritiesDao.creationAutorities(authoritoesDto);
        
        return true;
    }

    /**
     * Activier un compte utilisateur
     * @param idUser
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean activerAuthentification(int idUser) {
        return this.userDao.changerValidationUtilisateur(idUser, true);
    }

    /**
     * deactivier un compte utilisateur
     * @param idUser
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean desactiverAuthentification(int idUser) {
        return this.userDao.changerValidationUtilisateur(idUser, false);
    }

    /**
     * Récupérer les informations d'un utilisateur
     * @param idUser
     * @return 
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserDto recupererUser(int idUser) {
        return this.userDao.lectureUtilisateur(idUser);
    }

    /**
     * Désactiver l'ensemble des authentification à l'exeption des personnes qui ont le role ADMIN
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean desactiverEnsembleAuthentification() {
        return this.userDao.desactiverEnsembleAuthentification();
    }


    /**
     * Demander la réinitialisation du mot de passe (Génération d'un token envoyé par mail à l'adhérent)
     *
     * @param username
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean demanderReinitialisationMotDePasse(String username) {

        UserDto userDto = this.userDao.lectureUtilisateur(username);

        if (userDto == null) {
            this.LOGGER.info("Login non dans la BD {} " , username);
            return false;
        }
        else if (!userDto.getUsername().equalsIgnoreCase(username)) {
            this.LOGGER.error("Erreur de récupération du login {} en BD " , username);
            return false;
        }

        this.LOGGER.info("Demande de réinitialisation du mpt de passe pour le login {}", username);

        // Génération de la clée de validaton à envoyer par mail
        UUID cleeValidation = UUID.randomUUID();

        //Enregistrement de la clée en BD
        this.userDao.enregistrerCleeModification(userDto.getIdAdherent(), cleeValidation.toString());

        // Envoie du mail à l'utilisateur
        this.envoyerMailReinitMotDePasse(userDto, cleeValidation.toString() );

        return true;

    }

    /**
     * Envoyer un mail pour la réinit du mot de passe
     * @param userDto
     * @param cleeValidation
     */
    private void envoyerMailReinitMotDePasse(UserDto userDto, String cleeValidation) {
        MailInDto mailIn = new MailInDto();

        Collection<String> messageTo = new ArrayList<>();
        messageTo.add(userDto.getUsername());
        mailIn.setMessageTo(messageTo);


        /* type de template */
        mailIn.setTemplateMailEnum(TemplateMailEnum.DEMANDE_REINIT_MOT_DE_PASSE);

        /* variables associées au tempalte **/
        HashMap<String, String> templateVariables = new HashMap<>();
        templateVariables.put("valid_psw_link",
                env.getProperty("validation.password.url")
                        .concat("?key=").concat(cleeValidation)
                        .concat("&id=").concat(Integer.toString(userDto.getIdAdherent()))
        );
        mailIn.setTemplateVariables(templateVariables);

        /** Sujet du mail **/
        mailIn.setSujetMail("Réinitialisation de votre mot de passe");

        /** Id de mail **/
        mailIn.setIdMAil(env.getProperty("validation.password.id"));

        Collection<MailOutDto> mailOut = getionMail.sendMail(mailIn);
    }

    /**
     * Valider la réinitialisation du mot de passe
     *
     * @param idUser
     * @param cleeValidation
     * @param  motDePasseEncode
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean validerReinitialisationMotDePasse(int idUser, String cleeValidation, String motDePasseEncode) {
        UserDto userDto = this.userDao.lectureUtilisateur(idUser);

        if (cleeValidation == null) {
            this.LOGGER.error("Attention la clée de validation est null pour {} " , userDto.getUsername());
            return false;
        }

        else if (cleeValidation.isEmpty()) {
            this.LOGGER.error("Attention la clée de validation est vide pour {} " , userDto.getUsername());
            return false;
        }

        else if (!userDto.getCleeModification().equals(cleeValidation)) {
            this.LOGGER.error("Attention la clée de validation du changement de mot de passe pour {} est incorecte" , userDto.getUsername());
            return false;
        }

        long curTimeInMs = userDto.getDateModif().getTime();
        Date afterAddingMins = new Date(curTimeInMs + (10 * 60000));

        if (afterAddingMins.before(new Date())) {
            this.LOGGER.error("Attention : delais de vidité de  clée de validation du changement de mot de passe pour {} est dépassé" , userDto.getUsername());
            return false;
        }

        // changement du mot de passe
        this.userDao.changerMotDePasse(idUser, motDePasseEncode);

        // création d'une nouvelle clée pour éviter un nouveau changement de mot de passe
        return this.userDao.enregistrerCleeModification(userDto.getIdAdherent(), UUID.randomUUID().toString());

    }


}
