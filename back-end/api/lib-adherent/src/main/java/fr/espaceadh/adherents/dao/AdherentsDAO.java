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
package fr.espaceadh.adherents.dao;

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.dto.AdhesionDto;
import fr.espaceadh.adherents.dto.LienAdherentsDto;
import java.util.Collection;

/**
 *
 * @author emmanuel
 */
public interface AdherentsDAO {
    
    /**
     * Récupérer l'adhérent via son login
     * @param login
     * @return 
     */
    public AdherentDto getAdherentByLogin(final String login);
    
    /**
     * rechercher un adh avec son id
     * @param idAdh
     * @return 
     */
    public AdherentDto getAdherentByID(final long idAdh);
    
    /**
     * Création d'un adhérent
     * @param adherentDto adherents DTO
     * @return  id de l'adherent créé
     */
    public long creerAdherent(final AdherentDto adherentDto);
    
    
    /**
     * Recupérer la liste de l'ensemble des adhérents de la BD
     * @return collection d'AdherentDto
     */
    public Collection<AdherentDto> recupererListeCompletAdherent();
    
    /**
     * Recupérer la liste des adhérents de la saison
     * @return collection d'AdherentDto
     */
    public Collection<AdherentDto> recupererListeAdherentSaison();

    /**
     * Recupérer la liste des adhérents de la saison avec détail comptable sur l'adhésions de la saison
     */
    public Collection<AdhesionDto> recupererListeAdherentAdhesionSaison();
    
    /**
     * Mise à jour d'un adhérent
     * @param adherentDto
     * @return 
     */
    public boolean updateAdherents(final AdherentDto adherentDto);
    
    /**
     * Création d'une adhésion pour un adhérent
     * @param adhesionDto
     * @return 
     */
    public boolean creerAdhesion(final AdhesionDto adhesionDto);
    
    /**
     * Rechercher l'ensemble des adhésions pour un adherent
     * @param idAdh id de l'adherent
     * @return liste des adhésions de l'adherent
     */
    public Collection<AdhesionDto> getAdhesionsPourUnAdherent(final long idAdh);
    
    /**
     * Récupérer l'adhésion d'un année pour un adhérent
     * @param idAdh id de l'adherent
     * @param idAnneAdhesion id de l'année 
     * @return  adhésion
     */
    public AdhesionDto getAdhesionPourUnAdherent(final long idAdh, final long idAnneAdhesion);
    
    /**
     * Mise à jour d'une adhésion pour un adhérent
     * @param adhesionDto
     * @return 
     */
    public boolean majAdhesion(final AdhesionDto adhesionDto);
    
    /**
     * Supprimer une adhésion pour un adhérent
     * @param idAdh id de l'adherent
     * @param idAnneAdhesion id de l'année 
     * @return 
     */
    public boolean suppAdhesion(final long idAdh, final long idAnneAdhesion);    
    
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
     * Récupérer la liste des adhérents de la saison courant et saison précédente
     * @return
     */
    public Collection<AdherentDto> recupererListeAdherentSaisonEtAncienneSaison();


    /**
     * Récupérer les adhérents qui n'ont pas renouvelles leur adhésion
     * @return
     */
    public Collection<AdherentDto> recupererListeNonAdherentSaison();
}
