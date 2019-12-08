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
package fr.espaceadh.authorization.dto;

import java.util.Date;

/**
 *
 * @author emmanuel
 */
public class UserDto {
    
    private String username;
    private String passwordEncode;
    private boolean enabled;
    private int idAdherent;
    private Date dateCreation;
    private Date dateModif;
    private Date dateConnexion;
    private String cleeModification;
    private Date dateModifcationClee;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordEncode() {
        return passwordEncode;
    }

    public void setPasswordEncode(String passwordEncode) {
        this.passwordEncode = passwordEncode;
    }



    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
    }


    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public Date getDateConnexion() {
        return dateConnexion;
    }

    public void setDateConnexion(Date dateConnexion) {
        this.dateConnexion = dateConnexion;
    }

    public String getCleeModification() {
        return cleeModification;
    }

    public void setCleeModification(String cleeModification) {
        this.cleeModification = cleeModification;
    }

    public Date getDateModifcationClee() {
        return dateModifcationClee;
    }

    public void setDateModifcationClee(Date dateModifcationClee) {
        this.dateModifcationClee = dateModifcationClee;
    }


    
    

    
    
}
