package fr.espaceadh.utilitaire.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public class MailListeDiffusionDto {
    private long idListeDiffusion;
    private String messageHtml;
    private String sujet;
    private Collection<MultipartFile> lstFile;


    public long getIdListeDiffusion() {
        return idListeDiffusion;
    }

    public void setIdListeDiffusion(long idListeDiffusion) {
        this.idListeDiffusion = idListeDiffusion;
    }

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

    public Collection<MultipartFile> getLstFile() {
        return lstFile;
    }

    public void setLstFile(Collection<MultipartFile> lstFile) {
        this.lstFile = lstFile;
    }
}
