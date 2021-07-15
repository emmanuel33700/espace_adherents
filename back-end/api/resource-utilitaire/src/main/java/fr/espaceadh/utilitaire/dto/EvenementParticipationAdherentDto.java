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

/**
 *
 * @author emmanuel
 */
public class EvenementParticipationAdherentDto extends AdherentDto{
    private int typeParticipation;

    /**
     * 1 => Participe; 2 => Participe pas; 3 => Ne sais pas
     * @return 
     */
    public int getTypeParticipation() {
        return typeParticipation;
    }

    /**
     *  1 => Participe; 2 => Participe pas; 3 => Ne sais pas
     * @param typePArtiipation 
     */
    public void setTypeParticipation(int typeParticipation) {
        this.typeParticipation = typeParticipation;
    }
    
    
    
}
