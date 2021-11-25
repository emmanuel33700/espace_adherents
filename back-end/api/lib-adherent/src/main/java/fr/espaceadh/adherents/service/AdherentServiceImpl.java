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
package fr.espaceadh.adherents.service;

import fr.espaceadh.adherents.dao.AdherentsDAO;
import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.dto.AdhesionDto;
import fr.espaceadh.adherents.dto.LienAdherentsDto;
import fr.espaceadh.lib.mail.GestionMail;
import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;
import fr.espaceadh.lib.mail.dto.TemplateMailEnum;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import org.slf4j.Logger;
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
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 */
@Service
@Transactional(readOnly = true)
public class AdherentServiceImpl implements AdherentService{
    
    @Autowired
    private Environment env;

    @Autowired
    protected AdherentsDAO adherentsDAO;
    
        
    @Autowired
    private GestionMail getionMail;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdherentServiceImpl.class);  
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int creerAdherent(AdherentDto adherentDto) {
        
        if (adherentsDAO.getAdherentByLogin(adherentDto.getEmail() ) != null){
            LOGGER.info("Adhérent qui souhaite etre créé {} existe déjà en BD ", adherentDto.getEmail());
            return 10;
        }
        
        long idAdherent = adherentsDAO.creerAdherent(adherentDto);
        
 
        
        return 0;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AdherentDto recupererAdherent(Long idAdh) {
        return adherentsDAO.getAdherentByID(idAdh);
    }

    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<AdherentDto> recupererListeCompletAdherent() {
        return adherentsDAO.recupererListeCompletAdherent();
    }

    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<AdherentDto> recupererListeAdherentSaison() {
        return adherentsDAO.recupererListeAdherentSaison();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean updateAdherents(AdherentDto adherentDto) {
        
        final AdherentDto dtoAdherentOld = this.adherentsDAO.getAdherentByID(adherentDto.getId());
        
        
        
        if (dtoAdherentOld.getEmail() != null && adherentDto.getEmail() != null 
                && !dtoAdherentOld.getEmail().equals(adherentDto.getEmail())){
            LOGGER.info("Changement de username demandé  : {} => {} ", dtoAdherentOld.getEmail() , adherentDto.getEmail());

            
            OAuth2AccessToken token = this.recupererToken();
            this.changerUserName(adherentDto.getId(), adherentDto.getEmail(), token.getValue());
        } 
        // si l'adhérent vient de créer un mail et si l'adhérent est adhérent de la saision => Création d'un accès
        else if (dtoAdherentOld.getEmail() == null &&  adherentDto.getEmail() != null) {
            this.creerEtActiverCompte(adherentDto.getId());
        }
        
        return this.adherentsDAO.updateAdherents(adherentDto);
    }

    /**
     * Ajouter une adhésion à adhérent
     * @param adhesionDto
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean creerAdhesion(AdhesionDto adhesionDto) {
        
        boolean resulte = this.adherentsDAO.creerAdhesion(adhesionDto);

        if (resulte) this.creerEtActiverCompte(adhesionDto.getIdAdherent());
        
        return resulte;
    }
    
    /**
     * créer et activer un compte
     * @param idAdh
     * @return 
     */
    public boolean creerEtActiverCompte(long idAdh){
        AdherentDto adhDto = this.adherentsDAO.getAdherentByID(idAdh);
        
        //Activiation du compte uniquement si l'adhérent à un email 
        if (adhDto.getEmail() != null){
            // activier le compte
            OAuth2AccessToken token = this.recupererToken();
            int codehttp = this.activerCompte(idAdh, token.getValue());

            // Si le compte ne peut pas etre activé, c'est que l'adhérent n'a pas encore créé d'accès
            if (codehttp != 200) {
                LOGGER.info("Adherent {} n'a pas de compte d'acces ", adhDto.getEmail());
                //envoie du mail de validation du compte
                 MailInDto mailIn = new MailInDto();


                 Collection<String> messageTo = new ArrayList<>();
                 messageTo.add(adhDto.getEmail());
                 mailIn.setMessageTo(messageTo);


                 /* type de template */
                 mailIn.setTemplateMailEnum(TemplateMailEnum.INFORMATION_PRE_INSCRIPTION);

                 /* variables associées au tempalte **/
                 HashMap<String, String> templateVariables = new HashMap<>();
                 templateVariables.put("adh_prenom", adhDto.getPrenom());
                 templateVariables.put("confirmation_link", 
                         env.getProperty("validationmail.url")
                                 .concat("?mail=").concat(adhDto.getEmail())
                                 .concat("&id=").concat(Long.toString(idAdh))
                 );
                 mailIn.setTemplateVariables(templateVariables);

                 /** Sujet du mail **/
                 mailIn.setSujetMail("Votre préinscription");

                 /** Id de mail **/
                 mailIn.setIdMAil(env.getProperty("message.preinscription.id"));

                 Collection<MailOutDto> mailOut = getionMail.sendMail(mailIn);
            }
        }
        
        return true;
        
    }

    @Override
    public Collection<AdhesionDto> getAdhesionsPourUnAdherent(Long idAdh) {
        return this.adherentsDAO.getAdhesionsPourUnAdherent(idAdh);
    }

    @Override
    public AdhesionDto getAdhesionPourUnAdherent(Long idAdh, Long idAnneAdhesion) {
        return this.adherentsDAO.getAdhesionPourUnAdherent(idAdh, idAnneAdhesion);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean majAdhesion(AdhesionDto adhesionDto) {
        //TODO a implémenter
        return false;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean suppAdhesion(Long idAdh, Long idAnneAdhesion) {
        //TODO a implémenter
        return false;
    }

    /**
     * 
     * @param idAdherent
     * @param nomPhoto
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean updateLienPhotoAdherent(long idAdherent, String nomPhoto) {
        AdherentDto adherentDto = adherentsDAO.getAdherentByID(idAdherent);
        adherentDto.setLienPhotoProfil(nomPhoto);
        return this.adherentsDAO.updateAdherents(adherentDto);
    }

    /**
     * Récupérer les informations adhérents via son adresse email
     * @param email
     * @return 
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AdherentDto recupererAdherent(String email) {
       return adherentsDAO.getAdherentByLogin(email);
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
            LOGGER.error("MalformedURLException ", ex);
        } catch (IOException ex) {
             LOGGER.error("IOException ", ex);
        }
        return false;
    }

    /**
     * Appel de l'API d'activation d'un compte
     * @param idAdh
     * @param token
     * @return 
     */
    private int activerCompte(Long idAdh, String token) {
        try {
            StringBuilder bearer = new StringBuilder();
            bearer.append("Bearer ");
            bearer.append(token);

            
            String urlString = env.getProperty("api.ress.authorization.activation.url");
            String urlStringWithIdClient = urlString.replace("$", String.valueOf(idAdh));

            
            LOGGER.info("URL de la ressource {}", urlStringWithIdClient);
            
            
            URL url = new URL(urlStringWithIdClient);
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
            jsonInputString.append(" \"statutActivation\":true");
            jsonInputString.append(" } ");
            
             LOGGER.info("Data envoyé {}", jsonInputString.toString());
             
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(jsonInputString.toString());
            osw.flush();
            osw.close();

            final int status = con.getResponseCode();
            
            con.disconnect();
            
            LOGGER.info("code Statut {}",status);
            
            
            if (status != 200) {
                LOGGER.error("Error de l'API de modification du username {} ", con.getResponseCode());
            }
            return status;

        } catch (MalformedURLException ex) {
            LOGGER.error("MalformedURLException ", ex);
        } catch (IOException ex) {
             LOGGER.error("IOException ", ex);
        }
        return 500;
    }

    /**
     * Recherche d'un lien representant <=> representé entre deux adhérent
     * @param idAdherentRepresentant
     * @param idAdherentRepresente
     * @return 
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public LienAdherentsDto getLienAdherent(Long idAdherentRepresentant, Long idAdherentRepresente) {
        return adherentsDAO.getLienAdherent(idAdherentRepresentant, idAdherentRepresente);
    }

        /**
     * recupérer la liste des personnes représenté par un adhérent
     * @param idAdherentRepresentant
     * @return 
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Collection<LienAdherentsDto> getLiensAdherent(Long idAdherentRepresentant) {
        return adherentsDAO.getLiensAdherent(idAdherentRepresentant);
    }

        /**
     * Ajouter un lien entre deux adhérents
     * @return 
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean ajouterLienAdherent(Long idAdherentRepresentant, Long idAdherentRepresente) {
        return adherentsDAO.ajouterLienAdherent(idAdherentRepresentant, idAdherentRepresente);
    }

        /**
     * Supprimer un lien de représentation entre 2 adhérents
     * @param idAdherentRepresentant
     * @param idAdherentRepresente
     * @return 
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean supprimerLienAdherent(Long idAdherentRepresentant, Long idAdherentRepresente) {
        return adherentsDAO.supprimerLienAdherent(idAdherentRepresentant, idAdherentRepresente);
    }
    
}
