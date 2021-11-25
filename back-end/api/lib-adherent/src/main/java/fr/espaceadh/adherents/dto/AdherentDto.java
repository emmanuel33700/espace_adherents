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
package fr.espaceadh.adherents.dto;

import java.util.Date;

/**
 *
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 */
public class AdherentDto {

  private Long id;
  private CiviliteEnum civilite ;
  private String nom ;
  private String prenom ;
  private String adresse1 ;
  private String adresse2 ;
  private String codePostal;
  private String ville;
  private String telMaison;
  private String telTravail;
  private String telPortable ;
  private String email ;
  private String profession ;
  private Date dateNaissance;
  private String lienPhotoProfil ;
  private boolean accordMail ;
  private boolean publicContact;
  private String commentaire ;
  private Date dateEnregistrement ;
  private Date dateMiseAJour; 
  private String tokenAcces ;
  private Long idAdherentUpdate ;
  private boolean adhesionSaisonCourante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CiviliteEnum getCivilite() {
        return civilite;
    }

    public void setCivilite(CiviliteEnum civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getTelMaison() {
        return telMaison;
    }

    public void setTelMaison(String telMaison) {
        this.telMaison = telMaison;
    }

    public String getTelTravail() {
        return telTravail;
    }

    public void setTelTravail(String telTravail) {
        this.telTravail = telTravail;
    }

    public String getTelPortable() {
        return telPortable;
    }

    public void setTelPortable(String telPortable) {
        this.telPortable = telPortable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLienPhotoProfil() {
        return lienPhotoProfil;
    }

    public void setLienPhotoProfil(String lienPhotoProfil) {
        this.lienPhotoProfil = lienPhotoProfil;
    }
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public Date getDateMiseAJour() {
        return dateMiseAJour;
    }

    public void setDateMiseAJour(Date dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public String getTokenAcces() {
        return tokenAcces;
    }

    public void setTokenAcces(String tokenAcces) {
        this.tokenAcces = tokenAcces;
    }

    public Long getIdAdherentUpdate() {
        return idAdherentUpdate;
    }

    public void setIdAdherentUpdate(Long idAdherentUpdate) {
        this.idAdherentUpdate = idAdherentUpdate;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public boolean isAccordMail() {
        return accordMail;
    }

    public void setAccordMail(boolean accordMail) {
        this.accordMail = accordMail;
    }

    public boolean isPublicContact() {
        return publicContact;
    }

    public void setPublicContact(boolean publicContact) {
        this.publicContact = publicContact;
    }

    public boolean isAdhesionSaisonCourante() {
        return adhesionSaisonCourante;
    }

    public void setAdhesionSaisonCourante(boolean adhesionSaisonCourante) {
        this.adhesionSaisonCourante = adhesionSaisonCourante;
    }
  
  
}
