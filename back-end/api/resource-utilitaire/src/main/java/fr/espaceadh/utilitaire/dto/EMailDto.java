package fr.espaceadh.utilitaire.dto;

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.lib.mail.dto.InputStreamCustom;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Collection;

public class EMailDto {
    private String messageHtml;
    private String sujet;
    private Collection<InputStreamCustom> lstFile;
    private long idMail;

    private String mailReplyTo;

    private String auteurName;

    Collection<AdherentDto> lstAdherents;

    public String getMessageHtml() {
        return messageHtml;
    }

    public void setMessageHtml(String messageHtml) {
        this.messageHtml = messageHtml;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Collection<InputStreamCustom> getLstFile() {
        return lstFile;
    }

    public void setLstFile(Collection<InputStreamCustom> lstFile) {
        this.lstFile = lstFile;
    }

    public long getIdMail() {
        return idMail;
    }

    public void setIdMail(long idMail) {
        this.idMail = idMail;
    }

    public String getAuteurName() {
        return auteurName;
    }

    public void setAuteurName(String auteurName) {
        this.auteurName = auteurName;
    }

    public Collection<AdherentDto> getLstAdherents() {
        return lstAdherents;
    }

    public void setLstAdherents(Collection<AdherentDto> lstAdherents) {
        this.lstAdherents = lstAdherents;
    }

    public String getMailReplyTo() {
        return mailReplyTo;
    }

    public void setMailReplyTo(String mailReplyTo) {
        this.mailReplyTo = mailReplyTo;
    }
}
