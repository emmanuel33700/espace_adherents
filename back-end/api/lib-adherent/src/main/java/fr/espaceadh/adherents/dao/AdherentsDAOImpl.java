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
package fr.espaceadh.adherents.dao;

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.dto.AdhesionDto;
import fr.espaceadh.adherents.dto.CiviliteEnum;
import fr.espaceadh.adherents.dto.LienAdherentsDto;
import fr.espaceadh.adherents.dto.TypeAdhesionEnum;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
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
    public AdherentDto getAdherentByLogin(String login) {
        
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, e_mail, civilite, nom, premon");
        query.append("        , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("        , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("        , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("        , fk_id_adherents_update, update_date ");
        query.append("	      , CASE ");
        query.append("			 WHEN (select 1 ");
        query.append("			 from t_adhesions, i_annee_adhesion");
        query.append("			 where t_adhesions.fk_id_annee_adhesions =   i_annee_adhesion.id_annee_adhesion ");
        query.append("			 and i_annee_adhesion.annee_courante = true ");
        query.append("			 and t_adherents.id_adherents = t_adhesions.fk_id_adherents)  = 1 THEN true ");
        query.append("			 ELSE false");
        query.append("        END as adherent_saison_courante");
        query.append("	      , CASE ");
        query.append("			 WHEN (select 1 ");
        query.append("			 from t_adhesions, i_annee_adhesion");
        query.append("			 where t_adhesions.fk_id_annee_adhesions =   (i_annee_adhesion.id_annee_adhesion -1)");
        query.append("			 and i_annee_adhesion.annee_courante = true ");
        query.append("			 and t_adherents.id_adherents = t_adhesions.fk_id_adherents)  = 1 THEN true ");
        query.append("			 ELSE false");
        query.append("        END as adherent_saison_precedente");
        query.append("  FROM t_adherents");
        query.append(" where e_mail =  ?");
        
        List<AdherentDto> query1 = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper(), login);
        if (!query1.isEmpty()) {
            return query1.get(0);
        }
        LOGGER.warn("login {} non trouvé en BD", login);
        return null;
    }

    @Override
    public AdherentDto getAdherentByID(long idAdh) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, e_mail, civilite, nom, premon");
        query.append("        , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("        , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("        , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("        , fk_id_adherents_update, update_date ");
        query.append("	      , CASE ");
        query.append("			 WHEN (select 1 ");
        query.append("			 from t_adhesions, i_annee_adhesion");
        query.append("			 where t_adhesions.fk_id_annee_adhesions =   i_annee_adhesion.id_annee_adhesion ");
        query.append("			 and i_annee_adhesion.annee_courante = true ");
        query.append("			 and t_adherents.id_adherents = t_adhesions.fk_id_adherents)  = 1 THEN true ");
        query.append("			 ELSE false");
        query.append("        END as adherent_saison_courante");
        query.append("	      , CASE ");
        query.append("			 WHEN (select 1 ");
        query.append("			 from t_adhesions, i_annee_adhesion");
        query.append("			 where t_adhesions.fk_id_annee_adhesions =   (i_annee_adhesion.id_annee_adhesion -1)");
        query.append("			 and i_annee_adhesion.annee_courante = true ");
        query.append("			 and t_adherents.id_adherents = t_adhesions.fk_id_adherents)  = 1 THEN true ");
        query.append("			 ELSE false");
        query.append("        END as adherent_saison_precedente");
        query.append("  FROM t_adherents");
        query.append(" where id_adherents =  ?");
        
        List<AdherentDto> query1 = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper(), idAdh);
        if (!query1.isEmpty()) {
            return query1.get(0);
        }
        LOGGER.warn("id adherents {} non trouvé en BD", idAdh);
        return new AdherentDto();
    }

    
    
    @Override
    public long creerAdherent(AdherentDto adherentDto) {
        
        final long idAdh = this.recupererIdAdherent();
        
        StringBuilder query = new StringBuilder();
        query.append(" INSERT INTO t_adherents( ");
        query.append("		id_adherents, e_mail, civilite, nom, premon, adresse1 ");
        query.append("		, adresse2, code_postal, ville, tel1, tel2, tel3, date_maissance ");
        query.append("		, profession, link_picture, public_contact, accord_mail, token_acces ");
        query.append("		, commentaire, date_enregistrement, fk_id_adherents_update, update_date) ");
        query.append("	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");        
        
       int nbCreation;          
       nbCreation = this.getJdbcTemplate().update(query.toString() 
            , idAdh
            , adherentDto.getEmail()
            , adherentDto.getCivilite().toString()
            , adherentDto.getNom()
            , adherentDto.getPrenom()
            , adherentDto.getAdresse1()
       
            , adherentDto.getAdresse2()
            , adherentDto.getCodePostal()
            , adherentDto.getVille()
            , adherentDto.getTelMaison()
            , adherentDto.getTelTravail()
            , adherentDto.getTelPortable()
            , adherentDto.getDateNaissance()
       
            , adherentDto.getProfession()
            , adherentDto.getLienPhotoProfil()
            , adherentDto.isPublicContact()
            , adherentDto.isAccordMail()
            , adherentDto.getTokenAcces()
               
            , adherentDto.getCommentaire()
            , adherentDto.getDateEnregistrement()
            , adherentDto.getIdAdherentUpdate()
            , adherentDto.getDateMiseAJour()
            ) ;
       
        if (nbCreation ==  1) return idAdh;
        else {
            LOGGER.error("Erreur lors de la creation de l'utilisateur : nombre de ligne crée {} ", nbCreation );
        }
        return 0;
    }
    
    
    /**
     * Récuper un idAdherent
     * @return idAdherent
     */
    private long recupererIdAdherent(){
        StringBuilder query = new StringBuilder()
            .append(" select nextval('seq_t_adherents') ");
        
        final long idAdherent = this.getJdbcTemplate().queryForObject(query.toString(),  Long.class);
        
        return idAdherent;
    }

    /**
     * Récupérer la liste des adhérents de la saison courant et saison précédente
     *
     * @return
     */
    @Override
    public Collection<AdherentDto> recupererListeAdherentSaisonEtAncienneSaison() {
        StringBuilder query = new StringBuilder();
        query.append(" select distinct * from ( ");
        query.append("      SELECT id_adherents, e_mail, civilite, nom, premon ");
        query.append("      , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("      , adresse1, adresse2, code_postal, ville, tel1, tel2  ");
        query.append("      , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("      , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("      , fk_id_adherents_update, update_date, true as adherent_saison_courante  ");
        query.append("	      , CASE ");
        query.append("			 WHEN (select 1 ");
        query.append("			 from t_adhesions, i_annee_adhesion");
        query.append("			 where t_adhesions.fk_id_annee_adhesions =   (i_annee_adhesion.id_annee_adhesion -1)");
        query.append("			 and i_annee_adhesion.annee_courante = true ");
        query.append("			 and t_adherents.id_adherents = t_adhesions.fk_id_adherents)  = 1 THEN true ");
        query.append("			 ELSE false");
        query.append("        END as adherent_saison_precedente");
        query.append("  FROM t_adherents, t_adhesions, i_annee_adhesion ");
        query.append("  WHERE t_adherents.id_adherents = t_adhesions.fk_id_adherents ");
        query.append("  AND t_adhesions.fk_id_annee_adhesions =   i_annee_adhesion.id_annee_adhesion");
        query.append("  AND i_annee_adhesion.annee_courante = true  ");
        query.append(" UNION ");
        query.append("      SELECT id_adherents, e_mail, civilite, nom, premon ");
        query.append("      , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("      , adresse1, adresse2, code_postal, ville, tel1, tel2  ");
        query.append("      , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("      , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("      , fk_id_adherents_update, update_date, true as adherent_saison_courante  ");
        query.append("      , true as adherent_saison_precedente");
        query.append("  FROM t_adherents, t_adhesions, i_annee_adhesion ");
        query.append("  WHERE t_adherents.id_adherents = t_adhesions.fk_id_adherents ");
        query.append("  AND t_adhesions.fk_id_annee_adhesions =   ( i_annee_adhesion.id_annee_adhesion - 1) ");
        query.append("  AND i_annee_adhesion.annee_courante = true  ");
        query.append(" ) as resultat ");
        query.append(" order by nom, premon ");


        List<AdherentDto> lstAdherents = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper());

        LOGGER.debug("Nombre d'adherents récupéré  {} ", lstAdherents.size());
        return  lstAdherents;
    }

    /**
     * Récupérer les adhérents qui n'ont pas renouvelles leur adhésion
     *
     * @return
     */
    @Override
    public Collection<AdherentDto> recupererListeNonAdherentSaison() {
        StringBuilder query = new StringBuilder();
        query.append(" select distinct * from ( ");
        query.append("      SELECT id_adherents, e_mail, civilite, nom, premon ");
        query.append("      , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("      , adresse1, adresse2, code_postal, ville, tel1, tel2  ");
        query.append("      , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("      , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("      , fk_id_adherents_update, update_date, false as adherent_saison_courante  ");
        query.append("      , true as adherent_saison_precedente");
        query.append("  FROM t_adherents, t_adhesions, i_annee_adhesion ");
        query.append("  WHERE t_adherents.id_adherents = t_adhesions.fk_id_adherents ");
        query.append("  AND t_adhesions.fk_id_annee_adhesions =  ( i_annee_adhesion.id_annee_adhesion - 1)");
        query.append("  AND i_annee_adhesion.annee_courante = true  ");
        query.append(" EXCEPT ");
        query.append("      SELECT id_adherents, e_mail, civilite, nom, premon ");
        query.append("      , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("      , adresse1, adresse2, code_postal, ville, tel1, tel2  ");
        query.append("      , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("      , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("      , fk_id_adherents_update, update_date, false as adherent_saison_courante  ");
        query.append("      , true as adherent_saison_precedente");
        query.append("  FROM t_adherents, t_adhesions, i_annee_adhesion ");
        query.append("  WHERE t_adherents.id_adherents = t_adhesions.fk_id_adherents ");
        query.append("  AND t_adhesions.fk_id_annee_adhesions =   i_annee_adhesion.id_annee_adhesion  ");
        query.append("  AND i_annee_adhesion.annee_courante = true  ");
        query.append(" ) as resultat ");
        query.append(" order by nom, premon ");


        List<AdherentDto> lstAdherents = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper());

        LOGGER.debug("Nombre d'adherents récupéré  {} ", lstAdherents.size());
        return  lstAdherents;
    }

    @Override
    public Collection<AdherentDto> recupererListeCompletAdherent() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, e_mail, civilite, nom, premon");
        query.append("        , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("        , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("        , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("        , fk_id_adherents_update, update_date ");
        query.append("	      , CASE ");
        query.append("			 WHEN (select 1 ");
        query.append("			 from t_adhesions, i_annee_adhesion");
        query.append("			 where t_adhesions.fk_id_annee_adhesions =   i_annee_adhesion.id_annee_adhesion ");
        query.append("			 and i_annee_adhesion.annee_courante = true ");
        query.append("			 and t_adherents.id_adherents = t_adhesions.fk_id_adherents)  = 1 THEN true ");
        query.append("			 ELSE false");
        query.append("        END as adherent_saison_courante");
        query.append("	      , CASE ");
        query.append("			 WHEN (select 1 ");
        query.append("			 from t_adhesions, i_annee_adhesion");
        query.append("			 where t_adhesions.fk_id_annee_adhesions =   (i_annee_adhesion.id_annee_adhesion -1)");
        query.append("			 and i_annee_adhesion.annee_courante = true ");
        query.append("			 and t_adherents.id_adherents = t_adhesions.fk_id_adherents)  = 1 THEN true ");
        query.append("			 ELSE false");
        query.append("        END as adherent_saison_precedente");
        query.append("  FROM t_adherents ");
        query.append("  ORDER BY nom, premon");
        
        List<AdherentDto> lstAdherents = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper());

        LOGGER.debug("Nombre d'adherents récupéré (ensemble de la list des adhérents {} ", lstAdherents.size());
        return  lstAdherents;
    }

    @Override
    public Collection<AdherentDto> recupererListeAdherentSaison() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, e_mail, civilite, nom, premon");
        query.append("        , adresse1, adresse2, code_postal, ville, tel1, tel2 ");
        query.append("        , tel3, date_maissance, profession, link_picture, public_contact ");
        query.append("        , accord_mail, token_acces, commentaire, date_enregistrement ");
        query.append("        , fk_id_adherents_update, update_date, true as adherent_saison_courante ");
        query.append("	      , CASE ");
        query.append("			 WHEN (select 1 ");
        query.append("			 from t_adhesions, i_annee_adhesion");
        query.append("			 where t_adhesions.fk_id_annee_adhesions =   (i_annee_adhesion.id_annee_adhesion -1)");
        query.append("			 and i_annee_adhesion.annee_courante = true ");
        query.append("			 and t_adherents.id_adherents = t_adhesions.fk_id_adherents)  = 1 THEN true ");
        query.append("			 ELSE false");
        query.append("        END as adherent_saison_precedente");
        query.append("  FROM t_adherents, t_adhesions, i_annee_adhesion ");
        query.append("  WHERE t_adherents.id_adherents = t_adhesions.fk_id_adherents ");
        query.append("  AND t_adhesions.fk_id_annee_adhesions =   i_annee_adhesion.id_annee_adhesion");
        query.append("  AND i_annee_adhesion.annee_courante = true ");
        query.append("  ORDER BY nom, premon");
        
        List<AdherentDto> lstAdherents = this.getJdbcTemplate().query(query.toString(), new AdherentsMapper());

        LOGGER.debug("Nombre d'adherents récupéré (ensemble de la list des adhérents {} ", lstAdherents.size());
        return  lstAdherents;
    }

    /**
     * Recupérer la liste des adhérents de la saison avec détail comptable sur l'adhésions de la saison
     * @return
     */
    @Override
    public Collection<AdhesionDto> recupererListeAdherentAdhesionSaison() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id_adherents, e_mail, civilite, nom, premon");
        query.append("        , adresse1, adresse2, code_postal, ville, tel1, tel2");
        query.append("        , tel3, date_maissance, profession, link_picture, public_contact");
        query.append("        , accord_mail, token_acces, commentaire, date_enregistrement");
        query.append("        , fk_id_adherents_update, update_date, true as adherent_saison_courante");
        query.append("		, A.id_adhesions, A.fk_id_adherents, A.fk_id_annee_adhesions, B.libelle_annee, A.fk_id_type_adhesion");
        query.append("        , A.compta_somme, A.compta_banque, A.num_cheque, A.cheque, A.espece, A.a_carte_adhesions");
        query.append("  FROM t_adherents, t_adhesions as A, i_annee_adhesion as B");
        query.append("  WHERE t_adherents.id_adherents = A.fk_id_adherents");
        query.append("  AND A.fk_id_annee_adhesions =   B.id_annee_adhesion");
        query.append("  AND B.annee_courante = true");
        query.append("  ORDER BY nom, premon");

        List<AdhesionDto> lstAdherents = this.getJdbcTemplate().query(query.toString(), new AdherentsAdhesionMapper());

        LOGGER.debug("Nombre d'adherents récupéré (ensemble de la list des adhérents {} ", lstAdherents.size());
        return  lstAdherents;
    }


    @Override
    public boolean updateAdherents(AdherentDto adherentDto) {
        StringBuilder query = new StringBuilder();
        query.append(" UPDATE public.t_adherents ");
        query.append("	SET ");
        query.append("	e_mail=?, ");
        query.append("	civilite=?, ");
        query.append("	nom=?, ");
        query.append("	premon=?, ");
        query.append("	adresse1=?, ");
        query.append("	adresse2=?, ");
        query.append("	code_postal=?, ");
        query.append("	ville=?, ");
        query.append("	tel1=?, ");
        query.append("	tel2=?, ");
        query.append("	tel3=?, ");
        query.append("	date_maissance=?, ");
        query.append("	profession=?, ");
        query.append("	link_picture=?, ");
        query.append("	public_contact=?, ");
        query.append("	accord_mail=?, ");
        query.append("	commentaire=?, ");
        query.append("	fk_id_adherents_update=?,");
        query.append("	update_date= ? ");
        query.append("	WHERE id_adherents=? ");
        
        
        int nbCreation = this.getJdbcTemplate().update(query.toString() 
            , adherentDto.getEmail()
            , adherentDto.getCivilite().toString()
            , adherentDto.getNom()
            , adherentDto.getPrenom()
            , adherentDto.getAdresse1()
            , adherentDto.getAdresse2()
            , adherentDto.getCodePostal()
            , adherentDto.getVille()
            , adherentDto.getTelMaison()
            , adherentDto.getTelTravail()
            , adherentDto.getTelPortable()
            , adherentDto.getDateNaissance()
            , adherentDto.getProfession()
            , adherentDto.getLienPhotoProfil()
            , adherentDto.isPublicContact()
            , adherentDto.isAccordMail()
            , adherentDto.getCommentaire()
            , adherentDto.getIdAdherentUpdate()
            , adherentDto.getDateMiseAJour()
                
            , adherentDto.getId()
            ) ;
        
        if (nbCreation == 1) return true;
        else {
            LOGGER.error("Erreur lors de la mj de l'utilisateur {} : nombre de ligne crée {} ",adherentDto.getId(), nbCreation );
            return false;
        }
    }

    @Override
    public boolean creerAdhesion(AdhesionDto adhesionDto) {
        final long idAdhesion = this.recupererIdAdhesion();

        StringBuilder query = new StringBuilder();
        query.append(" INSERT INTO t_adhesions( ");
        query.append("		id_adhesions, fk_id_adherents, fk_id_annee_adhesions, fk_id_type_adhesion, ");
        query.append("		compta_somme, compta_banque, num_cheque, cheque, espece, a_carte_adhesions) ");
        query.append("	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

        int nbCreation;
        nbCreation = this.getJdbcTemplate().update(query.toString(),
                 idAdhesion,
                 adhesionDto.getIdAdherent(),
                 adhesionDto.getIdAnneeAdhesion(),
                 adhesionDto.getIdTypeAdhesion().toInt(),
                 adhesionDto.getComptaSomme(),
                 adhesionDto.getComptaBanque(),
                 adhesionDto.getComptaNumCheque(),
                 adhesionDto.isCheque(),
                 adhesionDto.isEspace(),
                 adhesionDto.isCarteAdhesion()
        );

        if (nbCreation == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la creation d'une adhésion : nombre de ligne crée {} ", nbCreation);
        }
        return false;

    }
    
    /**
     * récupérer un nouvel id d'adhésion
     * @return 
     */
    private long recupererIdAdhesion(){
        StringBuilder query = new StringBuilder()
            .append(" select nextval('seq_t_adhesion') ");
        
        final long idAdhesion = this.getJdbcTemplate().queryForObject(query.toString(),  Long.class);
        
        return idAdhesion;
    }

    @Override
    public Collection<AdhesionDto> getAdhesionsPourUnAdherent(long idAdh) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT A.id_adhesions, A.fk_id_adherents, A.fk_id_annee_adhesions, B.libelle_annee, A.fk_id_type_adhesion, ");
        query.append("        A.compta_somme, A.compta_banque, A.num_cheque, A.cheque, A.espece, A.a_carte_adhesions ");
        query.append("	FROM t_adhesions as A, i_annee_adhesion as B ");
        query.append("	where B.id_annee_adhesion = A.fk_id_annee_adhesions ");
        query.append("	and A.fk_id_adherents = ? ");
        query.append("	ORDER BY A.fk_id_annee_adhesions DESC ");
        
        List<AdhesionDto> lstAdhesions = this.getJdbcTemplate().query(query.toString(), new AdhesionsMapper(), idAdh);

        LOGGER.debug("Nombre d'adherents récupéré (ensemble de la list des adhérents {} ", lstAdhesions.size());
        return  lstAdhesions;   
    }

    @Override
    public AdhesionDto getAdhesionPourUnAdherent(long idAdh, long idAnneAdhesion) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT A.id_adhesions, A.fk_id_adherents, A.fk_id_annee_adhesions, B.libelle_annee, A.fk_id_type_adhesion, ");
        query.append("        A.compta_somme, A.compta_banque, A.num_cheque, A.cheque, A.espece, A.a_carte_adhesions ");
        query.append("	FROM t_adhesions as A, i_annee_adhesion as B ");
        query.append("	where B.id_annee_adhesion = A.fk_id_annee_adhesions ");
        query.append("	and A.fk_id_adherents = ? ");
        query.append("	AND A.fk_id_annee_adhesions = ? ");

        
        List<AdhesionDto> lstAdhesions = this.getJdbcTemplate().query(query.toString(), new AdhesionsMapper(), idAdh, idAnneAdhesion);

        LOGGER.debug("Nombre d'adherents récupéré (ensemble de la list des adhérents {} ", lstAdhesions.size());
        if (!lstAdhesions.isEmpty()) return lstAdhesions.get(0);
        else {
            LOGGER.info("aucune adhésion retrouvé pour cet adh {} et cette année {}",idAdh, idAnneAdhesion );
            return null;
        }
    }

    @Override
    public boolean majAdhesion(AdhesionDto adhesionDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean suppAdhesion(long idAdh, long idAnneAdhesion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *  Recherche d'un lien representant <=> representé entre deux adhérent
     * @param idAdherentRepresentant
     * @param idAdherentRepresente
     * @return 
     */
    @Override
    public LienAdherentsDto getLienAdherent(Long idAdherentRepresentant, Long idAdherentRepresente) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT fk_id_adherent_representant, fk_id_adherent_represente, t_adherents.nom ");
        query.append(" 	, t_adherents.premon, t_adherents.e_mail ");
        query.append(" 	FROM r_relation_adherent, t_adherents ");
        query.append(" 	where r_relation_adherent.fk_id_adherent_represente = t_adherents.id_adherents ");
        query.append(" 	and fk_id_adherent_representant = ? ");
        query.append(" 	and fk_id_adherent_represente = ? ");

        
        List<LienAdherentsDto> lstLienAdherentsDto = this.getJdbcTemplate().query(query.toString(), new LienAdherentsDtoMapper()
                , idAdherentRepresentant
                ,idAdherentRepresente);

        if (lstLienAdherentsDto == null) return null;
        if (lstLienAdherentsDto.size() > 0) return lstLienAdherentsDto.get(0);
        
        return null;
      
    }

            /**
     * recupérer la liste des personnes représenté par un adhérent
     * @param idAdherentRepresentant
     * @return 
     */
    @Override
    public Collection<LienAdherentsDto> getLiensAdherent(Long idAdherentRepresentant) {
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT fk_id_adherent_representant, fk_id_adherent_represente, t_adherents.nom ");
        query.append(" 	, t_adherents.premon, t_adherents.e_mail ");
        query.append(" 	FROM r_relation_adherent, t_adherents ");
        query.append(" 	where r_relation_adherent.fk_id_adherent_represente = t_adherents.id_adherents ");
        query.append(" 	and fk_id_adherent_representant = ? ");

        
        List<LienAdherentsDto> lstLienAdherentsDto = this.getJdbcTemplate().query(query.toString(), new LienAdherentsDtoMapper()
                , idAdherentRepresentant);

        if (lstLienAdherentsDto == null) return null;
        
        return lstLienAdherentsDto;
        
    }

        /**
     * Ajouter un lien entre deux adhérents
     * @return 
     */
    @Override
    public boolean ajouterLienAdherent(Long idAdherentRepresentant, Long idAdherentRepresente) {

        StringBuilder query = new StringBuilder();
        query.append(" INSERT INTO r_relation_adherent( ");
        query.append("   	fk_id_adherent_representant, fk_id_adherent_represente)");
        query.append(" 	VALUES (?, ?) ");

        int nbCreation;
        nbCreation = this.getJdbcTemplate().update(query.toString(),
                 idAdherentRepresentant,
                 idAdherentRepresente
        );

        if (nbCreation == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la creation d'une liaison entre adhérent : nombre de ligne crée {} ", nbCreation);
        }
        return false;
    }
    
    /**
     * Supprimer un lien de représentation entre 2 adhérents
     * @param idAdherentRepresentant
     * @param idAdherentRepresente
     * @return 
     */
    @Override
    public boolean supprimerLienAdherent(Long idAdherentRepresentant, Long idAdherentRepresente) {
        StringBuilder query = new StringBuilder();
        query.append(" DELETE FROM r_relation_adherent ");
        query.append(" 	WHERE fk_id_adherent_representant = ? ");
        query.append(" 	AND fk_id_adherent_represente = ? ");

        int nbSupp;
        nbSupp = this.getJdbcTemplate().update(query.toString(),
                 idAdherentRepresentant,
                 idAdherentRepresente
        );

        if (nbSupp == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la supression d'une liaison entre adhérent : nombre de ligne supprimer {} ", nbSupp);
        }
        return false;
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
    
    /**
     * Mapper adhésions
     */
     public static final class AdhesionsMapper implements RowMapper<AdhesionDto> {

        @Override
        public AdhesionDto mapRow(ResultSet rs, int i) throws SQLException {
            AdhesionDto dto = new AdhesionDto();
            
            dto.setId(rs.getLong("id_adhesions"));
            dto.setIdAdherent(rs.getLong("fk_id_adherents"));
            dto.setIdAnneeAdhesion(rs.getLong("fk_id_annee_adhesions"));
            dto.setLibelleAnneeAdhesion(rs.getString("libelle_annee"));
            
            int typeAdh = rs.getInt("fk_id_type_adhesion");
            if (typeAdh == TypeAdhesionEnum.BIENFAITEUR.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.BIENFAITEUR);
            else if (typeAdh == TypeAdhesionEnum.DEMANDEUR_EMPLOI.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.DEMANDEUR_EMPLOI);
            else if (typeAdh == TypeAdhesionEnum.ENFANT.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.ENFANT);
            else if (typeAdh == TypeAdhesionEnum.ETUDIANT.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.ETUDIANT);
            else if (typeAdh == TypeAdhesionEnum.FAMILLE.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.FAMILLE);
            else if (typeAdh == TypeAdhesionEnum.HONNEUR.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.HONNEUR);
            else if (typeAdh == TypeAdhesionEnum.RESPONSABLE_DE_FAMILLE.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.RESPONSABLE_DE_FAMILLE);
            else dto.setIdTypeAdhesion(TypeAdhesionEnum.ADULTE);
            
            dto.setComptaSomme(rs.getLong("compta_somme"));
            dto.setComptaBanque(rs.getString("compta_banque"));
            dto.setComptaNumCheque(rs.getString("num_cheque"));
            dto.setCheque(rs.getBoolean("cheque"));
            dto.setEspace(rs.getBoolean("espece"));
            dto.setCarteAdhesion(rs.getBoolean("a_carte_adhesions"));
            
            return dto;
        }
         
     }

    private static class LienAdherentsDtoMapper implements RowMapper<LienAdherentsDto>{

        @Override
        public LienAdherentsDto mapRow(ResultSet rs, int i) throws SQLException {
            LienAdherentsDto dto = new LienAdherentsDto();
            dto.setIdAdhRepresentant(rs.getLong("fk_id_adherent_representant"));
            dto.setIdAdhRepresente(rs.getLong("fk_id_adherent_represente"));
            dto.setNomAdhRepresente(rs.getString("nom"));
            dto.setPrenomAdhRepresente(rs.getString("premon"));
            dto.setEmailAdhRepresente(rs.getString("e_mail"));
            
            return dto;
        }

    }

    /**
     * Mapper des adhérenst avec les adhésions courants
     */
    private static class AdherentsAdhesionMapper implements RowMapper<AdhesionDto> {
        @Override
        public AdhesionDto mapRow(ResultSet rs, int i) throws SQLException {
            AdhesionDto dto = new AdhesionDto();

            dto.setId(rs.getLong("id_adhesions"));
            dto.setIdAdherent(rs.getLong("fk_id_adherents"));
            dto.setIdAnneeAdhesion(rs.getLong("fk_id_annee_adhesions"));
            dto.setLibelleAnneeAdhesion(rs.getString("libelle_annee"));

            int typeAdh = rs.getInt("fk_id_type_adhesion");
            if (typeAdh == TypeAdhesionEnum.BIENFAITEUR.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.BIENFAITEUR);
            else if (typeAdh == TypeAdhesionEnum.DEMANDEUR_EMPLOI.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.DEMANDEUR_EMPLOI);
            else if (typeAdh == TypeAdhesionEnum.ENFANT.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.ENFANT);
            else if (typeAdh == TypeAdhesionEnum.ETUDIANT.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.ETUDIANT);
            else if (typeAdh == TypeAdhesionEnum.FAMILLE.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.FAMILLE);
            else if (typeAdh == TypeAdhesionEnum.HONNEUR.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.HONNEUR);
            else if (typeAdh == TypeAdhesionEnum.RESPONSABLE_DE_FAMILLE.toInt())  dto.setIdTypeAdhesion(TypeAdhesionEnum.RESPONSABLE_DE_FAMILLE);
            else dto.setIdTypeAdhesion(TypeAdhesionEnum.ADULTE);

            dto.setComptaSomme(rs.getLong("compta_somme"));
            dto.setComptaBanque(rs.getString("compta_banque"));
            dto.setComptaNumCheque(rs.getString("num_cheque"));
            dto.setCheque(rs.getBoolean("cheque"));
            dto.setEspace(rs.getBoolean("espece"));
            dto.setCarteAdhesion(rs.getBoolean("a_carte_adhesions"));


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

            dto.setAdherentDto(adh);

            return dto;
        }
    }
}
