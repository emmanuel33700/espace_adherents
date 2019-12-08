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
package fr.espaceadh.authorization.dto;

/**
 *
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 */
public enum RolesEnum {
    ADHERENT("ADHERENT"),
    CONSEIL("CONSEIL"),
    BUREAU("BUREAU"),
    ADMIN("ADMIN");

    private String role = "";

    //Constructeur
    RolesEnum(String role) {
        this.role = role;
    }

    public String toString() {
        return role;
    }
}
