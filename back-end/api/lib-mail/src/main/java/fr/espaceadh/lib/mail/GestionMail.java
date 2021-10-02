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
package fr.espaceadh.lib.mail;

import fr.espaceadh.lib.mail.dto.ListeMessagesResulteDto;
import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;

import java.util.Collection;

/**
 *
 * @author emmanuel
 */
public interface GestionMail {
    
    /**
     *  Envoyer un mail via la librairie mailjet
     * @param mailIn
     * @return 
     */
    public Collection<MailOutDto> sendMail(final MailInDto mailIn);
    
    /**
     * Recuperer l'historique des messages envoyé à une personne
     * @param mail de la personne ou lon souhaite récupérer l'hostorique
     * @return 
     */
    public ListeMessagesResulteDto recupeHistoriqueMessage(String mail);
}
