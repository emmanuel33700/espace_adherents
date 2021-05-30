/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.authorization.service;

import fr.espaceadh.authorization.dto.AuthoritiesDto;
import fr.espaceadh.authorization.dto.RolesEnum;
import fr.espaceadh.authorization.dto.UserDto;
import java.util.List;

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
    
    /**
     * Validation du compte d'un utilisateur
     * @param idUser id de la personne
     * @param cleeValidation clée de validation
     * @return true: compte validé
     *          false : compte non validé
     */
    boolean validationCreationUser(int idUser, String cleeValidation);
    
    /**
     * Modifier les roles d'un utilisateur 
     * @param idUser
     * @param roles
     * @return 
     */
    boolean modifierRolesUtilisateur (int idUser, List<RolesEnum> rolesEnum );
    
    /**
     * Récupérer les authorities pour un id utilisateur
     * @param idUser
     * @return 
     */
    AuthoritiesDto recupererAuthorities (int idUser);
    
    /**
     * Modifier le username d'un utilisateur
     * @param idUser
     * @param username
     * @return 
     */
    boolean modifierInformationUtilisateur(int idUser, String username);
    
    
    /**
     * Activier un compte utilisateur
     * @param idUser
     * @return 
     */
    boolean activerAuthentification(int idUser);
    
    /**
     * désactiver un compte utilisateur
     * @param idUser
     * @return 
     */
    boolean desactiverAuthentification(int idUser);
    
}
