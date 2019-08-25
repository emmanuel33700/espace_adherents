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
    public long creerAdherent(AdherentDto adherentDto);
    
}
