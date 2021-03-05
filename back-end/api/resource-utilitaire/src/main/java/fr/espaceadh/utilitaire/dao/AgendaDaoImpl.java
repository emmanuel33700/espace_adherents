/*
 * Copyright (C) 2021 emmanuel
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

import fr.espaceadh.utilitaire.dto.EvenementDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
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
public class AgendaDaoImpl extends JdbcDaoSupport implements AgendaDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaDaoImpl.class);  
    
    @Autowired
    public AgendaDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * Crééer un évènement
     * @param evenement
     * @return  true si évènement est créé
     */
    @Override
    public boolean creerEvenement(EvenementDto evenement) {
        final long idEvenement = this.recupererIdEvenement();
        
                StringBuilder query = new StringBuilder();
        query.append(" INSERT INTO public.t_evenement( ");
        query.append("			id_evenement, description_courte, detail_text, lieux, date_debut, date_fin, fk_id_type_authority) ");
        query.append("	VALUES (?, ?, ?, ?, ?, ?, ?) ");

        int nbCreation;
        nbCreation = this.getJdbcTemplate().update(query.toString(),
                 evenement.getIdEvenement(),
                 evenement.getDescriptionCourte(),
                 evenement.getDescriptionLongue(),
                 evenement.getLieux(),
                 evenement.getDateDebut(),
                 evenement.getDateFin(),
                 evenement.getIdAuthority()
        );

        if (nbCreation == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la creation d'un évènement : nombre de ligne crée {} ", nbCreation);
        }
        return false;

    }
    
    
    
    /**
     * recupérer un nouvel id evenement
     * @return 
     */
    private long recupererIdEvenement(){
        StringBuilder query = new StringBuilder()
            .append(" select nextval('seq_t_evenement') ");
        
        final long idEvenement = this.getJdbcTemplate().queryForObject(query.toString(),  Long.class);
        
        return  idEvenement;
    }

    /**
     * Recuperer la liste des évènements en fonction du type d'autority
     * @param typeAutority
     * @return liste d'évènement
     */
    @Override
    public Collection<EvenementDto> getLstEvenement(int typeAutority) {
        StringBuilder query = new StringBuilder();
        
        query.append(" SELECT id_evenement, description_courte, detail_text, lieux, date_debut, date_fin, fk_id_type_authority ");
        query.append("	FROM t_evenement ");
        query.append(" 	WHERE fk_id_type_authority <= ? ");
        
        
        List<EvenementDto> lstEvenement = this.getJdbcTemplate().query(query.toString(), new EvenementsMapper(), typeAutority);
        
        
        
        LOGGER.debug("Nombre d'évènements récupérés  {} ", lstEvenement.size());
        
        return lstEvenement;
    }
    
    /**
     * 
     */
    public static final class EvenementsMapper implements RowMapper<EvenementDto> {

        /**
         * Mapper table t_evenement
         * @param rs
         * @param i
         * @return
         * @throws SQLException 
         */
        @Override
        public EvenementDto mapRow(ResultSet rs, int i) throws SQLException {
            EvenementDto dto = new EvenementDto();
            
            dto.setIdEvenement(rs.getLong("id_evenement"));
            dto.setDescriptionCourte(rs.getString("description_courte"));
            dto.setDescriptionLongue(rs.getString("detail_text"));
            dto.setLieux(rs.getString("lieux"));
            dto.setDateDebut(new Date(rs.getTimestamp("date_debut").getTime()));
            dto.setDateFin(new Date(rs.getTimestamp("date_fin").getTime()));
            dto.setIdAuthority(rs.getInt("fk_id_type_authority"));
            
            return dto;
        }
        
    }
}
