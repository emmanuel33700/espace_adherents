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
package fr.espaceadh.authorization.service;

import fr.espaceadh.authorization.dao.AuthoritiesDao;
import fr.espaceadh.authorization.dao.userDao;
import fr.espaceadh.authorization.dto.AuthoritiesDto;
import fr.espaceadh.authorization.dto.RolesEnum;
import fr.espaceadh.authorization.dto.UserDto;
import fr.espaceadh.lib.mail.GestionMail;
import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;
import fr.espaceadh.lib.mail.dto.TemplateMailEnum;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emmanuel
 */
@Service
@Transactional(readOnly = true)
public class AuthentificationServiceImpl implements AuthentificationService {

    @Autowired
    private GestionMail getionMail;
    
    @Autowired
    protected userDao userDao;
    
    @Autowired
    protected AuthoritiesDao authoritiesDao;
    
    @Autowired
    private Environment env;
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int creerUser(UserDto usersDto) {
        
        // vérification dans la BD adhérent si l'utilsateur à été créé
        int idAdh = 39;
        

        
        // création du compte dans la BD d'authorisation
        UUID uuid = UUID.randomUUID();
        usersDto.setCleeModification(uuid.toString());
        usersDto.setDateModifcationClee(new Date());
        usersDto.setDateCreation(new Date());
        usersDto.setEnabled(false);
        usersDto.setIdAdherent(idAdh);
        
        userDao.creationUser(usersDto);
        
        // création de l'authorité BD d'authorisation
        AuthoritiesDto authoritiesDto = new AuthoritiesDto();
        authoritiesDto.setUsername(usersDto.getUsername());
        authoritiesDto.setRoles(RolesEnum.ADHERENT);
        authoritiesDao.creationAutorities(authoritiesDto);
        
        //envoie du mail de validation du compte
        MailInDto mailIn = new MailInDto();

        
        Collection<String> messageTo = new ArrayList<>();
        messageTo.add(usersDto.getUsername());
        mailIn.setMessageTo(messageTo);
        
        
        /* type de template */
        mailIn.setTemplateMailEnum(TemplateMailEnum.DEMANDE_VALIDATION_MAIL);
        
        /* variables associées au tempalte **/
        HashMap<String, String> templateVariables = new HashMap<>();
        templateVariables.put("adh_prenom", "Emmanuel");
        templateVariables.put("confirmation_link", env.getProperty("validationmail.url").concat("?token=").concat(uuid.toString()));
        mailIn.setTemplateVariables(templateVariables);
        
        
        MailOutDto mailOut = getionMail.sendMail(mailIn);
        
        if (mailOut.getStatutEnvoi().endsWith("success")) return 0;
        else return 99;
    }
    
}
