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

import java.util.Date;

/**
 *
 * @author emmanuel
 */
public class MessageResultDto {
    
    private long id = 0;
    private String statut = null;
    private String sujetMail = null;
    private String mailDestinataire =  null;
    private int typeErreur;
    private int scoreSpam;
    private String regleSpam =  null;
    private String UUID =  null;
    private Date dateArrive = null;

    public String getSujetMail() {
        return sujetMail;
    }

    public void setSujetMail(String sujetMail) {
        this.sujetMail = sujetMail;
    }

    public String getMailDestinataire() {
        return mailDestinataire;
    }

    public void setMailDestinataire(String mailDestinataire) {
        this.mailDestinataire = mailDestinataire;
    }

    /**
     * 
     * @return ID numérique unique expliquant pourquoi le message n'a pas été remis avec succès au destinataire. Uniquement renvoyé si le message n'a pas été remis avec succès. Valeur possible :
    1 : user unknown (recipient)
    2 : mailbox inactive (recipient)
    3 : quota exceeded (recipient)
    4 : invalid domain (domain)
    5 : no mail host (domain)
    6 : relay/access denied (domain)
    7 : sender blocked (spam)
    8 : content blocked (spam)
    9 : policy issue (spam)
    10 : system issue (system)
    11 : protocol issue (system)
    12 : connection issue (system)
    13 : greylisted (domain)
    14 : preblocked (Mailjet)
    15 : duplicate in campaign (Mailjet)
    16 : spam preblocked (Mailjet)
    17 : bad or empty template (content)
    18 : error in template language (content)
    19 : typofix (domain)
    20 : blacklisted (recipient)
    21 : spam reporter (recipient)
     */
    public int getTypeErreur() {
        return typeErreur;
    }

    public void setTypeErreur(int typeErreur) {
        this.typeErreur = typeErreur;
    }

    public int getScoreSpam() {
        return scoreSpam;
    }

    public void setScoreSpam(int scoreSpam) {
        this.scoreSpam = scoreSpam;
    }

    public String getRegleSpam() {
        return regleSpam;
    }

    public void setRegleSpam(String regleSpam) {
        this.regleSpam = regleSpam;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    


    /**
     * 
     * @return  unknown, queued, sent, opened, clicked, bounce, spam, unsub, blocked, hardbounced, softbounced, deferred
     */
    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
    }
    
    
    
    
}
