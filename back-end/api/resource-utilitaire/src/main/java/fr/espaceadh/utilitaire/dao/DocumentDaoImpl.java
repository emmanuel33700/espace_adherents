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
public class DocumentDaoImpl extends JdbcDaoSupport implements DocumentDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentDaoImpl.class);  

        
    @Autowired
    public DocumentDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    /**
     * Recuper la list de document enfant à partir d'un document parent
     * @param idDossierParent
     * @return 
     */
    @Override
    public Collection<DocumentDto> getListDocuments(long idDossierParent) {
        
        StringBuilder query = new StringBuilder();
        
        query.append("  SELECT id_share_docs, fk_id_share_docs, label_short, label_long, date_save, ");
        query.append("  	is_folder, is_file, filer, fk_id_user_created, fk_id_type_authority, ");
        query.append("  	(select distinct 1 from t_share_docs t_share_docsBis where t_share_docsBis.fk_id_share_docs = t_share_docs.id_share_docs) as aEnfant ");
        query.append("  FROM t_share_docs ");
        query.append("  where fk_id_share_docs = ? ");
        
        List<DocumentDto> lstEvenement =  this.getJdbcTemplate().query(query.toString(), new DocumentMapper(), idDossierParent);
        
        return lstEvenement;
        
    }

    /**
     * Créer un document
     * @param document
     * @return 
     */
    @Override
    public boolean creerDocument(DocumentDto document) {
        StringBuilder query = new StringBuilder();
        
        query.append(" INSERT INTO public.t_share_docs(  ");
        query.append("      id_share_docs, fk_id_share_docs, label_short, label_long, date_save, is_folder, is_file, filer, fk_id_user_created, fk_id_type_authority)  ");
        query.append("  VALUES (?, ?, ?, ?, now(), ?, ?, ?, ?, ?) ");

        int nbCreation;
        nbCreation = this.getJdbcTemplate().update(query.toString(),
                 document.getIdDocument(),
                 document.getIdDocumentParent(),
                 document.getLablelCourt(),
                 document.getLabelLong(),
                 document.isDossier(),
                 document.isFichier(),
                 document.getNonFichier(),
                 document.getIdAuteur(),
                 document.getIdAuthority()
        );

        if (nbCreation == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la creation d'un évènement : nombre de ligne crée {} ", nbCreation);
        }
        return false;
    }




    @Override
    public DocumentDto getDocument(long idDocument) {
        StringBuilder query = new StringBuilder();
        
        query.append("  SELECT id_share_docs, fk_id_share_docs, label_short, label_long, date_save, ");
        query.append("  	is_folder, is_file, filer, fk_id_user_created, fk_id_type_authority, ");
        query.append("  	(select distinct 1 from t_share_docs t_share_docsBis where t_share_docsBis.fk_id_share_docs = t_share_docs.id_share_docs) as aEnfant ");
        query.append("  FROM t_share_docs ");
        query.append("  where id_share_docs = ? ");
        
        List<DocumentDto> lstEvenement =  this.getJdbcTemplate().query(query.toString(), new DocumentMapper(), idDocument);
        
        if (lstEvenement != null && !lstEvenement.isEmpty()) return lstEvenement.get(0);
        
        LOGGER.error("Attention aucun document pour l'id {}", idDocument);
        return null;    
    
    }

    /**
     * Supprimer un document
     * @param idDocument
     * @return 
     */
    @Override
    public boolean supprimerDocument(long idDocument) {
        StringBuilder query = new StringBuilder();
            query.append(" DELETE FROM t_share_docs WHERE id_share_docs = ? ");

        int nbSupression;
        nbSupression = this.getJdbcTemplate().update(query.toString(),
                 idDocument
        );

        if (nbSupression == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la suppression d'un document ", idDocument);
        }
        return false;
    }

    /**
     * 
     * @param document
     * @return 
     */
    @Override
    public boolean modifierDocument(DocumentDto document) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Recupérer les documents en fonction de la date min et max de création
     * @param minDateCreation
     * @param maxDateCreation
     * @return 
     */
    @Override
    public Collection<DocumentDto> getDocuments(Date minDateCreation, Date maxDateCreation) {
        StringBuilder query = new StringBuilder();
        
        query.append("  SELECT id_share_docs, fk_id_share_docs, label_short, label_long, date_save, ");
        query.append("  	is_folder, is_file, filer, fk_id_user_created, fk_id_type_authority, ");
        query.append("  	(select distinct 1 from t_share_docs t_share_docsBis where t_share_docsBis.fk_id_share_docs = t_share_docs.id_share_docs) as aEnfant ");
        query.append("  FROM t_share_docs ");
        query.append("  where id_share_docs != -1 ");

        List<DocumentDto> lstEvenement = null;
        if (minDateCreation != null && maxDateCreation != null){
            query.append("  AND date_save >= ? ");
            query.append("  AND date_save <= ? ");
            lstEvenement =  this.getJdbcTemplate().query(query.toString(), new DocumentMapper(), minDateCreation, maxDateCreation );
        } else {
            lstEvenement =  this.getJdbcTemplate().query(query.toString(), new DocumentMapper());
        }    

        if (lstEvenement != null && !lstEvenement.isEmpty()) return lstEvenement;
        
        LOGGER.error("Attention aucun document trouvé pour minDateCreation {} et  maxDateCreation {}", minDateCreation, maxDateCreation);
        return null;      }

    
    
    /**
     * 
     */
    private static class DocumentMapper  implements RowMapper<DocumentDto>{

        @Override
        public DocumentDto mapRow(ResultSet rs, int i) throws SQLException {
            DocumentDto dto = new DocumentDto();
            
            dto.setIdDocument(rs.getLong("id_share_docs"));
            dto.setIdDocumentParent(rs.getLong("fk_id_share_docs"));
            dto.setLablelCourt(rs.getString("label_short"));
            dto.setLabelLong(rs.getString("label_long"));
            dto.setDateDeCreation(new Date(rs.getTimestamp("date_save").getTime()));
            dto.setDossier(rs.getBoolean("is_folder"));
            dto.setFichier(rs.getBoolean("is_file"));
            dto.setNonFichier(rs.getString("filer"));
            dto.setIdAuteur(rs.getLong("fk_id_user_created"));
            dto.setIdAuthority(rs.getInt("fk_id_type_authority"));
            dto.setaEnfant((rs.getInt("aEnfant") == 1));
            
            return dto;
        }

    }
    
}
