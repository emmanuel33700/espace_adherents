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
import fr.espaceadh.authorization.dto.RolesEnum;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
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
        
        int nbCreation = 0;       
        for (RolesEnum role : authoritiesDto.getRoles()){
            nbCreation =  nbCreation + this.getJdbcTemplate().update(query.toString(),
                authoritiesDto.getUsername(),
                role.toString()
        );
        }

        if (nbCreation >=  1) return true;
        else {
            LOGGER.error("Erreur lors de la creation de l'autorité : nombre de ligne crée {} ", nbCreation );
        }
        return false;         
    }


    /**
     * Suppression des autorities
     * @param username
     * @return 
     */
    @Override
    public boolean suppressionAutorities(String username) {
        StringBuilder query = new StringBuilder();
        query.append(" DELETE FROM authorities	WHERE username = ? ");
        
        int nbDelete = 0;    
        nbDelete =  this.getJdbcTemplate().update(query.toString(),username);
        if (nbDelete >=  1) return true;
        else return false;
        
    }

    /**
     * Recuperer les Autorities d'un utilisateur
     * @param string
     * @return 
     */
    @Override
    public AuthoritiesDto recupererAutorities(String login) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT username, authority ");    
        query.append(" 	FROM authorities ");   
        query.append(" 	where username = ? ");   
    
        AuthoritiesDto dto = new AuthoritiesDto();
        
        dto.setRoles(this.getJdbcTemplate().query(query.toString(), new  AutoritiesMapper(), login));
        dto.setUsername(login);
        
        return dto;
        
    }

    /**
     *  Modifier le username d'un utilisateur
     * @param usernameOld
     * @param usernameNew
     * @return 
     */
    @Override
    public boolean modifierUserNameUtilisateur(String usernameOld, String usernameNew) {
        StringBuilder query = new StringBuilder();
        query.append(" UPDATE authorities SET username = ? WHERE username = ? ");
        
        int nbDelete = 0;    
        nbDelete =  this.getJdbcTemplate().update(query.toString()
                ,usernameNew
                ,usernameOld
                );
        if (nbDelete >  0) return true;
        else {
            LOGGER.error("Erreur aucune mise à jour du username {} pour le user {} ",usernameNew, usernameOld );
            return false;
        }    
    }
    
    
     public static final class AutoritiesMapper implements  RowMapper<RolesEnum> {

        @Override
        public RolesEnum mapRow(ResultSet rs, int i) throws SQLException {

            
            switch (rs.getString("authority")) {
                case "ADMIN":
                    return RolesEnum.ADMIN;
                case "CONSEIL":
                    return RolesEnum.CONSEIL;
                case "RES_ATELIER":
                    return RolesEnum.RES_ATELIER;
                default:
                    return RolesEnum.ADHERENT;
            }  
        }
         
     }
    
}
