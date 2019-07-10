/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
