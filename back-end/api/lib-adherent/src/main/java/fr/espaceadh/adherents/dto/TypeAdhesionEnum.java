/*
 * Copyright (C) 2020 emmanuel
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

/**
 ** Type d'adhesion
 * @author emmanuel
 */
public enum TypeAdhesionEnum {
    ADULTE(1),
    FAMILLE(2),
    RESPONSABLE_DE_FAMILLE(3),
    ENFANT(4),
    BIENFAITEUR(5),
    HONNEUR(6),
    ETUDIANT(7),
    DEMANDEUR_EMPLOI(8);
    
    private int typeAdhesion;
    
    TypeAdhesionEnum(int typeAdh){
        this.typeAdhesion = typeAdh;
    }
    
    public int toInt() {
        return this.typeAdhesion;
    }
    
}
