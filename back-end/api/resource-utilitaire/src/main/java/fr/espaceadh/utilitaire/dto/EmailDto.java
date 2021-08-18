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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author emmanuel
 */
public class EmailDto {
   private String titreMail;
   private String mail;
   private Collection<MultipartFile> lstFichier;
   
   /**
    * 1 : ANNEE COURANTE               
    * 2 : ANNEE COURANTE + ANNEE -1           
    * 3 : MAILING LISTE
    */
   private int typeEnvoi;
   
   /**
    * Si type d'envoie == 3 alors utilisation de l'idMailing liste
    */
   private long idMailingListe;

    public String getTitreMail() {
        return titreMail;
    }

    public void setTitreMail(String titreMail) {
        this.titreMail = titreMail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Collection<MultipartFile> getLstFichier() {
        return lstFichier;
    }

    public void setLstFichier(Collection<MultipartFile> lstFichier) {
        this.lstFichier = lstFichier;
    }

    public int getTypeEnvoi() {
        return typeEnvoi;
    }

    public void setTypeEnvoi(int typeEnvoi) {
        this.typeEnvoi = typeEnvoi;
    }

    public long getIdMailingListe() {
        return idMailingListe;
    }

    public void setIdMailingListe(long idMailingListe) {
        this.idMailingListe = idMailingListe;
    }
   
   
   
}
