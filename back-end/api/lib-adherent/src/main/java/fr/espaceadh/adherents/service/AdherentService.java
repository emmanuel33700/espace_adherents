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
import fr.espaceadh.adherents.dto.LienAdherentsDto;
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
     * Recupérer un adhérent avec son id
     * @param idAdh id  de la personne
     * @return adherentDto
     */
    public AdherentDto recupererAdherent (Long idAdh);


    /**
     * Recupérer un adhérent avec son username
     * @param email email de l'adherent
     * @return adherentDto
     */
    public AdherentDto recupererAdherent (String email);

    
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
     * Recupérer la liste des adhérents de la saison avec détail comptable sur l'adhésions de la saison
     * @return
     */
    public Collection<AdhesionDto> recupererListeAdherentAdhesionSaison();

    /**
     * Mise à jour d'un adhérents
     * @param adherentDto
     * @return 
     */
    public boolean updateAdherents (AdherentDto adherentDto);
    
    /**
     * Mise à jour du nom de la photo de profil de l'adhérent
     * @param idAdherent id de l'adhérent
     * @param nomPhoto nom du fichier de la photo
     * @return  
     */
    public boolean updateLienPhotoAdherent (long idAdherent, String nomPhoto);
    
    
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
    
    /**
     * Recherche d'un lien representant <=> representé entre deux adhérent
     * @param idAdherentRepresentant
     * @param idAdherentRepresente
     * @return 
     */
    public LienAdherentsDto getLienAdherent(Long idAdherentRepresentant, Long idAdherentRepresente); 
    
    
    /**
     * recupérer la liste des personnes représenté par un adhérent
     * @param idAdherentRepresentant
     * @return 
     */
    public Collection<LienAdherentsDto> getLiensAdherent(Long idAdherentRepresentant); 
    
    /**
     * Ajouter un lien entre deux adhérents
     * @param idAdherentRepresentant
     * @param idAdherentRepresente
     * @return 
     */
    public boolean ajouterLienAdherent(Long idAdherentRepresentant, Long idAdherentRepresente);
    
    /**
     * Supprimer un lien de représentation entre 2 adhérents
     * @param idAdherentRepresentant
     * @param idAdherentRepresente
     * @return 
     */
    public boolean supprimerLienAdherent(Long idAdherentRepresentant, Long idAdherentRepresente);


    /**
     * Créer et activer un compte
     * @param idAdh
     * @return
     */
    boolean creerEtActiverCompte(long idAdh);
    
}
