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
package fr.espaceadh.adherents.config;

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.dto.LienAdherentsDto;
import fr.espaceadh.adherents.service.AdherentService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 */
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMethodSecurityExpressionRoot.class);  

    protected AdherentService adherentService;
    
    private Object filterObject;
    private Object returnObject;
    private Object target;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }


    public boolean isProprietraireDonnee(Long idAdh) {
       
        Object principal = this.getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        LOGGER.info("username {}", username);
        
        
        AdherentDto adh = adherentService.recupererAdherent(idAdh);
        
        if (adh != null){
            boolean estAutorisie = username.equalsIgnoreCase(adh.getEmail());
            if (!estAutorisie) LOGGER.warn("Tentative de piratage sur idAdh {} avec le login {}", idAdh, username);
            return estAutorisie;
        }
         return false;
        
    }
    
    /**
     * Vérification si une personne est propriétaire d'une donnée ou peut agir sur la donnée d'un autre personne
     * @param idAdh
     * @return 
     */
    public boolean isProprietraireOuPeutAgirSurDonnee(Long idAdh) {
        
        
        /**
         * Vérifier sur c'est le propriétaire de la donnée
         */
        if (this.isProprietraireDonnee(idAdh)){
            return true;
        }

        
        /**
         * Sinon vérifie si il existe un liend qui donne le doit d'agir sur la donnée
         */
        else if (this.peutAgirSurDonnee(idAdh)){
            return true;
        }
        
        else  {
            return false;
        }

        
    }
    
    /**
     * Vérifier si une personne peut agir sur une donnée d'une autre personne
     * @param idAdh
     * @return 
     */
    private boolean peutAgirSurDonnee(Long idAdh) {
        Object principal = this.getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        LOGGER.info("username {}", username);
        
        
        AdherentDto adhRepresentant = adherentService.recupererAdherent(username); 
        
        
        LienAdherentsDto lienADh = adherentService.getLienAdherent(adhRepresentant.getId(), idAdh);
        if (lienADh != null && lienADh.getIdAdhRepresentant() == adhRepresentant.getId() && lienADh.getIdAdhRepresente() == idAdh) {
            return true;
        }
        
        return false;
    }
    

    public boolean isDansGroupe(String permission) {
       
       Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) this.getAuthentication().getAuthorities();
       
       for(GrantedAuthority authoritie : authorities) {
           LOGGER.debug("authoritie {}", authoritie.toString());
            if (authoritie.getAuthority().contains(permission)) {
                return true;
            }
       }   
        return false;   
    }


    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }
    
    
    void setThis(Object target) {
        this.target = target;
    }

    
    public void setAdherentService(AdherentService adherentService) {
        this.adherentService = adherentService;
    }


    
}
