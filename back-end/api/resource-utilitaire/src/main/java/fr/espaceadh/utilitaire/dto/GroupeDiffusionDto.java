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
public class GroupeDiffusionDto {
    private long idGroupeDiffusion;
    private String libelleGroupeDiffusion;
    private int idAuthority;
    private long fkIdAdherent;
    private int nbInscrit;

    public long getIdGroupeDiffusion() {
        return idGroupeDiffusion;
    }

    public void setIdGroupeDiffusion(long idGroupeDiffusion) {
        this.idGroupeDiffusion = idGroupeDiffusion;
    }

    public String getLibelleGroupeDiffusion() {
        return libelleGroupeDiffusion;
    }

    public void setLibelleGroupeDiffusion(String libelleGroupeDiffusion) {
        this.libelleGroupeDiffusion = libelleGroupeDiffusion;
    }

    public long getFkIdAdherent() {
        return fkIdAdherent;
    }

    public void setFkIdAdherent(long fkIdAdherent) {
        this.fkIdAdherent = fkIdAdherent;
    }

    public int getIdAuthority() {
        return idAuthority;
    }

    public void setIdAuthority(int idAuthority) {
        this.idAuthority = idAuthority;
    }

    public int getNbInscrit() {
        return nbInscrit;
    }

    public void setNbInscrit(int nbInscrit) {
        this.nbInscrit = nbInscrit;
    }
}
