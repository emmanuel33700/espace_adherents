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

import fr.espaceadh.adherents.dto.AdherentEvenementDto;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author emmanuel
 */
public interface AdherentEvenementsService {
   
    /**
     * Recupérer les évenmenets pour un adhérents en fonction des ces droits des une plage de recherche sur date
     * @param idAdh id de l'adhérent
     * @param dateDebut date de début
     * @param datefin date de fin
     * @return 
     */
    public Collection<AdherentEvenementDto> getLstEvenement(final long idAdh, final Date dateDebut, final Date datefin);
    
    /**
     * Recupérer les évenmenets pour un adhérents en fonction des ces droits
     * @param idAdh
     * @return 
     */
    public Collection<AdherentEvenementDto> getLstEvenement(final long idAdh);
}
