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

import fr.espaceadh.adherents.dto.GroupeDiffusionDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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
public class AdherentListeDiffusionDAOImpl extends JdbcDaoSupport implements AdherentListeDiffusionDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdherentEvenementsDAOImpl.class);  

    
    @Autowired
    public AdherentListeDiffusionDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * Récupéer la liste complete des mailing liste avec le statut de participation de l'adhérent
     * @param idAdh
     * @return 
     */
    @Override
    public Collection<GroupeDiffusionDto> getListDiffusion(long idAdh) {
        StringBuilder query = new StringBuilder();
        
        query.append(" SELECT id_groupe_diffusion, description, fk_id_type_authority, ");
        query.append("      CASE  ");
        query.append(" 		WHEN (select 1 FROM r_groupe_diffusion_adherents  ");
        query.append(" 		where t_groupe_diffusion.id_groupe_diffusion = r_groupe_diffusion_adherents.pk_id_groupe_diffusion  ");
        query.append("          and r_groupe_diffusion_adherents.pk_id_adherent = ?) = 1 THEN TRUE ");
        query.append(" 		ELSE FALSE");
        query.append("      END AS participation ");
        query.append(" 	FROM t_groupe_diffusion ");
        
        Collection<GroupeDiffusionDto> lstGroupeDiffusionDto =  this.getJdbcTemplate().query(query.toString()
                , new GroupeDiffusionMapper()
                , idAdh);
        
        if (lstGroupeDiffusionDto.isEmpty())
            LOGGER.error("Attention aucune mailing liste");
        return lstGroupeDiffusionDto;          
    
    }

    /**
     * Ajouter une participation à un groupe de diffusion
     * @param idAdh
     * @param idGpeDiffusion
     * @return 
     */
    @Override
    public boolean ajouterParticipationGroupeDiffusion(long idAdh, long idGpeDiffusion) {
        StringBuilder query = new StringBuilder();
            query.append(" INSERT INTO r_groupe_diffusion_adherents( ");
            query.append(" 	pk_id_groupe_diffusion, pk_id_adherent, date_enregistrement) ");
            query.append(" 	VALUES (?, ?, now()) ");


        this.getJdbcTemplate().update(query.toString(),
                 idGpeDiffusion, idAdh
        );

        return true;         
    }

    /**
     * Supprimer une participation à un groupe de diffusion
     * @param idAdh
     * @param idGpeDiffusion
     * @return 
     */
    @Override
    public boolean supprimerParticipationGroupeDiffusion(long idAdh, long idGpeDiffusion) {
        StringBuilder query = new StringBuilder();
            query.append(" DELETE FROM r_groupe_diffusion_adherents ");
            query.append(" 	WHERE pk_id_groupe_diffusion = ? ");
            query.append(" 	AND pk_id_adherent = ? ");


        this.getJdbcTemplate().update(query.toString(),
                 idGpeDiffusion, idAdh
        );

        return true;  
    }
    
    
    /**
     * 
     */
    private static class GroupeDiffusionMapper  implements RowMapper<GroupeDiffusionDto> {

        @Override
        public GroupeDiffusionDto mapRow(ResultSet rs, int i) throws SQLException {
            GroupeDiffusionDto dto = new GroupeDiffusionDto();
            dto.setIdGroupeDiffusion(rs.getLong("id_groupe_diffusion"));
            dto.setLibelleGroupeDiffusion(rs.getString("description"));
            dto.setParticipe(rs.getBoolean("participation"));
            return dto;
        }
         
     }
}
