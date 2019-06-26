/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.dao;

import fr.espaceadh.model.Authorization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
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
            query.append(" SELECT id_adherent, id_adherent_public, civilite, nom, prenom, login,    ");
            query.append("        pass_digest, pass_salt, roles, date_derniere_con, code_activation,   ");
            query.append("        adh_actif   ");
            query.append("   FROM public.t_utilisateur  ");
            query.append("   WHERE login = ?  ");
        
        List<Authorization> query1 = this.getJdbcTemplate().query(query.toString(), new AuthorizationMapper(), login);
        return query1.get(1);
        
    }
    
    
    
     public static final class AuthorizationMapper implements RowMapper<Authorization> {

        @Override
        public Authorization mapRow(ResultSet rs, int i) throws SQLException {
            Authorization auth = new Authorization();
            
            return auth;
        }
         
     }
}
