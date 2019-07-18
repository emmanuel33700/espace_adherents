/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.authorization.service;

import fr.espaceadh.authorization.dto.UserDto;

/**
 *
 * @author emmanuel
 */
public interface AuthentificationService {
    
    /**
     * Création d'un utilisateur 
     * @param usersDto renseigner le login et le mot de passe
     * @return  0 : utilisateur crée
     *          1 : utilisateur existe déjà en BD
     *          2 : utilisateur non présent dans l'espace adhérent
     *          99 : erreur générique 
     */
    int creerUser(UserDto usersDto);
    
}
