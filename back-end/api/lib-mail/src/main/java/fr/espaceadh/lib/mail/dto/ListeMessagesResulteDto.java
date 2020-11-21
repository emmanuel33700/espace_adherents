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
package fr.espaceadh.lib.mail.dto;

import java.util.Collection;

/**
 *
 * @author emmanuel
 */
public class ListeMessagesResulteDto {

    private int nbMessages;
    private Collection<MessageResultDto> lstMessageResulteDto;

    public int getNbMessages() {
        return nbMessages;
    }

    public void setNbMessages(int nbMessages) {
        this.nbMessages = nbMessages;
    }

    public Collection<MessageResultDto> getLstMessageResulteDto() {
        return lstMessageResulteDto;
    }

    public void setLstMessageResulteDto(Collection<MessageResultDto> lstMessageResulteDto) {
        this.lstMessageResulteDto = lstMessageResulteDto;
    }
    
    
}
