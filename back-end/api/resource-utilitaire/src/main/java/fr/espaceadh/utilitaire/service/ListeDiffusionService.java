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

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.utilitaire.dto.AdherentMailingListeDto;
import fr.espaceadh.utilitaire.dto.GroupeDiffusionDto;
import fr.espaceadh.utilitaire.dto.EMailDto;

import java.util.Collection;

/**
 *
 * @author emmanuel
 */
public interface ListeDiffusionService {
    
        /**
     * Ajouter une liste de diffusion
     * @param groupeDiffusionDto
     * @return 
     */
    public boolean addListeDiffusion(GroupeDiffusionDto groupeDiffusionDto);
    
    /**
     * Mise à jour d'une liste de diffusion
     * @param groupeDiffusionDto
     * @return 
     */
    public boolean updateListeDiffusion(GroupeDiffusionDto groupeDiffusionDto);
    
    /**
     * Suppression d'une liste de diffusion
     * @param idGroupeDiffusion
     * @return 
     */
    public boolean deleteListeDiffusion(long idGroupeDiffusion);
    
    
    /**
     * Récupérer la liste complete des listes de diffusion
     * @return 
     */
    public Collection<GroupeDiffusionDto> getListeListeDiffusion();


    /**
     * Envoyer un mail à une liste de diffusion
     * @param eMailDto
     * @param idMailingListe
     * @return
     */
    public boolean envoyerMailListeDiffusion(EMailDto eMailDto, long idMailingListe);


    /**
     * Envoyer un mail à une liste d'adherent
     * @param eMailDto
     * @param typeEnvoi  1 : ANNEE COURANTE
     *      * 2 : ANNEE COURANTE + ANNEE -1
     *      * 4 : NON ADHERENT
     * @return
     */
    public boolean envoyerMailListeAdherent(EMailDto eMailDto, int typeEnvoi);

    /**
     * recherche la liste des adhérents avec le statut d'inscription à la mailing liste
     * @param idListeDiffusion
     * @return
     */
    public Collection<AdherentMailingListeDto> getListeAdherentsListDiffusion(long idListeDiffusion);
}
