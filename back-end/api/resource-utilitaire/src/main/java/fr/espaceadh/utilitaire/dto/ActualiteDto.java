package fr.espaceadh.utilitaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ActualiteDto {

    private int id;
    private String libelleActualite ;
    private Date dateCreation ;
    private long idAdherent ;
    private int idAuthority;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelleActualite() {
        return libelleActualite;
    }

    public void setLibelleActualite(String libelleActualite) {
        this.libelleActualite = libelleActualite;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public long getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(long idAdherent) {
        this.idAdherent = idAdherent;
    }

    public int getIdAuthority() {
        return idAuthority;
    }

    public void setIdAuthority(int idAuthority) {
        this.idAuthority = idAuthority;
    }
}
