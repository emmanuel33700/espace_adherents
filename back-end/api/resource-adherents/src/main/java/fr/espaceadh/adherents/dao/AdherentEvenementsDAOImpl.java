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
package fr.espaceadh.adherents.dao;

import fr.espaceadh.adherents.dto.AdherentEvenementDto;
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
public class AdherentEvenementsDAOImpl extends JdbcDaoSupport implements AdherentEvenementsDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdherentEvenementsDAOImpl.class);  

    
    @Autowired
    public AdherentEvenementsDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    /**
     * Recupérer les évenmenets pour un adhérents en fonction des ces droits
     * @param typeAutority
     * @param idAdh
     * @param dateDebut
     * @param dateFin
     * @return 
     */
    @Override
    public Collection<AdherentEvenementDto> getLstEvenement(final int typeAutority, final long idAdh, final Date dateDebut, final Date dateFin) {
        
        List<AdherentEvenementDto> lstEvenement = null;
        
        StringBuilder query = new StringBuilder();
        
        query.append(" SELECT id_evenement, description_courte, detail_text, lieux, date_debut, date_fin, fk_id_type_authority ");
        query.append(" , ( ");
        query.append(" 	SELECT CASE WHEN ( ");
        query.append(" 		SELECT bool_or(participe_evenement) ");
        query.append(" 		FROM r_adh_evenement ");
        query.append("		where r_adh_evenement.fk_id_evenement = t_evenement.id_evenement ");
        query.append("		and r_adh_evenement.fk_id_adherent = ? ");
        query.append("	) = true then 1 ");
        query.append("	WHEN ( ");
        query.append("		SELECT bool_or(participe_evenement) ");
        query.append("		FROM r_adh_evenement ");
        query.append("		where r_adh_evenement.fk_id_evenement = t_evenement.id_evenement ");
        query.append("		and r_adh_evenement.fk_id_adherent = ? ");
        query.append("	) = false then 2 ");
        query.append("	ELSE 3 ");
        query.append("	END  ");
        query.append("  ) as adh_participe ");
        query.append(" FROM t_evenement ");
        query.append(" where fk_id_type_authority <= ?  ");
        
        if (dateDebut != null && dateFin != null) {
            query.append("      and date_debut >=  ?	 ");
            query.append(" 	and date_fin <= ? ");
            
             lstEvenement = this.getJdbcTemplate().query(query.toString(), new EvenementsMapper(), idAdh, idAdh, typeAutority, dateDebut, dateFin);
        } 
        
        else {
             lstEvenement = this.getJdbcTemplate().query(query.toString(), new EvenementsMapper(), idAdh, idAdh,  typeAutority);
        }
        
        
        
        LOGGER.debug("Nombre d'évènements récupérés  {} ", lstEvenement.size());
        
        return lstEvenement;
    }

    /**
     *   recupérer un évènement pour un adhérent
     * @param typeAutority
     * @param idAdh
     * @param idEvenement
     * @return 
     */
    @Override
    public AdherentEvenementDto evenement(int typeAutority, long idAdh, long idEvenement) {
        List<AdherentEvenementDto> lstEvenement = null;
        
        StringBuilder query = new StringBuilder();
        
        query.append(" SELECT id_evenement, description_courte, detail_text, lieux, date_debut, date_fin, fk_id_type_authority ");
        query.append(" , ( ");
        query.append(" 	SELECT CASE WHEN ( ");
        query.append(" 		SELECT bool_or(participe_evenement) ");
        query.append(" 		FROM r_adh_evenement ");
        query.append("		where r_adh_evenement.fk_id_evenement = t_evenement.id_evenement ");
        query.append("		and r_adh_evenement.fk_id_adherent = ? ");
        query.append("	) = true then 1 ");
        query.append("	WHEN ( ");
        query.append("		SELECT bool_or(participe_evenement) ");
        query.append("		FROM r_adh_evenement ");
        query.append("		where r_adh_evenement.fk_id_evenement = t_evenement.id_evenement ");
        query.append("		and r_adh_evenement.fk_id_adherent = ? ");
        query.append("	) = false then 2 ");
        query.append("	ELSE 3 ");
        query.append("	END  ");
        query.append("  ) as adh_participe ");
        query.append(" FROM t_evenement ");
        query.append(" where fk_id_type_authority <= ?  ");
        query.append(" and  id_evenement = ?  ");


        lstEvenement = this.getJdbcTemplate().query(query.toString(), new EvenementsMapper(), idAdh, idAdh,  typeAutority, idEvenement);

        LOGGER.debug("Nombre d'évènements récupérés  {} ", lstEvenement.size());
        
        if (!lstEvenement.isEmpty()) {
            return lstEvenement.get(0);
        }
        else {
            LOGGER.error("Erreur, aucun évènement récupéré");
        }
        
        return new AdherentEvenementDto();
    }
        
    
    /**
     * Supression des participations à un évènement
     * @param idEvenement id de l'évènement à supprimer
     * @return  true si l'évènement est supprimé 
     */
    @Override
    public boolean deleteParticipationEvenement(long idEvenement, long idAdherent) {
        StringBuilder query = new StringBuilder();
            query.append(" DELETE FROM r_adh_evenement	WHERE fk_id_evenement = ? and fk_id_adherent = ? ");


        this.getJdbcTemplate().update(query.toString(),
                 idEvenement, idAdherent
        );

        return true;        
    }

    /**
     * Création d'une participation à un évènement
     * @param idEvenement
     * @param idAdherent
     * @param participeEvenement 
     * @return 
     */
    @Override
    public boolean creationParticipationEvenement(long idEvenement, long idAdherent, boolean participeEvenement) {
        StringBuilder query = new StringBuilder();
            query.append(" INSERT INTO r_adh_evenement( ");
            query.append("  	fk_id_adherent, fk_id_evenement, date_enregistrement, participe_evenement) ");
            query.append(" 	VALUES (?, ?, now(), ?) ");    
    
        int nbResult = this.getJdbcTemplate().update(query.toString(),
                 idEvenement, idAdherent, participeEvenement
        );

        LOGGER.debug("Nombre dde participation créé {} ", nbResult);

        return true;     
    
    }
    
        /**
     * 
     */
    public static final class EvenementsMapper implements RowMapper<AdherentEvenementDto> {

        /**
         * Mapper table t_evenement
         * @param rs
         * @param i
         * @return
         * @throws SQLException 
         */
        @Override
        public AdherentEvenementDto mapRow(ResultSet rs, int i) throws SQLException {
            AdherentEvenementDto dto = new AdherentEvenementDto();
            
            dto.setIdEvenement(rs.getLong("id_evenement"));
            dto.setDescriptionCourte(rs.getString("description_courte"));
            dto.setDescriptionLongue(rs.getString("detail_text"));
            dto.setLieux(rs.getString("lieux"));
            dto.setDateDebut(new Date(rs.getTimestamp("date_debut").getTime()));
            dto.setDateFin(new Date(rs.getTimestamp("date_fin").getTime()));
            dto.setIdAuthority(rs.getInt("fk_id_type_authority"));
            dto.setTypeParticipation(rs.getInt("adh_participe"));
            
            return dto;
        }
        
    }

}
