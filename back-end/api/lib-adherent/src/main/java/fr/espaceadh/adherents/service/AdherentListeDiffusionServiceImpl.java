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

import fr.espaceadh.adherents.dao.AdherentListeDiffusionDAO;
import fr.espaceadh.adherents.dto.GroupeDiffusionDto;
import java.util.Collection;
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
public class AdherentListeDiffusionServiceImpl implements AdherentListeDiffusionService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AdherentListeDiffusionServiceImpl.class);
    
    @Autowired
    private Environment env;
    
    @Autowired
    private AdherentListeDiffusionDAO adherentListeDiffusionDAO;



    /**
     * Récupéer la liste complete des mailing liste avec le statut de participation de l'adhérent
     * @param idAdh
     * @return 
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<GroupeDiffusionDto> getListDiffusion(long idAdh) {
        return adherentListeDiffusionDAO.getListDiffusion(idAdh);
    }

    /**
     * Ajouter une participation à un groupe de diffusion
     * @param idAdh
     * @param idGpeDiffusion
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean ajouterParticipationGroupeDiffusion(long idAdh, long idGpeDiffusion) {
        return adherentListeDiffusionDAO.ajouterParticipationGroupeDiffusion(idAdh, idGpeDiffusion);
    }

    /**
     * Supprimer une participation à un groupe de diffusion
     * @param idAdh
     * @param idGpeDiffusion
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean supprimerParticipationGroupeDiffusion(long idAdh, long idGpeDiffusion) {
       return adherentListeDiffusionDAO.supprimerParticipationGroupeDiffusion(idAdh, idGpeDiffusion);
    }
    
    
}
