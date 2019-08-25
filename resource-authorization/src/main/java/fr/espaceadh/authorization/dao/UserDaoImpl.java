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
package fr.espaceadh.authorization.dao;

import fr.espaceadh.authorization.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author emmanuel
 */
@Repository
public class UserDaoImpl extends JdbcDaoSupport implements userDao{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
        
    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public boolean creationUser(UserDto userDto) {
        StringBuilder query = new StringBuilder();
                 query.append(" INSERT INTO users( ");
                 query.append("             username, password, enabled, idadherent, datecreation, cleemodification, datemodificationclee)");
                 query.append("     VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        int nbCreation;          
        nbCreation = this.getJdbcTemplate().update(query.toString(),
                userDto.getUsername(),
                userDto.getPasswordEncode(),
                userDto.isEnabled(),
                userDto.getIdAdherent(),
                userDto.getDateCreation(),
                userDto.getCleeModification(),
                userDto.getDateModifcationClee()
        );
        if (nbCreation ==  1) return true;
        else {
            LOGGER.error("Erreur lors de la creation de l'utilisateur : nombre de ligne crée {} ", nbCreation );
        }
        return false;        
        
    }

    @Override
    public boolean majUser(UserDto userDto) {
        boolean indicVirgul = false;
        List<Object> values = new ArrayList<Object>();

        StringBuilder query = new StringBuilder();
        query.append("UPDATE users ");
        query.append(" SET ");
        
        if (isNotNull(userDto.getUsername())) {
            query.append(getVirgule(indicVirgul));
            query.append(" username = ? ");
            values.add(userDto.getUsername().toLowerCase());
            indicVirgul = true;
        }
        if (isNotNull(userDto.getPasswordEncode())) {
            query.append(getVirgule(indicVirgul));
            query.append(" password = ? ");
            values.add(userDto.getPasswordEncode());
            indicVirgul = true;
        }
        query.append(getVirgule(indicVirgul));
        query.append(" enabled = ? ");
        values.add(userDto.isEnabled());
        indicVirgul = true;
        if (isNotNull(userDto.getDateModif())) {
            query.append(getVirgule(indicVirgul));
            query.append(" datemodif = ? ");
            values.add(userDto.getDateModif());
            indicVirgul = true;
        }
        if (isNotNull(userDto.getDateConnexion())) {
            query.append(getVirgule(indicVirgul));
            query.append(" dateconnexion = ? ");
            values.add(userDto.getDateConnexion());
            indicVirgul = true;
        }
        if (isNotNull(userDto.getCleeModification())) {
            query.append(getVirgule(indicVirgul));
            query.append(" cleemodification = ? ");
            values.add(userDto.getCleeModification());
            indicVirgul = true;
        }
        if (isNotNull(userDto.getDateModifcationClee())) {
            query.append(getVirgule(indicVirgul));
            query.append(" datemodificationclee = ? ");
            values.add(userDto.getDateModifcationClee());
            indicVirgul = true;
        }  
        query.append(" WHERE idadherent = ? ");
        values.add(userDto.getIdAdherent());


        LOGGER.debug("Query {}", query.toString());
        LOGGER.debug("values {} ", values.toArray());

        int nbUpdate;        
        nbUpdate = this.getJdbcTemplate().update(query.toString(), values.toArray());
        if (nbUpdate ==  1) return true;
        else {
            LOGGER.error("Erreur lors de la mise à jour de l'utilisateur : nombre de ligne mise à jour {} ", nbUpdate );
        }
        return false;
    }

    @Override
    public UserDto lectureUtilisateur(String login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    private String getVirgule(boolean ind) {
        if (ind) {
            return ", ";
        }
        return " ";
    }

    private boolean isNotNull(Object string) {
        return string != null;
    }
    
    
}