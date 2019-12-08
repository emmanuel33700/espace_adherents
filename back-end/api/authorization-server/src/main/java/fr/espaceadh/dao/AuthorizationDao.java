/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.dao;

import fr.espaceadh.dto.Authorization;

/**
 *
 * @author emmanuel
 */
public interface AuthorizationDao {
    

    /**
     * 
     * @param login
     * @return 
     */
    public  Authorization getAuthorisation(String login);
    
}
