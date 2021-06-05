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

import fr.espaceadh.authorization.dto.UserDto;

/**
 *
 * @author emmanuel
 */
public interface userDao {
    
    /**
     * Creation d'un utilisateur dans la base des authorisation
     * @param userDto
     * @return si true : utilisateur créé
     *          si false : utilisateur non créé
     */
    boolean creationUser(UserDto userDto);
    
    /**
     * Mise à jour d'un utilisateur dans la base des authorisation
     *      Attention, la date de création, l'idAdherent ne sont jamais mis à jour
     * @param userDto
     * @return boolean si true : utilisateur mis à jour
     *                  si false : utilsateur non mis à jour
     */
    boolean majUser(UserDto userDto);
    
    /**
     * Lecture d'un utilsateur via son logn
     * @param login login de l'utilisateur 
     * @return UserDto
     */
    UserDto lectureUtilisateur(String login);
    
    /**
     * Lecture d'un utilsateur via son id
     * @param idUser
     * @return 
     */
    UserDto lectureUtilisateur(int idUser);
    
    /**
     * Validation de la création d'un utilisateur via sa clée de validation
     * @param idUser id de l'utilisateur
     * @param cleeValidation clée de validation
     * @return true si validation OK
     */
    boolean validationUtilisateur (int idUser, String cleeValidation);
    
    /**
     * Changer le statut d'acces à un compte utilisateur
     * @param idUser
     * @param statutActivation true => COmpte activé ; false => compte désactivé
     * @return 
     */
    boolean changerValidationUtilisateur (int idUser, boolean statutActivation);
    
    /**
     * Modifier le username d'un utilisateur
     * @param idUser
     * @param username
     * @return 
     */
    boolean modifierUserNameUtilisateur (int idUser, String username);
    
        /**
     * Désactiver l'ensemble des authentification à l'exeption des personnes qui ont le role ADMIN
     * @return 
     */
    boolean desactiverEnsembleAuthentification();
    
}
