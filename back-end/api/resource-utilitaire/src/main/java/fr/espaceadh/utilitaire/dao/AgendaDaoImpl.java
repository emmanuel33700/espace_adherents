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
     * Mise à jour d'un évènement
     * @param evenement
     * @return  true si l'évènement est modifié
     */
    @Override
    public boolean updateEvenement(EvenementDto evenement) {
                StringBuilder query = new StringBuilder();
        query.append(" UPDATE t_evenement ");
        query.append("	   	SET description_courte=?, detail_text=?, lieux=?, date_debut=?, date_fin=?, fk_id_type_authority=? ");
        query.append("	 	WHERE id_evenement=?");

        int nbUpdate;
        nbUpdate = this.getJdbcTemplate().update(query.toString(),
                 evenement.getDescriptionCourte(),
                 evenement.getDescriptionLongue(),
                 evenement.getLieux(),
                 evenement.getDateDebut(),
                 evenement.getDateFin(),
                 evenement.getIdAuthority(),
                 evenement.getIdEvenement()
        );

        if (nbUpdate == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la modification d'un évènement : nombre de ligne crée {} ", nbUpdate);
        }
        return false;    }

        /**
     * Supression d'un évènement
     * @param idEvenement id de l'évènement à supprimer
     * @return true si l'évènement est supprimé 
     */
    @Override
    public boolean deleteEvenement(long idEvenement) {
        StringBuilder query = new StringBuilder();
            query.append(" DELETE FROM t_evenement WHERE id_evenement = ? ");

        int nbSupression;
        nbSupression = this.getJdbcTemplate().update(query.toString(),
                 idEvenement
        );

        if (nbSupression == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la suppression d'un évènement ", idEvenement);
        }
        return false;    
    }

    
        /**
     * Supression des participations à un évènement
     * @param idEvenement id de l'évènement à supprimer
     * @return  true si l'évènement est supprimé 
     */
    @Override
    public boolean deleteParticipationEvenement(long idEvenement) {
        StringBuilder query = new StringBuilder();
            query.append(" DELETE FROM r_adh_evenement	WHERE fk_id_evenement = ? ");


        this.getJdbcTemplate().update(query.toString(),
                 idEvenement
        );

        return true;        
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
