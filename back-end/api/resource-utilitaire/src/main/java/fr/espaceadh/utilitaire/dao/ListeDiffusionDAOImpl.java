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

import fr.espaceadh.utilitaire.dto.DocumentDto;
import fr.espaceadh.utilitaire.dto.GroupeDiffusionDto;
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
public class ListeDiffusionDAOImpl extends JdbcDaoSupport implements ListeDiffusionDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListeDiffusionDAOImpl.class);  

        
    @Autowired
    public ListeDiffusionDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

        /**
     * Ajouter une liste de diffusion
     * @param groupeDiffusionDto
     * @return 
     */
    @Override
    public boolean addListeDiffusion(GroupeDiffusionDto groupeDiffusionDto) {
        StringBuilder query = new StringBuilder();
        
        query.append(" INSERT INTO t_groupe_diffusion(  ");
        query.append("    id_groupe_diffusion, description, fk_id_type_authority) ");
        query.append("    VALUES (?, ?, ?) ");

        int nbCreation;
        nbCreation = this.getJdbcTemplate().update(query.toString(),
                groupeDiffusionDto.getIdGroupeDiffusion()
                , groupeDiffusionDto.getLibelleGroupeDiffusion()
                , 1
        );

        if (nbCreation == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la création d'une mailing liste : nombre de ligne créé {} ", nbCreation);
        }
        return false;    }

        /**
     * Mise à jour d'une liste de diffusion
     * @param groupeDiffusionDto
     * @return 
     */
    @Override
    public boolean updateListeDiffusion(GroupeDiffusionDto groupeDiffusionDto) {
        StringBuilder query = new StringBuilder();
        
        query.append(" UPDATE t_groupe_diffusion  ");
        query.append("    SET  description=?, fk_id_type_authority=? ");
        query.append("    WHERE id_groupe_diffusion=? ");

        int nbCreation;
        nbCreation = this.getJdbcTemplate().update(query.toString(),
                groupeDiffusionDto.getLibelleGroupeDiffusion()
                , 1
                , groupeDiffusionDto.getIdGroupeDiffusion()
        );

        if (nbCreation == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de maj d'une mailing liste : nombre de ligne modifiée {} ", nbCreation);
        }
        return false;
    }

        /**
     * Suppression d'une liste de diffusion
     * @param idGroupeDiffusion
     * @return 
     */
    @Override
    public boolean deleteListeDiffusion(long idGroupeDiffusion) {
        StringBuilder query = new StringBuilder();
            query.append(" DELETE FROM t_groupe_diffusion WHERE id_groupe_diffusion = ? ");

        int nbSupression;
        nbSupression = this.getJdbcTemplate().update(query.toString(),
                 idGroupeDiffusion
        );

        if (nbSupression == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la suppression d'une mailing liste ", idGroupeDiffusion);
        }
        return false;    
    }

        /**
     * Récupérer la liste complete des listes de diffusion
     * @return 
     */
    @Override
    public Collection<GroupeDiffusionDto> getListeListeDiffusion() {
        StringBuilder query = new StringBuilder();
        
        query.append(" SELECT id_groupe_diffusion, description, fk_id_type_authority ");
        query.append(" 	  FROM t_groupe_diffusion ");
        
        List<GroupeDiffusionDto> lstGroupeDiffusionDto =  this.getJdbcTemplate().query(query.toString(), new GroupeDiffusionMapper());
        
        if (lstGroupeDiffusionDto.isEmpty())
            LOGGER.error("Attention aucune mailing liste");
        return lstGroupeDiffusionDto;      
    }

    /**
     * Supression des inscriptions à une liste de diffusion
     * @param idGroupeDiffusion
     * @return 
     */
    @Override
    public boolean deleteInscriptionListeDiffusion(long idGroupeDiffusion) {
        StringBuilder query = new StringBuilder();
            query.append(" DELETE FROM r_groupe_diffusion_adherents WHERE pk_id_groupe_diffusion = ? ");

        int nbSupression;
        nbSupression = this.getJdbcTemplate().update(query.toString(),
                 idGroupeDiffusion
        );


        return true;       
    }
    
    
    
    
     private static class GroupeDiffusionMapper  implements RowMapper<GroupeDiffusionDto> {

        @Override
        public GroupeDiffusionDto mapRow(ResultSet rs, int i) throws SQLException {
            GroupeDiffusionDto dto = new GroupeDiffusionDto();
            dto.setIdGroupeDiffusion(rs.getLong("id_groupe_diffusion"));
            dto.setLibelleGroupeDiffusion(rs.getString("description"));
            
            return dto;
        }
         
     }
    
}
