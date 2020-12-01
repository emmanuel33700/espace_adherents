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
import fr.espaceadh.lib.mail.GestionMail;
import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;
import fr.espaceadh.lib.mail.dto.TemplateMailEnum;
import java.util.ArrayList;
import java.util.Collection;
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
        
        //envoie du mail de validation du compte
        MailInDto mailIn = new MailInDto();

        
        Collection<String> messageTo = new ArrayList<>();
        messageTo.add(adherentDto.getEmail());
        mailIn.setMessageTo(messageTo);
        
        
        /* type de template */
        mailIn.setTemplateMailEnum(TemplateMailEnum.INFORMATION_PRE_INSCRIPTION);
        
        /* variables associées au tempalte **/
        HashMap<String, String> templateVariables = new HashMap<>();
        templateVariables.put("adh_prenom", adherentDto.getPrenom());
        templateVariables.put("confirmation_link", 
                env.getProperty("validationmail.url")
                        .concat("?mail=").concat(adherentDto.getEmail())
                        .concat("&id=").concat(Long.toString(idAdherent))
        );
        mailIn.setTemplateVariables(templateVariables);
        
        /** Sujet du mail **/
        mailIn.setSujetMail("Votre préinscription");
        
        MailOutDto mailOut = getionMail.sendMail(mailIn);
        
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
        return this.adherentsDAO.updateAdherents(adherentDto);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean creerAdhesion(AdhesionDto adhesionDto) {
        return this.adherentsDAO.creerAdhesion(adhesionDto);
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
    
    
}
