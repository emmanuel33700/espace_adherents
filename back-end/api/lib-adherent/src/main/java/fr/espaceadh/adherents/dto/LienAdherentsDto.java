/*
 * Copyright (C) 2021 emmanuel
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
package fr.espaceadh.adherents.dto;

/**
 *
 * @author emmanuel
 */
public class LienAdherentsDto {

    private Long idAdhRepresentant;
    private Long idAdhRepresente;
    private String nomAdhRepresente;
    private String prenomAdhRepresente;
    private String emailAdhRepresente;

    public Long getIdAdhRepresentant() {
        return idAdhRepresentant;
    }

    public void setIdAdhRepresentant(Long idAdhRepresentant) {
        this.idAdhRepresentant = idAdhRepresentant;
    }

    public Long getIdAdhRepresente() {
        return idAdhRepresente;
    }

    public void setIdAdhRepresente(Long idAdhRepresente) {
        this.idAdhRepresente = idAdhRepresente;
    }

    public String getNomAdhRepresente() {
        return nomAdhRepresente;
    }

    public void setNomAdhRepresente(String nomAdhRepresente) {
        this.nomAdhRepresente = nomAdhRepresente;
    }

    public String getPrenomAdhRepresente() {
        return prenomAdhRepresente;
    }

    public void setPrenomAdhRepresente(String prenomAdhRepresente) {
        this.prenomAdhRepresente = prenomAdhRepresente;
    }

    public String getEmailAdhRepresente() {
        return emailAdhRepresente;
    }

    public void setEmailAdhRepresente(String emailAdhRepresente) {
        this.emailAdhRepresente = emailAdhRepresente;
    }
    
    
    
    
}
