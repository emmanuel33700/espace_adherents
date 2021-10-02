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
package fr.espaceadh.lib.mail.dto;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author emmanuel
 */
public class MailInDto {

    private TemplateMailEnum templateMailEnum;
    private Collection<String> messageTo;
    HashMap<String, String> templateVariables;
    private String sujetMail;
    private String htmlMessage;
    private Collection<InputStreamCustom> lstFile;
    private String idMAil;

    public Collection<String> getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(Collection<String> messageTo) {
        this.messageTo = messageTo;
    }

    public TemplateMailEnum getTemplateMailEnum() {
        return templateMailEnum;
    }

    public void setTemplateMailEnum(TemplateMailEnum templateMailEnum) {
        this.templateMailEnum = templateMailEnum;
    }

    public HashMap<String, String> getTemplateVariables() {
        return templateVariables;
    }

    public void setTemplateVariables(HashMap<String, String> templateVariables) {
        this.templateVariables = templateVariables;
    }

    public String getSujetMail() {
        return sujetMail;
    }

    public void setSujetMail(String sujetMail) {
        this.sujetMail = sujetMail;
    }

    public String getHtmlMessage() {
        return htmlMessage;
    }

    public void setHtmlMessage(String htmlMessage) {
        this.htmlMessage = htmlMessage;
    }

    public Collection<InputStreamCustom> getLstFile() {
        return lstFile;
    }

    public void setLstFile(Collection<InputStreamCustom> lstFile) {
        this.lstFile = lstFile;
    }

    public String getIdMAil() {
        return idMAil;
    }

    public void setIdMAil(String idMAil) {
        this.idMAil = idMAil;
    }
}
