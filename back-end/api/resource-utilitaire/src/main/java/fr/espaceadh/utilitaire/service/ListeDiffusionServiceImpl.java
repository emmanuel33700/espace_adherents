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

import fr.espaceadh.lib.mail.GestionMail;
import fr.espaceadh.lib.mail.dto.InputStreamCustom;
import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;
import fr.espaceadh.utilitaire.dao.ListeDiffusionDAO;
import fr.espaceadh.utilitaire.dto.AdherentDto;
import fr.espaceadh.utilitaire.dto.GroupeDiffusionDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import fr.espaceadh.utilitaire.dto.MailListeDiffusionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    
    @Override
    public boolean addListeDiffusion(GroupeDiffusionDto groupeDiffusionDto) {
        return listeDiffusionDAO.addListeDiffusion(groupeDiffusionDto);
    }

    @Override
    public boolean updateListeDiffusion(GroupeDiffusionDto groupeDiffusionDto) {
        return listeDiffusionDAO.updateListeDiffusion(groupeDiffusionDto);
    }

    @Override
    public boolean deleteListeDiffusion(long idGroupeDiffusion) {
        this.listeDiffusionDAO.deleteInscriptionListeDiffusion(idGroupeDiffusion);
        return listeDiffusionDAO.deleteListeDiffusion(idGroupeDiffusion);
    }

    @Override
    public Collection<GroupeDiffusionDto> getListeListeDiffusion() {
        return listeDiffusionDAO.getListeListeDiffusion();
    }

    /**
     * Envoyer un mail à une liste de diffusion
     *
     * @param mailListeDiffusionDto
     * @return
     */
    @Override
    public boolean envoyerMailListeDiffusion(MailListeDiffusionDto mailListeDiffusionDto) {
        try {

            // récupérer les adresses email de destinations
            Collection<AdherentDto> lstAdherents = listeDiffusionDAO.getAdherentsInscritListeDiffusion(mailListeDiffusionDto.getIdListeDiffusion());

            if (lstAdherents == null || lstAdherents.isEmpty()) {
                LOGGER.info("Aucun mail associé à cette liste de diffusion {}", mailListeDiffusionDto.getIdListeDiffusion());
                return false;
            }
            MailInDto mailIn = new MailInDto();

            // renseigner les adresses de destination
            Collection<String> messageTo = new ArrayList<>();
            for (AdherentDto adh : lstAdherents) {
                messageTo.add(adh.getEmail());
            }
            mailIn.setMessageTo(messageTo);

            /* type de template */
            mailIn.setTemplateMailEnum(null);

            /** ajouter un message **/
            mailIn.setHtmlMessage(mailListeDiffusionDto.getMessageHtml());
            /**- ajouter le sujet **/
            mailIn.setSujetMail(mailListeDiffusionDto.getSujet());

            /** ajouter les fichiers **/

            if (mailListeDiffusionDto.getLstFile() != null && !mailListeDiffusionDto.getLstFile().isEmpty()){
                /** ajouter des fichiers **/
                Collection<InputStreamCustom> lstFile = new ArrayList<>();
                for (MultipartFile file : mailListeDiffusionDto.getLstFile()) {
                    InputStreamCustom iptc1 = new InputStreamCustom();
                    iptc1.setInputStream(file.getInputStream());
                    iptc1.setContentType( file.getContentType());
                    iptc1.setFileName(file.getName());
                    lstFile.add(iptc1);
                }
                mailIn.setLstFile(lstFile);
            }

            /* demande d'envoie du mail */
            MailOutDto mailOut = sendMail.sendMail(mailIn);

            return true;


        } catch (FileNotFoundException e) {
            LOGGER.error("FileNotFoundException", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException", e.getMessage());
        }

        return false;
    }


}
