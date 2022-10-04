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
import fr.espaceadh.utilitaire.dao.ListeDiffusionDAO;
import fr.espaceadh.utilitaire.dto.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
public class ListeDiffusionServiceImpl implements ListeDiffusionService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(ListeDiffusionServiceImpl.class);


    @Autowired
    private GestionMail sendMail;
    
    @Autowired
    private Environment env;

    @Autowired
    protected ListeDiffusionDAO listeDiffusionDAO;

    @Autowired
    protected AdherentsDAO adherentsDAO;
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean addListeDiffusion(GroupeDiffusionDto groupeDiffusionDto) {
        return listeDiffusionDAO.addListeDiffusion(groupeDiffusionDto);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean updateListeDiffusion(GroupeDiffusionDto groupeDiffusionDto) {
        return listeDiffusionDAO.updateListeDiffusion(groupeDiffusionDto);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean deleteListeDiffusion(long idGroupeDiffusion) {
        this.listeDiffusionDAO.deleteInscriptionListeDiffusion(idGroupeDiffusion);
        return listeDiffusionDAO.deleteListeDiffusion(idGroupeDiffusion);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<GroupeDiffusionDto> getListeListeDiffusion() {
        return listeDiffusionDAO.getListeListeDiffusion();
    }

    /**
     * Envoyer un mail à une liste de diffusion
     *
     * @param emailDto
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean envoyerMailListeDiffusion(EMailDto emailDto, long idMailingListe) {

        // récupérer les adresses email de destinations
        emailDto.setLstAdherents(listeDiffusionDAO.getAdherentsInscritListeDiffusion(idMailingListe));

        //récupérer le détail de la liste de diffusion
        GroupeDiffusionDto lstDiffsionDto = listeDiffusionDAO.getListeListeDiffusion(idMailingListe);

        //Si liste de diffusion publique => Avec avec "reply to" avec l'adresse email de l'adhérent; sinon => par defauit (rien)
        if (lstDiffsionDto.getIdAuthority() != 2){
            emailDto.setMailReplyTo(null);
            emailDto.setAuteurName(null);
        }

        if (emailDto.getLstAdherents() == null || emailDto.getLstAdherents().isEmpty()) {
            LOGGER.info("Aucun mail associé à cette liste de diffusion {}", idMailingListe);
            return false;
        }

        return this.envoyerMail(emailDto );


    }

    /**
     * Création et envoie du mail
     * @param emailDto
     * @return
     */
    private boolean envoyerMail(EMailDto emailDto ) {
        MailInDto mailIn = new MailInDto();

        // renseigner les adresses de destination
        Collection<String> messageTo = new ArrayList<>();
        for (AdherentDto adh : emailDto.getLstAdherents()) {
            messageTo.add(adh.getEmail());
        }
        mailIn.setMessageTo(messageTo);

        /* type de template */
        mailIn.setTemplateMailEnum(null);

        /** ajouter un message **/
        mailIn.setHtmlMessage(emailDto.getMessageHtml());
        /**- ajouter le sujet **/
        mailIn.setSujetMail(emailDto.getSujet());

        /** ajouter l'id de mail **/
        mailIn.setIdMAil(Long.toString(emailDto.getIdMail()));

        /** ajout auteur du mail **/
        mailIn.setAuteurName(emailDto.getAuteurName());

        /** ajout email replyto **/
        mailIn.setMailReply(emailDto.getMailReplyTo());

        /** ajouter les fichiers **/

        if (emailDto.getLstFile() != null && !emailDto.getLstFile().isEmpty()){
            /** ajouter des fichiers **/
            mailIn.setLstFile(emailDto.getLstFile());
        }

        /* demande d'envoie du mail */
        Collection<MailOutDto> mailsOut = sendMail.sendMail(mailIn);

        boolean result  = true;
        for (MailOutDto mo : mailsOut) {
            if (!mo.getStatutEnvoi().equalsIgnoreCase("success")) {
                LOGGER.error("Erreor lors de l'envoi de mail");
                return false;
            }
        }
        return true;
    }

    /**
     * Envoyer un mail à une liste d'adherent
     * @param eMailDto
     * @param typeEnvoi  1 : ANNEE COURANTE
     *      * 2 : ANNEE COURANTE + ANNEE -1
     *      * 4 : NON ADHERENT
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean envoyerMailListeAdherent(EMailDto eMailDto,  int typeEnvoi) {
        // récupérer les adresses email de destinations
        Collection<AdherentDto> lstAdherents = null;
        if (typeEnvoi == 1) {
            lstAdherents = adherentsDAO.recupererListeAdherentSaison();
        }
        else if (typeEnvoi == 2) {
            lstAdherents = adherentsDAO.recupererListeAdherentSaisonEtAncienneSaison();
        }
        else if (typeEnvoi == 4) {
            lstAdherents = adherentsDAO.recupererListeNonAdherentSaison();
        } else {
            LOGGER.error("Erreur de sélection du type de diffusion");
            return false;
        }

        eMailDto.setLstAdherents(lstAdherents);
        return this.envoyerMail(eMailDto);



    }

    /**
     * recherche la liste des adhérents avec le statut d'inscription à la mailing liste
     *
     * @param idListeDiffusion
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<AdherentMailingListeDto> getListeAdherentsListDiffusion(long idListeDiffusion) {

        Collection<AdherentMailingListeDto> lst =  this.listeDiffusionDAO.getListeAdherentsListDiffusion(idListeDiffusion);
        Collections.sort((ArrayList)lst);

        return lst;
    }


}
