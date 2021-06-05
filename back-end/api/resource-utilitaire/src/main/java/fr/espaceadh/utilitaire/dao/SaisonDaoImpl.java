/*
 * Copyright (C) 2021 emmanuel33700 https://github.com/emmanuel33700/espace_adherents
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

import fr.espaceadh.utilitaire.dto.DocumentDto;
import fr.espaceadh.utilitaire.dto.SaisonDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author emmanuel
 */
@Repository
public class SaisonDaoImpl extends JdbcDaoSupport implements SaisonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaisonDaoImpl.class);  

        
    @Autowired
    public SaisonDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    /**
     * R2cupérer la liste des saisons
     * @return 
     */
    @Override
    public Collection<SaisonDto> getLstSaison() {
        StringBuilder query = new StringBuilder();
        
        query.append(" SELECT id_annee_adhesion, libelle_annee, annee_courante ");
        query.append(" 	FROM i_annee_adhesion ");

        
        List<SaisonDto> lstSaison =  this.getJdbcTemplate().query(query.toString(), new SaisonMapper());
        
        if (lstSaison == null || lstSaison.isEmpty()) {
            LOGGER.error("Aucune saison trouvée");
            return null;
        }
        
        return lstSaison;    
    }

    /**
     * Récupérer la saison courante
     * @return 
     */
    @Override
    public SaisonDto getSaisonCourant() {
        StringBuilder query = new StringBuilder();
        
        query.append(" SELECT id_annee_adhesion, libelle_annee, annee_courante ");
        query.append(" 	FROM i_annee_adhesion ");
        query.append(" 	WHERE annee_courante = TRUE ");

        
        List<SaisonDto> lstSaison =  this.getJdbcTemplate().query(query.toString(), new SaisonMapper());
        
        if (lstSaison == null || lstSaison.isEmpty()) {
            LOGGER.error("Aucune saison trouvée");
            return null;
        }
        
        return lstSaison.get(0);
    }

    /**
     * Positionner la saison courante
     * @param idSaison
     * @return 
     */
    @Override
    public boolean setSaisonCourante(int idSaison) {
        StringBuilder query = new StringBuilder();
            query.append(" UPDATE i_annee_adhesion ");
            query.append(" 	SET annee_courante=TRUE ");
            query.append(" 	WHERE id_annee_adhesion=? ");

        int nbUpdate;
        nbUpdate = this.getJdbcTemplate().update(query.toString(),
                 idSaison
        );

        if (nbUpdate == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la mise a jour de la saison courante {} ", idSaison);
        }
        return false;    
    }

    /**
     * retirer la saison courante
     * @return 
     */
    @Override
    public boolean retirerSaisonCourante() {
        StringBuilder query = new StringBuilder();
            query.append(" UPDATE i_annee_adhesion ");
            query.append(" 	SET annee_courante = FALSE ");
            query.append(" 	WHERE annee_courante = TRUE ");

        int nbUpdate;
        nbUpdate = this.getJdbcTemplate().update(query.toString());

        if (nbUpdate == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la mise a jour de la saison courante {} ");
        }
        return false;       
    }

    
    /**
     * 
     */
    private static class SaisonMapper implements RowMapper<SaisonDto> {

        @Override
        public SaisonDto mapRow(ResultSet rs, int i) throws SQLException {
            SaisonDto dto = new SaisonDto();
            dto.setId(rs.getInt("id_annee_adhesion"));
            dto.setLibelle(rs.getString("libelle_annee"));
            dto.setActive(rs.getBoolean("annee_courante"));
            
            return dto;
        }


    }
    
}
