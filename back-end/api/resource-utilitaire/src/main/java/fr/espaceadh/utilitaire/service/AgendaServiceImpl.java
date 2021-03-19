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
import fr.espaceadh.utilitaire.dao.AgendaDao;
import fr.espaceadh.utilitaire.dto.EvenementDto;
import java.util.Collection;
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
            //TODO envoyer un email au adhérent
        } 
        return resultCreation;
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
        return this.agendaDao.updateEvenement(evenement);
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
    
}
