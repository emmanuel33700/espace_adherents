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
package fr.espaceadh.utilitaire.service;

import fr.espaceadh.utilitaire.dto.EvenementDto;
import fr.espaceadh.utilitaire.dto.EvenementSyntheseDto;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author emmanuel
 */
public interface AgendaService {
    
    
    /**
     * Crééer un évènement
     * @param evenement
     * @param envoyerMailInfo indique si il faut envoyer un mail d'information au adhérent
     * @return  true si évènement est créé
     */
    public boolean creerEvenement(EvenementDto evenement, boolean  envoyerMailInfo);
    
    /**
     * Recuperer la liste des évènement en fonction du type d'autority
     * @param typeAutority
     * @return liste d'évènement
     */
    public Collection<EvenementDto> getLstEvenement(int typeAutority);
    
    /**
     * Mise à jour d'un évènement
     * @param evenement
     * @return  true si l'évènement est modifié
     */
    public boolean updateEvenement(EvenementDto evenement);
    

    /**
     * Supression d'un évènement
     * @param idEvenement id de l'évènement à supprimer
     * @return true si l'évènement est supprimé 
     */
    public boolean deleteEvenement(long idEvenement);
    
    
        /**
     * Récupérer la synthèse des participations aux manifestations
     * @param dateDebut date min de démarage de la conférence
     * @param dateFin date max de démarage de la conférence
     * @param demandeConfirParticipation indique si une confirmation de la participation est nécésaire
     * @return 
     */
    public Collection<EvenementSyntheseDto> recupererSyntheseParticipation(final Date dateDebut, final Date dateFin, final boolean demandeConfirParticipation);

}
