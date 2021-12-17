package fr.espaceadh.utilitaire.dto;

import fr.espaceadh.adherents.dto.AdherentDto;

public class AdherentMailingListeDto extends AdherentDto {

    private long idMailingListe;
    private boolean inscriptionMailingList;


    public long getIdMailingListe() {
        return idMailingListe;
    }

    public void setIdMailingListe(long idMailingListe) {
        this.idMailingListe = idMailingListe;
    }

    public boolean isInscriptionMailingList() {
        return inscriptionMailingList;
    }

    public void setInscriptionMailingList(boolean inscriptionMailingList) {
        this.inscriptionMailingList = inscriptionMailingList;
    }
}
