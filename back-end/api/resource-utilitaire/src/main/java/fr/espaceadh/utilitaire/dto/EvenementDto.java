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
package fr.espaceadh.utilitaire.dto;

import java.util.Date;

/**
 *
 * @author emmanuel
 */
public class EvenementDto {
    
    private long idEvenement;
    private String descriptionCourte;
    private String descriptionLongue;
    private String lieux;
    private Date dateDebut;
    private Date dateFin;
    private int idAuthority;
    private boolean envoyerInfoAdherents;
    private boolean demanderConfirmationParticipation;

    public long getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(long idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getDescriptionCourte() {
        return descriptionCourte;
    }

    public void setDescriptionCourte(String descriptionCourte) {
        this.descriptionCourte = descriptionCourte;
    }

    public String getDescriptionLongue() {
        return descriptionLongue;
    }

    public void setDescriptionLongue(String descriptionLongue) {
        this.descriptionLongue = descriptionLongue;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getIdAuthority() {
        return idAuthority;
    }

    public void setIdAuthority(int idAuthority) {
        this.idAuthority = idAuthority;
    }

    public boolean isEnvoyerInfoAdherents() {
        return envoyerInfoAdherents;
    }

    public void setEnvoyerInfoAdherents(boolean envoyerInfoAdherents) {
        this.envoyerInfoAdherents = envoyerInfoAdherents;
    }

    public boolean isDemanderConfirmationParticipation() {
        return demanderConfirmationParticipation;
    }

    public void setDemanderConfirmationParticipation(boolean demanderConfirmationParticipation) {
        this.demanderConfirmationParticipation = demanderConfirmationParticipation;
    }
    

    
}
