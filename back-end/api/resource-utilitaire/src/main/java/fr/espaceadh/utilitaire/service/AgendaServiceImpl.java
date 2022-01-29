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
package fr.espaceadh.utilitaire.service;

import fr.espaceadh.adherents.dao.AdherentsDAO;
import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.lib.mail.GestionMail;
import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;
import fr.espaceadh.lib.mail.dto.TemplateMailEnum;
import fr.espaceadh.utilitaire.dao.AgendaDao;
import fr.espaceadh.utilitaire.dto.EvenementDto;
import fr.espaceadh.utilitaire.dto.EvenementParticipationAdherentDto;
import fr.espaceadh.utilitaire.dto.EvenementSyntheseDto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emmanuel
 */
@Service
@Transactional(readOnly = true)
public class AgendaServiceImpl implements AgendaService{

    @Autowired
    private Environment env;
        
    @Autowired
    private GestionMail getionMail;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaServiceImpl.class);      

    @Autowired
    protected AgendaDao agendaDao;
    
    @Autowired
    protected AdherentsDAO adherentsDAO;
    
    @Autowired
    private GestionMail sendMail;
            
        
    /**
     * Crééer un évènement
     * @param evenement
     * @param envoyerMailInfo indique si il faut envoyer un mail d'information au adhérent
     * @return  true si évènement est créé
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean creerEvenement(EvenementDto evenement, boolean  envoyerMailInfo) {
        boolean resultCreation = this.agendaDao.creerEvenement(evenement);
        
        if (envoyerMailInfo) {
            LOGGER.debug("Envoyer un mail d'information au adhérent sur la création d'un évènement {}", evenement.getIdEvenement());
            //envoyer un email au adhérent
            Collection<AdherentDto> lstAdherent = this.adherentsDAO.recupererListeAdherentSaison();
            for(AdherentDto adhDto : lstAdherent) {
                if (adhDto.getEmail() != null) {
                    MailInDto mailIn = new MailInDto();

                    /* adresse email de destination */
                    Collection<String> messageTo = new ArrayList<>();
                    messageTo.add(adhDto.getEmail());
                    mailIn.setMessageTo(messageTo);

                    mailIn.setSujetMail(evenement.getDescriptionCourte());
                    
                    /* type de template */
                    mailIn.setTemplateMailEnum(TemplateMailEnum.INFORMATION_EVENEMENT_AJOUTE);

                    /* id du message**/
                    mailIn.setIdMAil(env.getProperty("message.evenement.ajoute.id"));
                    
                    /* variables associées au tempalte **/
                    HashMap<String, String> templateVariables = new HashMap<>();
                    templateVariables.put("adh_prenom", adhDto.getPrenom());
                    templateVariables.put("libelle_court_manifestation", evenement.getDescriptionCourte());
                    templateVariables.put("libelle_long_manifestation", evenement.getDescriptionLongue());
                    templateVariables.put("date_debut_manifestation", this.dateToString(evenement.getDateDebut()));
                    templateVariables.put("heure_debut_manifestation", this.heureToString(evenement.getDateDebut()));
                    templateVariables.put("date_fin_manifestation", this.dateToString(evenement.getDateFin()));
                    templateVariables.put("heure_fin_manifestation", this.heureToString(evenement.getDateFin()));

                    // construction d'une partie du mail uniquement si il y a besoin de confirmer la participation
                    if (evenement.isDemanderConfirmationParticipation()) {
                        templateVariables.put("demande_reponse", "O");

                        templateVariables.put("url_lien_participe_evnt",
                                env.getProperty("confirmparticipationevenement.url")
                                        .concat("?mailadh=").concat(adhDto.getEmail())
                                        .concat("&idadh=").concat(adhDto.getId().toString())
                                        .concat("&idevt=").concat(String.valueOf(evenement.getIdEvenement()))
                                        .concat("&participation=").concat("TRUE")
                        );

                        templateVariables.put("url_lien_participe_pas_evnt",
                                env.getProperty("confirmparticipationevenement.url")
                                        .concat("?mailadh=").concat(adhDto.getEmail())
                                        .concat("&idadh=").concat(adhDto.getId().toString())
                                        .concat("&idevt=").concat(String.valueOf(evenement.getIdEvenement()))
                                        .concat("&participation=").concat("FALSE")
                        );


                    } else {
                        templateVariables.put("demande_reponse", "N");
                    }






                    mailIn.setTemplateVariables(templateVariables);


                    /* demande d'envoie du mail */
                    Collection<MailOutDto> mailOut = sendMail.sendMail(mailIn);
                }


                
            }
        } 
        return resultCreation;
    }

    /**
     * Convertion d'un format date en date yyy-MM-dd
     * @param date
     * @return 
     */
    private String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("EEE d MMM yyyy ");

        return sdf.format(date);
    }
    
    /**
     * Convertir un format date en heure HH:mm
     * @param date
     * @return 
     */
    private String heureToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("HH:mm");

        return sdf.format(date);
    }
    
    /**
     * Recuperer la liste des évènement en fonction du type d'autority
     * @param typeAutority
     * @return liste d'évènement
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<EvenementDto> getLstEvenement(int typeAutority) {
        return this.agendaDao.getLstEvenement(typeAutority);
    }

        /**
     * Mise à jour d'un évènement
     * @param evenement
     * @return  true si l'évènement est modifié
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean updateEvenement(EvenementDto evenement) {
        
        EvenementDto evenementtmp = this.agendaDao.getEvenement(evenement.getIdEvenement());
        
        if (evenement.getDateDebut() == null) evenement.setDateDebut(evenementtmp.getDateDebut());
        if (evenement.getDateFin()== null) evenement.setDateFin(evenementtmp.getDateFin());
        if (evenement.getDescriptionCourte()== null) evenement.setDescriptionCourte(evenementtmp.getDescriptionCourte());
        if (evenement.getDescriptionLongue()== null) evenement.setDescriptionLongue(evenementtmp.getDescriptionLongue());
        if (evenement.getIdAuthority() == 0) evenement.setIdAuthority(evenementtmp.getIdAuthority());
        if (evenement.getLieux()== null) evenement.setLieux(evenementtmp.getLieux());
        
        
        return this.agendaDao.updateEvenement(evenement);
    }

    /**
     * Récupérer le détail d'un évènement
     *
     * @param idEvenement
     * @return
     */
    @Override
    public EvenementDto getEvenement(long idEvenement) {
        return this.agendaDao.getEvenement(idEvenement);
    }

    /**
     * Supression d'un évènement
     * @param idEvenement id de l'évènement à supprimer
     * @return true si l'évènement est supprimé 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean deleteEvenement(long idEvenement) {
        this.agendaDao.deleteParticipationEvenement(idEvenement);
        return this.agendaDao.deleteEvenement(idEvenement);
    }

    
    /**
     * Récupérer la synthèse des participations aux manifestations
     * @param dateDebut date min de démarage de la conférence
     * @param dateFin date max de démarage de la conférence
     * @param demandeConfirParticipation indique si une confirmation de la participation est nécésaire
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<EvenementSyntheseDto> recupererSyntheseParticipations(Date dateDebut, Date dateFin, boolean demandeConfirParticipation) {
        return this.agendaDao.recupererSyntheseParticipations(dateDebut, dateFin, demandeConfirParticipation);
    }

            /**
     * Récupérer les participations adhérents pour un évènement
     * @param idEvenement
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<EvenementParticipationAdherentDto> recupererSyntheseParticipationAdherents(long idEvenement) {
        return this.agendaDao.recupererSyntheseParticipationAdherents(idEvenement);
    }
    
    
    
    
}
