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

import fr.espaceadh.adherents.dto.AdherentEvenementDto;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author emmanuel
 */
public interface AdherentEvenementsDAO {
    



    /**
     * Recupérer les évenmenets pour un adhérents en fonction des ces droits
     * @param typeAutority
     * @param idAdh
     * @param dateDebut
     * @param dateFin
     * @param demandeConfirmationParticipation
     * @return 
     */
    public Collection<AdherentEvenementDto> getLstEvenement(final int typeAutority, final long idAdh, final Date dateDebut, final Date dateFin, final boolean demandeConfirmationParticipation);
    
    /**
     * recupérer un évènement pour un adhérent
     * @param typeAutority
     * @param idAdh
     * @param idEvenement
     * @return 
     */
     public AdherentEvenementDto evenement(final int typeAutority, final long idAdh, final long idEvenement);
     
         /**
     * Supression des participations à un évènement
     * @param idEvenement id de l'évènement à supprimer
     * @return  true si l'évènement est supprimé 
     */
    public boolean deleteParticipationEvenement(long idEvenement, long idAdherent);
    
    /**
     * Création d'une participation à un évènement
     * @param idEvenement
     * @param idAdherent
     * @param participeEvenement 
     * @return 
     */
    public boolean creationParticipationEvenement(long idEvenement, long idAdherent, boolean participeEvenement);
    
}
