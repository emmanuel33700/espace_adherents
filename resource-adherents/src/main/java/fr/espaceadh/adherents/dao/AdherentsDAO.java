/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.adherents.dao;

import fr.espaceadh.adherents.model.Adherent;

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
    public Adherent getAdherentByLogin(final String login);
    
    /**
     * rechercher un adh avec son id
     * @param idAdh
     * @return 
     */
    public Adherent getAdherentByID(final long idAdh);
    
}
