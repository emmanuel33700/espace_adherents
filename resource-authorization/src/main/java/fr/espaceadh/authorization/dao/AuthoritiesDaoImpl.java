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

import fr.espaceadh.authorization.dto.AuthoritiesDto;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 */
@Repository
public class AuthoritiesDaoImpl extends JdbcDaoSupport implements AuthoritiesDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthoritiesDaoImpl.class);
    
    @Autowired
    public AuthoritiesDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    
    @Override
    public boolean creationAutorities(AuthoritiesDto authoritiesDto) {
        StringBuilder query = new StringBuilder();
                 query.append(" INSERT INTO authorities( ");
                 query.append("            username, authority)");
                 query.append("     VALUES (?, ?) ");
        
        int nbCreation;          
        nbCreation = this.getJdbcTemplate().update(query.toString(),
                authoritiesDto.getUsername(),
                authoritiesDto.getRoles().toString()
        );
        if (nbCreation ==  1) return true;
        else {
            LOGGER.error("Erreur lors de la creation de l'autorité : nombre de ligne crée {} ", nbCreation );
        }
        return false;         }
    
}
