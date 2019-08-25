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
     */
    int creerAdherent(AdherentDto adherentDto);
    
    /**
     * Recupérer un adhérent avec son username
     * @param idAdh id  de la personne
     * @return adherentDto
     */
    AdherentDto recupererAdherent (Long idAdh);
    
}
