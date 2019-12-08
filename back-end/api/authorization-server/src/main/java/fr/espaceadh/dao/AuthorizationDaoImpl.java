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
package fr.espaceadh.dao;

import fr.espaceadh.dto.Authorization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emmanuel
 */
@Repository
@Transactional
public class AuthorizationDaoImpl extends JdbcDaoSupport  implements AuthorizationDao{
    
    
    @Autowired
    public AuthorizationDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    

    

    @Override
    public Authorization getAuthorisation(String login) {
        
       StringBuilder query = new StringBuilder();
            query.append(" SELECT username, password, enabled, idadherent, datecreation, datemodif,     ");
            query.append("        dateconnexion   ");
            query.append("  FROM users  ");
            query.append("   WHERE username = ?  ");
        
        List<Authorization> query1 = this.getJdbcTemplate().query(query.toString(), new AuthorizationMapper(), login);
        return query1.get(0);
        
    }
    
    
    
     public static final class AuthorizationMapper implements RowMapper<Authorization> {

        @Override
        public Authorization mapRow(ResultSet rs, int i) throws SQLException {
            Authorization auth = new Authorization();
            
            auth.setIdAdherent(rs.getString("idadherent"));
            return auth;
        }
         
     }
}
