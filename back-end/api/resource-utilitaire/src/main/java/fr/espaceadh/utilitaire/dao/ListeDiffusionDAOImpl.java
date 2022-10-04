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

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.dto.CiviliteEnum;
import fr.espaceadh.utilitaire.dto.AdherentMailingListeDto;
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
                , groupeDiffusionDto.getIdAuthority()
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
        query.append("   , (select count(1) from r_groupe_diffusion_adherents where r_groupe_diffusion_adherents.pk_id_groupe_diffusion = t_groupe_diffusion.id_groupe_diffusion) as nbinscrit");
        query.append(" 	  FROM t_groupe_diffusion ");
        
        List<GroupeDiffusionDto> lstGroupeDiffusionDto =  this.getJdbcTemplate().query(query.toString(), new GroupeDiffusionMapper());
        
        if (lstGroupeDiffusionDto.isEmpty())
            LOGGER.error("Attention aucune mailing liste");
        return lstGroupeDiffusionDto;      
    }

    @Override
    public GroupeDiffusionDto getListeListeDiffusion(long idGroupeDiffusion) {
        StringBuilder query = new StringBuilder();

        query.append(" SELECT id_groupe_diffusion, description, fk_id_type_authority ");
        query.append("   , (select count(1) from r_groupe_diffusion_adherents where r_groupe_diffusion_adherents.pk_id_groupe_diffusion = t_groupe_diffusion.id_groupe_diffusion) as nbinscrit");
        query.append(" 	  FROM t_groupe_diffusion ");
        query.append(" 	  where  id_groupe_diffusion = ? ");

        List<GroupeDiffusionDto> lstGroupeDiffusionDto =  this.getJdbcTemplate().query(query.toString(), new GroupeDiffusionMapper(), idGroupeDiffusion);

        if (lstGroupeDiffusionDto.isEmpty())
            LOGGER.error("Attention aucune mailing liste");
        return lstGroupeDiffusionDto.get(0);
    }

    /**
     * Recherche la liste des adhérents inscrit à une mailing liste
     *
     * @param idGroupeDiffusion
     * @return
     */
    @Override
    public Collection<AdherentDto> getAdherentsInscritListeDiffusion(long idGroupeDiffusion) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, e_mail, civilite, nom, premon");
        query.append("        , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("        , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("        , accord_mail, token_acces, commentaire, t_adherents.date_enregistrement ");
        query.append("        , fk_id_adherents_update, update_date, true as adherent_saison_courante ");
        query.append("  FROM t_adherents, t_adhesions, i_annee_adhesion, r_groupe_diffusion_adherents ");
        query.append("  WHERE r_groupe_diffusion_adherents.pk_id_adherent = t_adherents.id_adherents  ");
        query.append("  AND t_adherents.id_adherents = t_adhesions.fk_id_adherents  ");
        query.append("  AND t_adhesions.fk_id_annee_adhesions =   i_annee_adhesion.id_annee_adhesion ");
        query.append(" AND i_annee_adhesion.annee_courante = true  ");
        query.append("  AND r_groupe_diffusion_adherents.pk_id_groupe_diffusion = ? ");
        query.append(" ORDER BY nom, premon ");

        List<AdherentDto> lstAdherents = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper(), idGroupeDiffusion );

        LOGGER.debug("Nombre d'adherents récupéré pour la liste de diffusion {} : {} ", idGroupeDiffusion, lstAdherents.size());
        return  lstAdherents;
    }

    /**
     * recherche la liste des adhérents avec le statut d'inscription à la mailing liste
     *
     * @param idListeDiffusion
     * @return
     */
    @Override
    public Collection<AdherentMailingListeDto> getListeAdherentsListDiffusion(long idListeDiffusion) {

        StringBuilder query = new StringBuilder();

        query.append(" SELECT id_adherents, e_mail, civilite, nom, premon ");
        query.append("       , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("       , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("       , accord_mail, token_acces, commentaire, t_adherents.date_enregistrement ");
        query.append("       , fk_id_adherents_update, update_date ");
        query.append("       , CASE ");
        query.append("             WHEN (select 1 from r_groupe_diffusion_adherents ");
        query.append("                 WHERE r_groupe_diffusion_adherents.pk_id_adherent = t_adherents.id_adherents ");
        query.append("                 AND r_groupe_diffusion_adherents.pk_id_groupe_diffusion = ?)  = 1 THEN TRUE ");
        query.append("             ELSE FALSE ");
        query.append("       END as inscrit_mailing_list ");
        query.append("       , CASE ");
        query.append("             WHEN (select 1 from t_adhesions, i_annee_adhesion ");
        query.append("                 WHERE t_adherents.id_adherents = t_adhesions.fk_id_adherents ");
        query.append("                 AND t_adhesions.fk_id_annee_adhesions =   i_annee_adhesion.id_annee_adhesion ");
        query.append("                 AND i_annee_adhesion.annee_courante = true ");
        query.append("                 AND t_adherents.id_adherents = t_adhesions.fk_id_adherents )  = 1 THEN TRUE ");
        query.append("             ELSE FALSE ");
        query.append("       END as adherent_saison_courante ");
        query.append("  FROM t_adherents ");
        query.append("  ORDER BY  nom, premon ");


        List<AdherentMailingListeDto> lstAdherents = this.getJdbcTemplate().query(query.toString(), new AdherentsMailingListMapper(), idListeDiffusion );

        LOGGER.debug("Nombre d'adherents récupéré pour la liste de diffusion {} : {} ", idListeDiffusion, lstAdherents.size());
        return  lstAdherents;
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
            dto.setIdAuthority(rs.getInt("fk_id_type_authority"));
            dto.setNbInscrit(rs.getInt("nbinscrit"));
            
            return dto;
        }
         
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


    public static final class AdherentsMailingListMapper implements RowMapper<AdherentMailingListeDto> {

        @Override
        public AdherentMailingListeDto mapRow(ResultSet rs, int i) throws SQLException {
            AdherentMailingListeDto adh = new AdherentMailingListeDto();
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
            adh.setInscriptionMailingList(rs.getBoolean("inscrit_mailing_list"));
            return adh;
        }

    }
}
