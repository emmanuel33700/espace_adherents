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
package fr.espaceadh.authorization.dao;

import fr.espaceadh.authorization.dto.AuthoritiesDto;

/**
 *
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 */
public interface AuthoritiesDao {
    
    /**
     * Création de autorites pour un utilisatgeur
     * @param authoritiesDto authoritiesDto
     * @return true : autorites créée
     *          false : autorites non créée
     */
    boolean creationAutorities(AuthoritiesDto authoritiesDto);

    
    /**
     * Suppression des autorities
     * @param username
     * @return 
     */
    boolean suppressionAutorities(String username);
    
    
    /**
     * Recuperer les autorities d'un utilisateur
     * @param username
     * @return 
     */
    AuthoritiesDto recupererAutorities (String username);
    
    

    /**
     * Modifier le username d'un utilisateur
     * @param usernameOld
     * @param usernameNew
     * @return 
     */
    boolean modifierUserNameUtilisateur (String usernameOld, String usernameNew);
    
}
