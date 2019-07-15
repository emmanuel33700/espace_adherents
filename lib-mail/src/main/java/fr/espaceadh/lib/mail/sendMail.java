/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.lib.mail;

import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;

/**
 *
 * @author emmanuel
 */
public interface sendMail {
    
    /**
     *  Envoyer un mail via la librairie mailjet
     * @param mailIn
     * @return 
     */
    public MailOutDto sendMail(final MailInDto mailIn);
}
