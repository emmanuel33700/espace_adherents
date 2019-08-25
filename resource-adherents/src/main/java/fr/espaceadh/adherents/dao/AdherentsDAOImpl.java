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
package fr.espaceadh.adherents.dao;

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.dto.CiviliteEnum;
import fr.espaceadh.adherents.model.Adherent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author emmanuel
 */
@Repository
public class AdherentsDAOImpl extends JdbcDaoSupport implements AdherentsDAO{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdherentsDAOImpl.class);  

    
    @Autowired
    public AdherentsDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public AdherentDto getAdherentByLogin(String login) {
        
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, e_mail, civilite, nom, premon");
        query.append("        , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("        , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("        , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("        , fk_id_adherents_update, update_date ");
        query.append("  FROM t_adherents");
        query.append(" where e_mail =  ?");
        
        List<AdherentDto> query1 = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper(), login);
        if (!query1.isEmpty()) {
            return query1.get(1);
        }
        LOGGER.warn("login {} non trouvé en BD", login);
        return new AdherentDto();
    }

    @Override
    public AdherentDto getAdherentByID(long idAdh) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, e_mail, civilite, nom, premon");
        query.append("        , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("        , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("        , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("        , fk_id_adherents_update, update_date ");
        query.append("  FROM t_adherents");
        query.append(" where id_adherents =  ?");
        
        List<AdherentDto> query1 = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper(), idAdh);
        if (!query1.isEmpty()) {
            return query1.get(1);
        }
        LOGGER.warn("id adherents {} non trouvé en BD", idAdh);
        return new AdherentDto();
    }

    
    
    @Override
    public long creerAdherent(AdherentDto adherentDto) {
       
        adherentDto.setId(this.recupererIdAdherent());
        
        StringBuilder query = new StringBuilder();
        query.append(" INSERT INTO t_adherents( ");
        query.append("		id_adherents, e_mail, civilite, nom, premon, adresse1 ");
        query.append("		, adresse2, code_postal, ville, tel1, tel2, tel3, date_maissance ");
        query.append("		, profession, link_picture, public_contact, accord_mail, token_acces ");
        query.append("		, commentaire, date_enregistrement, fk_id_adherents_update, update_date) ");
        query.append("	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");        
        
       int nbCreation;          
       nbCreation = this.getJdbcTemplate().update(query.toString() 
            , adherentDto.getId()
            , adherentDto.getEmail()
            , adherentDto.getCivilite().toString()
            , adherentDto.getNom()
            , adherentDto.getPrenom()
            , adherentDto.getAdresse1()
       
            , adherentDto.getAdresse2()
            , adherentDto.getCodePostal()
            , adherentDto.getVille()
            , adherentDto.getTelMaison()
            , adherentDto.getTelTravail()
            , adherentDto.getTelPortable()
            , adherentDto.getDateNaissance()
       
            , adherentDto.getProfession()
            , adherentDto.getLienPhotoProfil()
            , adherentDto.isPublicContact()
            , adherentDto.isAccordMail()
            , adherentDto.getTokenAcces()
               
            , adherentDto.getCommentaire()
            , adherentDto.getDateEnregistrement()
            , adherentDto.getIdAdherentUpdate()
            , adherentDto.getDateMiseAJour()
            ) ;
       
        if (nbCreation ==  1) return adherentDto.getId();
        else {
            LOGGER.error("Erreur lors de la creation de l'utilisateur : nombre de ligne crée {} ", nbCreation );
        }
        return 0;
    }
    
    
    /**
     * Récuper un idAdherent
     * @return idAdherent
     */
    private long recupererIdAdherent(){
        StringBuilder query = new StringBuilder()
            .append(" select nextval('seq_t_adherents') ");
        
        final long idAdherent = this.getJdbcTemplate().queryForObject(query.toString(),  Long.class);
        
        return idAdherent;
    }
    
    
    public static final class AdherentsMapper implements RowMapper<AdherentDto> {

        @Override
        public AdherentDto mapRow(ResultSet rs, int i) throws SQLException {
            AdherentDto adh = new AdherentDto();
            adh.setId(rs.getLong("id_adherents"));
            adh.setEmail(rs.getString("e_mail"));
            if (rs.getString("civilite") == null ? CiviliteEnum.MADAME.toString() == null : rs.getString("civilite").equals(CiviliteEnum.MADAME.toString()))
                adh.setCivilite(CiviliteEnum.MADAME);
            else adh.setCivilite(CiviliteEnum.MONSIEUR);
            adh.setNom(rs.getString("nom"));
            adh.setPrenom(rs.getString("premon"));
            adh.setAdresse1(rs.getString("adresse1"));
            
            //TODO a compléter le mapping
            
            return adh;
        }
        
    }
    
}
