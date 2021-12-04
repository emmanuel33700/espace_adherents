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
package fr.espaceadh.adherents.dto;

/**
 *
 * @author emmanuel
 */
public class GroupeDiffusionDto {
    private long idGroupeDiffusion;
    private String libelleGroupeDiffusion;
    private long fkIdAdherent;
    private boolean participe;
    private int typeAuthority;

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

    public boolean isParticipe() {
        return participe;
    }

    public void setParticipe(boolean participe) {
        this.participe = participe;
    }

    public int getTypeAuthority() {
        return typeAuthority;
    }

    public void setTypeAuthority(int typeAuthority) {
        this.typeAuthority = typeAuthority;
    }
}
