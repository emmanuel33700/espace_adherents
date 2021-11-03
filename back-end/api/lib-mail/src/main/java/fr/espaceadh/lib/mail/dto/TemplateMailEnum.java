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

/**
 *
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 */
public enum TemplateMailEnum {
    SANS_TEMPLATE("SANS_TEMPLATE"),
    DEMANDE_VALIDATION_MAIL("DEMANDE_VALIDATION_MAIL"),
    CONFIRMATION_ADHESION("CONFIRMATION_ADHESION"),
    INFORMATION_PRE_INSCRIPTION("CONFIRMATION_ADHESION"),
    INFORMATION_EVENEMENT_AJOUTE("INFORMATION_EVENEMENT_AJOUTE"),
    DEMANDE_REINIT_MOT_DE_PASSE("DEMANDE_REINIT_MOT_DE_PASSE");
    
    
    private String template = "";
    
        //Constructeur
    TemplateMailEnum(String template) {
        this.template = template;
    }

    public String toString() {
        return template;
    }
}
