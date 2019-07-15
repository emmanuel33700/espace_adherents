/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.adherents.dao;

import fr.espaceadh.adherents.model.Adherent;
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
public class AdherentsDAOImpl extends JdbcDaoSupport implements AdherentsDAO{
    
    @Autowired
    public AdherentsDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public Adherent getAdherentByLogin(String login) {
        
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, nom, premon, adresse1, adresse2, code_postal, ville,");
        query.append("        tel1, tel2, tel3, e_mail, date_maissance, profession, a_telescope, ");
        query.append("        a_lunette, a_jumelles, modele_instrument, commentaire, date_enregistrement, ");
        query.append("        login_site_web, pwd_site_web, fk_id_type_user, link_picture, ");
        query.append("        update_date, update_fk_id_adherents, public_contact, mail_auto, ");
        query.append("        token, civilite");
        query.append("  FROM t_adherents");
        query.append(" where e_mail =  ?");
        
        List<Adherent> query1 = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper(), login);
        return query1.get(1);
    }

    @Override
    public Adherent getAdherentByID(long idAdh) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, nom, premon, adresse1, adresse2, code_postal, ville,");
        query.append("        tel1, tel2, tel3, e_mail, date_maissance, profession, a_telescope, ");
        query.append("        a_lunette, a_jumelles, modele_instrument, commentaire, date_enregistrement, ");
        query.append("        login_site_web, pwd_site_web, fk_id_type_user, link_picture, ");
        query.append("        update_date, update_fk_id_adherents, public_contact, mail_auto, ");
        query.append("        token, civilite");
        query.append("  FROM t_adherents");
        query.append(" where id_adherents =  ?");
        
        List<Adherent> query1 = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper(), idAdh);
        return query1.get(0);
    }
    
    
    
    public static final class AdherentsMapper implements RowMapper<Adherent> {

        @Override
        public Adherent mapRow(ResultSet rs, int i) throws SQLException {
            Adherent adh = new Adherent();
            adh.setId(rs.getLong("id_adherents"));
            adh.setNom(rs.getString("nom"));
            
            return adh;
        }
        
    }
    
}
