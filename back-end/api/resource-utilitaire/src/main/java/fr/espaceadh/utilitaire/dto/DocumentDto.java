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
package fr.espaceadh.utilitaire.dto;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author emmanuel
 */
public class DocumentDto {

    private long idDocument;
    private long idDocumentParent;
    private String lablelCourt;
    private String labelLong;
    private Date dateDeCreation;
    private boolean dossier;
    private boolean fichier;
    private String nonFichier;
    private int idAuthority;
    private long idAuteur;

    private int niveau;
    private boolean aEnfant;
    private Collection<DocumentDto> documentsFils;

    public long getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(long idDocument) {
        this.idDocument = idDocument;
    }

    public long getIdDocumentParent() {
        return idDocumentParent;
    }

    public void setIdDocumentParent(long idDocumentParent) {
        this.idDocumentParent = idDocumentParent;
    }

    public String getLablelCourt() {
        return lablelCourt;
    }

    public void setLablelCourt(String lablelCourt) {
        this.lablelCourt = lablelCourt;
    }

    public String getLabelLong() {
        return labelLong;
    }

    public void setLabelLong(String labelLong) {
        this.labelLong = labelLong;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public boolean isDossier() {
        return dossier;
    }

    public void setDossier(boolean dossier) {
        this.dossier = dossier;
    }

    public boolean isFichier() {
        return fichier;
    }

    public void setFichier(boolean fichier) {
        this.fichier = fichier;
    }

    public String getNonFichier() {
        return nonFichier;
    }

    public void setNonFichier(String nonFichier) {
        this.nonFichier = nonFichier;
    }

    public int getIdAuthority() {
        return idAuthority;
    }

    public void setIdAuthority(int idAuthority) {
        this.idAuthority = idAuthority;
    }

    public long getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(long idAuteur) {
        this.idAuteur = idAuteur;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public boolean isaEnfant() {
        return aEnfant;
    }

    public void setaEnfant(boolean aEnfant) {
        this.aEnfant = aEnfant;
    }

    public Collection<DocumentDto> getDocumentsFils() {
        return documentsFils;
    }

    public void setDocumentsFils(Collection<DocumentDto> documentsFils) {
        this.documentsFils = documentsFils;
    }

 
    
    
}
