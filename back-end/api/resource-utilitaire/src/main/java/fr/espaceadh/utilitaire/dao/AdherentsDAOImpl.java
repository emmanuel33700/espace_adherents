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
package fr.espaceadh.utilitaire.dao;

import fr.espaceadh.utilitaire.dto.AdherentDto;
import fr.espaceadh.utilitaire.dto.CiviliteEnum;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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
    public Collection<AdherentDto> recupererListeAdherentSaison() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, e_mail, civilite, nom, premon");
        query.append("        , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("        , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("        , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("        , fk_id_adherents_update, update_date, true as adherent_saison_courante ");
        query.append("  FROM t_adherents, t_adhesions, i_annee_adhesion ");
        query.append("  WHERE t_adherents.id_adherents = t_adhesions.fk_id_adherents ");
        query.append("  AND t_adhesions.fk_id_annee_adhesions =   i_annee_adhesion.id_annee_adhesion");
        query.append("  AND i_annee_adhesion.annee_courante = true ");
        query.append("  ORDER BY nom, premon");
        
        List<AdherentDto> lstAdherents = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper());

        LOGGER.debug("Nombre d'adherents récupéré (ensemble de la list des adhérents {} ", lstAdherents.size());
        return  lstAdherents;
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
            adh.setAdresse2(rs.getString("adresse2"));
            adh.setCodePostal(rs.getString("code_postal"));
            adh.setVille(rs.getString("ville"));
            adh.setTelMaison(rs.getString("tel1"));
            adh.setTelPortable(rs.getString("tel3"));
            adh.setTelTravail(rs.getString("tel2"));
            adh.setDateNaissance(rs.getDate("date_maissance"));
            adh.setProfession(rs.getString("profession"));
            adh.setLienPhotoProfil(rs.getString("link_picture"));
            adh.setPublicContact(rs.getBoolean("public_contact"));
            adh.setAccordMail(rs.getBoolean("accord_mail"));
            adh.setTokenAcces(rs.getString("token_acces"));
            adh.setCommentaire(rs.getString("commentaire"));
            adh.setDateEnregistrement(rs.getDate("date_enregistrement"));
            adh.setIdAdherentUpdate(rs.getLong("fk_id_adherents_update"));
            adh.setDateMiseAJour(rs.getDate("update_date"));
            adh.setAdhesionSaisonCourante(rs.getBoolean("adherent_saison_courante"));
            return adh;
        }
        
    }
    

}
