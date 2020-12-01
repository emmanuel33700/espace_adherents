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
package fr.espaceadh.adherents.dto;

/**
 *
 * @author emmanuel
 */
public class AdhesionDto {

    private Long id = null;
    private Long idAdherent = null;
    private Long idAnneeAdhesion = null;
    private TypeAdhesionEnum idTypeAdhesion = null;
    private Long comptaSomme = null;
    private String comptaBanque = null;
    private String comptaNumCheque = null;
    private boolean cheque ;
    private boolean espace ;
    private boolean carteAdhesion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(Long idAdherent) {
        this.idAdherent = idAdherent;
    }

    public Long getIdAnneeAdhesion() {
        return idAnneeAdhesion;
    }

    public void setIdAnneeAdhesion(Long idAnneeAdhesion) {
        this.idAnneeAdhesion = idAnneeAdhesion;
    }

    public TypeAdhesionEnum getIdTypeAdhesion() {
        return idTypeAdhesion;
    }

    public void setIdTypeAdhesion(TypeAdhesionEnum idTypeAdhesion) {
        this.idTypeAdhesion = idTypeAdhesion;
    }

    public Long getComptaSomme() {
        return comptaSomme;
    }

    public void setComptaSomme(Long comptaSomme) {
        this.comptaSomme = comptaSomme;
    }

    public String getComptaBanque() {
        return comptaBanque;
    }

    public void setComptaBanque(String comptaBanque) {
        this.comptaBanque = comptaBanque;
    }

    public String getComptaNumCheque() {
        return comptaNumCheque;
    }

    public void setComptaNumCheque(String comptaNumCheque) {
        this.comptaNumCheque = comptaNumCheque;
    }

    public boolean isCheque() {
        return cheque;
    }

    public void setCheque(boolean cheque) {
        this.cheque = cheque;
    }

    public boolean isEspace() {
        return espace;
    }

    public void setEspace(boolean espace) {
        this.espace = espace;
    }

    public boolean isCarteAdhesion() {
        return carteAdhesion;
    }

    public void setCarteAdhesion(boolean carteAdhesion) {
        this.carteAdhesion = carteAdhesion;
    }

  
}
