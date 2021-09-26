package fr.espaceadh.lib.mail.dto;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamCustom {

    private InputStream inputStream;
    private String fileName;
    private String contentType;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
