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
package fr.espaceadh.adherents.dao;

import fr.espaceadh.adherents.dto.GroupeDiffusionDto;
import java.util.Collection;

/**
 *
 * @author emmanuel
 */
public interface AdherentListeDiffusionDAO {
    
    /**
     * Récupéer la liste complete des mailing liste avec le statut de participation de l'adhérent
     * @param idAdh
     * @return 
     */
    public Collection<GroupeDiffusionDto> getListDiffusion(long idAdh);
    
    /**
     * Ajouter une participation à un groupe de diffusion
     * @param idAdh
     * @param idGpeDiffusion
     * @return 
     */
    public boolean ajouterParticipationGroupeDiffusion(long idAdh, long idGpeDiffusion);
    
    /**
     * Supprimer une participation à un groupe de diffusion
     * @param idAdh
     * @param idGpeDiffusion
     * @return 
     */
    public boolean supprimerParticipationGroupeDiffusion(long idAdh, long idGpeDiffusion);
}
