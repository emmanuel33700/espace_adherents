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

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.dto.AdhesionDto;
import java.util.Collection;

/**
 *
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 */
public interface AdherentService {
    
    /**
     * Création d'un adherent
     * @param adherentDto adherents DTO
     * @return  0 => Création OK
     *          1 => Creation erreur
     *          10 => Adherent existant déjà
     */
    public int creerAdherent(AdherentDto adherentDto);
    
    /**
     * Recupérer un adhérent avec son username
     * @param idAdh id  de la personne
     * @return adherentDto
     */
    public AdherentDto recupererAdherent (Long idAdh);
    
    /**
     * récupérer la liste complete des adhérents
     * @return 
     */
    public Collection<AdherentDto> recupererListeCompletAdherent ();
    
    
    /**
     * Récupérer la liste des adhérents de la saison
     * @return 
     */
    public Collection<AdherentDto> recupererListeAdherentSaison ();
    
    

    /**
     * Mise à jour d'un adhérents
     * @param adherentDto
     * @return 
     */
    public boolean updateAdherents (AdherentDto adherentDto);
    
    
    /**
     * Création d'une adhésion pour un adhérent
     * @param adhesionDto
     * @return 
     */
    public boolean creerAdhesion(AdhesionDto adhesionDto);
    
    /**
     * Rechercher l'ensemble des adhésions pour un adherent
     * @param idAdh id de l'adherent
     * @return liste des adhésions de l'adherent
     */
    public Collection<AdhesionDto> getAdhesionsPourUnAdherent(Long idAdh);
    
    /**
     * Récupérer l'adhésion d'un année pour un adhérent
     * @param idAdh id de l'adherent
     * @param idAnneAdhesion id de l'année 
     * @return  adhésion
     */
    public AdhesionDto getAdhesionPourUnAdherent(Long idAdh, Long idAnneAdhesion);
    
    /**
     * Mise à jour d'une adhésion pour un adhérent
     * @param adhesionDto
     * @return 
     */
    public boolean majAdhesion(AdhesionDto adhesionDto);
    
    /**
     * Supprimer une adhésion pour un adhérent
     * @param idAdh id de l'adherent
     * @param idAnneAdhesion id de l'année 
     * @return 
     */
    public boolean suppAdhesion(Long idAdh, Long idAnneAdhesion);
    
}
