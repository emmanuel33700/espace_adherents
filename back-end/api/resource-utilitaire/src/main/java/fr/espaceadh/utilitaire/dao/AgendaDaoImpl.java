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

import fr.espaceadh.adherents.dto.CiviliteEnum;
import fr.espaceadh.utilitaire.dto.EvenementDto;
import fr.espaceadh.utilitaire.dto.EvenementParticipationAdherentDto;
import fr.espaceadh.utilitaire.dto.EvenementSyntheseDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        query.append("			id_evenement, description_courte, detail_text, lieux, date_debut, date_fin, fk_id_type_authority, besoin_confirm_participation, demande_communication) ");
        query.append("	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");

        int nbCreation;
        nbCreation = this.getJdbcTemplate().update(query.toString(),
                 evenement.getIdEvenement(),
                 evenement.getDescriptionCourte(),
                 evenement.getDescriptionLongue(),
                 evenement.getLieux(),
                 evenement.getDateDebut(),
                 evenement.getDateFin(),
                 evenement.getIdAuthority(),
                 evenement.isDemanderConfirmationParticipation(),
                 evenement.isEnvoyerInfoAdherents()
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
        
        query.append(" SELECT id_evenement, description_courte, detail_text, lieux, date_debut, date_fin, fk_id_type_authority, besoin_confirm_participation, demande_communication ");
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
        query.append("	 	, besoin_confirm_participation=?, demande_communication=?");
        query.append("	 	WHERE id_evenement=?");
       
        int nbUpdate;
        nbUpdate = this.getJdbcTemplate().update(query.toString(),
                 evenement.getDescriptionCourte(),
                 evenement.getDescriptionLongue(),
                 evenement.getLieux(),
                 evenement.getDateDebut(),
                 evenement.getDateFin(),
                 evenement.getIdAuthority(),
                 evenement.isDemanderConfirmationParticipation(),
                 evenement.isEnvoyerInfoAdherents(),
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
     * Récupérer le détail d'un évènement
     * @param idEvnement
     * @return 
     */
    @Override
    public EvenementDto getEvenement(long idEvnement) {
        StringBuilder query = new StringBuilder();
        
        query.append(" SELECT id_evenement, description_courte, detail_text, lieux, date_debut, date_fin, fk_id_type_authority ");
        query.append("	, besoin_confirm_participation, demande_communication ");
        query.append("	FROM t_evenement ");
        query.append(" 	WHERE id_evenement = ? ");

        
        List<EvenementDto> lstEvenement = this.getJdbcTemplate().query(query.toString(), new EvenementsMapper(), idEvnement);
        
        if (! lstEvenement.isEmpty()) {
            return lstEvenement.get(0);
        }
        
        LOGGER.debug("Nombre d'évènements récupérés  {} ", lstEvenement.size());
        
        return null;    
    }

    @Override
    public Collection<EvenementSyntheseDto> recupererSyntheseParticipations(Date dateDebut, Date dateFin, boolean demandeConfirParticipation) {
        
        Collection<EvenementSyntheseDto> lst =  new ArrayList<>();
        
        StringBuilder query = new StringBuilder();
        query.append(" SELECT id_evenement, description_courte, detail_text, lieux, date_debut, date_fin ");
        query.append(" 	, fk_id_type_authority, besoin_confirm_participation, demande_communication ");
        query.append(" 	, (SELECT count (1)  ");
        query.append("		FROM r_adh_evenement as participe ");
        query.append("		where participe.participe_evenement = true ");
        query.append("		and participe.fk_id_evenement = t_evenement.id_evenement) as participe ");
        query.append(" 	, (SELECT count (1)  ");
        query.append("		FROM r_adh_evenement as participepas ");
        query.append("		where participepas.participe_evenement = false ");
        query.append("		and participepas.fk_id_evenement = t_evenement.id_evenement) as participepas	 ");
        query.append("	, (SELECT count (1) as nesaispas  ");
        query.append("	   from (SELECT fk_id_adherents ");
        query.append("			FROM t_adhesions, i_annee_adhesion ");
        query.append("			where t_adhesions.fk_id_annee_adhesions = i_annee_adhesion.id_annee_adhesion ");
        query.append("			and i_annee_adhesion.annee_courante = true ");
        query.append("		EXCEPT ");
        query.append("			SELECT b.fk_id_adherent ");
        query.append("				FROM r_adh_evenement b ");
        query.append("				where b.fk_id_evenement = t_evenement.id_evenement) as resulta ");
        query.append("	  )	as nesaispas ");
        query.append("	FROM t_evenement ");
        query.append("	where true ");
        if (demandeConfirParticipation) {
            query.append("	AND besoin_confirm_participation = true ");
        }
        
        if (dateDebut != null && dateFin != null) {
            query.append("      and date_debut >=  ?	 ");
            query.append(" 	and date_fin <= ? ");
            
             lst = this.getJdbcTemplate().query(query.toString(), new EvenementsSyntheseMapper(),dateDebut, dateFin);
        } 
        
        else {
             lst = this.getJdbcTemplate().query(query.toString(), new EvenementsSyntheseMapper());
        }
        
        return lst;
        
    }

    /**
     * Récupérer les participations adhérents pour un évènement
     * @param idEvenement
     * @return 
     */
    @Override
    public Collection<EvenementParticipationAdherentDto> recupererSyntheseParticipationAdherents(long idEvenement) {
            StringBuilder query = new StringBuilder();        
            query.append(" SELECT id_adherents, e_mail, civilite, nom, premon, adresse1, adresse2, code_postal ");
            query.append("	, ville, tel1, tel2, tel3, date_maissance, profession, link_picture, public_contact ");
            query.append("	, accord_mail, token_acces, commentaire, date_enregistrement, fk_id_adherents_update, update_date ");
            query.append("	, type_participation.type_participe ");
            query.append("	, CASE ");
            query.append("		WHEN (select 1 ");
            query.append("			  from t_adhesions, i_annee_adhesion ");
            query.append("			  where t_adhesions.fk_id_annee_adhesions =  i_annee_adhesion.id_annee_adhesion ");
            query.append("			  and i_annee_adhesion.annee_courante = true ");
            query.append("			  and t_adherents.id_adherents = t_adhesions.fk_id_adherents)  = 1 THEN true	 ");
            query.append("			  ELSE false ");
            query.append("		END as adherent_saison_courante	  ");
            query.append(" FROM ( ");
            query.append(" 	SELECT a.fk_id_adherent as fk_id_adherent, 1 as type_participe ");
            query.append("		FROM r_adh_evenement a ");
            query.append("		WHERE a.participe_evenement = true ");
            query.append("		AND a.fk_id_evenement = ? ");
            query.append("	UNION ");
            query.append("	SELECT b.fk_id_adherent as fk_id_adherent, 2 as type_participe ");
            query.append("		FROM r_adh_evenement b ");
            query.append("		WHERE b.participe_evenement = false ");
            query.append("		AND b.fk_id_evenement = ? ");
            query.append("	UNION ");
            query.append("	(SELECT fk_id_adherents as fk_id_adherent, 3 as type_participe ");
            query.append("		FROM t_adhesions, i_annee_adhesion ");
            query.append("		WHERE t_adhesions.fk_id_annee_adhesions = i_annee_adhesion.id_annee_adhesion ");
            query.append("		AND i_annee_adhesion.annee_courante = true ");
            query.append("	EXCEPT ");
            query.append("		SELECT a.fk_id_adherent as fk_id_adherent, 3 as type_participe ");
            query.append("		FROM r_adh_evenement a ");
            query.append("		WHERE a.fk_id_evenement = ? ");
            query.append("	 ) ");
            query.append(" ) as type_participation, t_adherents ");
            query.append(" WHERE t_adherents.id_adherents = type_participation.fk_id_adherent ");
            query.append(" ORDER BY nom, premon ");    
            
            
            Collection<EvenementParticipationAdherentDto> lst = this.getJdbcTemplate().query(query.toString(), new ParticipationAdherentsSyntheseMapper(),idEvenement, idEvenement, idEvenement);
            
            if (lst.isEmpty()) LOGGER.error("Erreur, la liste de récupération des participations à un évènement est vide");
            
            return lst;

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
            dto.setEnvoyerInfoAdherents(rs.getBoolean("demande_communication"));
            dto.setDemanderConfirmationParticipation(rs.getBoolean("besoin_confirm_participation"));
            
            return dto;
        }
        
    }
    
    
    
        public static final class EvenementsSyntheseMapper implements RowMapper<EvenementSyntheseDto> {

        /**
         * Mapper table t_evenement
         * @param rs
         * @param i
         * @return
         * @throws SQLException 
         */
        @Override
        public EvenementSyntheseDto mapRow(ResultSet rs, int i) throws SQLException {
            EvenementSyntheseDto dto = new EvenementSyntheseDto();
            
            dto.setIdEvenement(rs.getLong("id_evenement"));
            dto.setDescriptionCourte(rs.getString("description_courte"));
            dto.setDescriptionLongue(rs.getString("detail_text"));
            dto.setLieux(rs.getString("lieux"));
            dto.setDateDebut(new Date(rs.getTimestamp("date_debut").getTime()));
            dto.setDateFin(new Date(rs.getTimestamp("date_fin").getTime()));
            dto.setIdAuthority(rs.getInt("fk_id_type_authority"));
            dto.setEnvoyerInfoAdherents(rs.getBoolean("demande_communication"));
            dto.setDemanderConfirmationParticipation(rs.getBoolean("besoin_confirm_participation"));
            dto.setNbAherentsParticipe(rs.getInt("participe"));
            dto.setNbAdherentsParticipePas(rs.getInt("participepas"));
            dto.setNbAdherentsEnAttenteParticipation(rs.getInt("nesaispas"));
            
            return dto;
        }
        
    }
        
        
        
    public static final class ParticipationAdherentsSyntheseMapper implements RowMapper<EvenementParticipationAdherentDto> {

        @Override
        public EvenementParticipationAdherentDto mapRow(ResultSet rs, int i) throws SQLException {            
            
            EvenementParticipationAdherentDto adh = new EvenementParticipationAdherentDto();
            
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
            adh.setTypeParticipation(rs.getInt("type_participe"));
            
            return adh;
        
        
        
        }


        
    }
}
