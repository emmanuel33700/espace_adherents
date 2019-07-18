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
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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
    protected userDao userDao;
    
    @Autowired
    protected AuthoritiesDao authoritiesDao;
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
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
        
        
        AuthoritiesDto authoritiesDto = new AuthoritiesDto();
        authoritiesDto.setUsername(usersDto.getUsername());
        authoritiesDto.setRoles(RolesEnum.ADHERENT);
        authoritiesDao.creationAutorities(authoritiesDto);
        
        return 0;
    }
    
}
