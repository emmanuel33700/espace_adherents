-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.2
-- PostgreSQL version: 11.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: espace_adh | type: DATABASE --
-- -- DROP DATABASE IF EXISTS espace_adh;
-- CREATE DATABASE espace_adh
-- 	ENCODING = 'UTF8'
-- 	LC_COLLATE = 'fr_FR.UTF-8'
-- 	LC_CTYPE = 'fr_FR.UTF-8'
-- 	TABLESPACE = pg_default
-- 	OWNER = postgres;
-- -- ddl-end --
-- 

-- object: public.i_annee_adhesion | type: TABLE --
-- DROP TABLE IF EXISTS public.i_annee_adhesion CASCADE;
CREATE TABLE public.i_annee_adhesion (
	id_annee_adhesion numeric(4,0) NOT NULL,
	libelle_annee character varying(9) NOT NULL,
	annee_courante boolean,
	CONSTRAINT pk_i_annee_adhesion PRIMARY KEY (id_annee_adhesion)

);
-- ddl-end --
COMMENT ON COLUMN public.i_annee_adhesion.annee_courante IS E'indicateur de l''anne encours';
-- ddl-end --
-- ALTER TABLE public.i_annee_adhesion OWNER TO postgres;
-- ddl-end --

-- object: public.t_adherents | type: TABLE --
-- DROP TABLE IF EXISTS public.t_adherents CASCADE;
CREATE TABLE public.t_adherents (
	id_adherents numeric(9,0) NOT NULL,
	e_mail character varying(60),
	civilite character varying(3),
	nom character varying(60) NOT NULL,
	premon character varying(60),
	adresse1 character varying(150),
	adresse2 character varying(170),
	code_postal character varying(6),
	ville character varying(50),
	tel1 character varying(15),
	tel2 character varying(15),
	tel3 character varying(50),
	date_maissance date,
	profession character varying(60),
	link_picture character varying(40),
	public_contact boolean NOT NULL DEFAULT false,
	accord_mail boolean NOT NULL DEFAULT true,
	token_acces character varying(12),
	commentaire text,
	date_enregistrement date,
	fk_id_adherents_update numeric(9,0),
	update_date timestamp with time zone,
	CONSTRAINT pk_t_adherents PRIMARY KEY (id_adherents)

);
-- ddl-end --
COMMENT ON TABLE public.t_adherents IS E'liste des adhérents';
-- ddl-end --
COMMENT ON COLUMN public.t_adherents.public_contact IS E'boolean indiquant si on afficher les coordonees sur l espace public';
-- ddl-end --
COMMENT ON COLUMN public.t_adherents.accord_mail IS E'indique si l utilisateur recois tous les mails de la plateforme';
-- ddl-end --
COMMENT ON COLUMN public.t_adherents.token_acces IS E'token, permetant de faire des opérations sans authentification (exemple, indiquer la participation aux evenements)';
-- ddl-end --
COMMENT ON COLUMN public.t_adherents.fk_id_adherents_update IS E'id de la personne qui à mis à jour cette personne';
-- ddl-end --
-- ALTER TABLE public.t_adherents OWNER TO postgres;
-- ddl-end --

-- object: public.t_adhesions | type: TABLE --
-- DROP TABLE IF EXISTS public.t_adhesions CASCADE;
CREATE TABLE public.t_adhesions (
	id_adhesions numeric(9,0) NOT NULL,
	fk_id_adherents numeric(9,0) NOT NULL,
	fk_id_annee_adhesions numeric(4,0) NOT NULL,
	fk_id_type_adhesion numeric(4,0) NOT NULL,
	compta_somme numeric(4,0),
	compta_banque character varying(35),
	num_cheque character varying(35),
	cheque boolean,
	espece boolean,
	a_carte_adhesions boolean,
	CONSTRAINT pk_t_adhesions PRIMARY KEY (id_adhesions)

);
-- ddl-end --
COMMENT ON TABLE public.t_adhesions IS E'table relatif aux adhesions. Une adhésions est le lien entre une personne physique adhérent et une saison';
-- ddl-end --
-- ALTER TABLE public.t_adhesions OWNER TO postgres;
-- ddl-end --

-- object: public.i_type_adhesion | type: TABLE --
-- DROP TABLE IF EXISTS public.i_type_adhesion CASCADE;
CREATE TABLE public.i_type_adhesion (
	id_type_adhesion numeric(4,0) NOT NULL,
	label character varying(20) NOT NULL,
	CONSTRAINT pk_i_type_adhesion PRIMARY KEY (id_type_adhesion)

);
-- ddl-end --
COMMENT ON TABLE public.i_type_adhesion IS E'type d''adhésion possible';
-- ddl-end --
-- ALTER TABLE public.i_type_adhesion OWNER TO postgres;
-- ddl-end --

-- object: public.i_type_authority | type: TABLE --
-- DROP TABLE IF EXISTS public.i_type_authority CASCADE;
CREATE TABLE public.i_type_authority (
	id_type_authority numeric(2,0) NOT NULL,
	authority character varying(10) NOT NULL,
	CONSTRAINT i_type_authority_pk PRIMARY KEY (id_type_authority)

);
-- ddl-end --
COMMENT ON TABLE public.i_type_authority IS E'les types d''authority. Attention, en lien avec la base d''authentification authorities';
-- ddl-end --
-- ALTER TABLE public.i_type_authority OWNER TO postgres;
-- ddl-end --

-- object: public.r_adh_evenement | type: TABLE --
-- DROP TABLE IF EXISTS public.r_adh_evenement CASCADE;
CREATE TABLE public.r_adh_evenement (
	fk_id_adherent numeric(9,0) NOT NULL,
	fk_id_evenement numeric(20,0) NOT NULL,
	date_enregistrement timestamp with time zone,
	participe_evenement boolean NOT NULL DEFAULT true,
	CONSTRAINT pk_r_adh_evenement PRIMARY KEY (fk_id_adherent,fk_id_evenement)

);
-- ddl-end --
COMMENT ON TABLE public.r_adh_evenement IS E'table de participation des personnes à un evenement';
-- ddl-end --
COMMENT ON COLUMN public.r_adh_evenement.date_enregistrement IS E'date d''enregistrement de la participation';
-- ddl-end --
COMMENT ON COLUMN public.r_adh_evenement.participe_evenement IS E'indique si l adherent participe à la manifestation';
-- ddl-end --
-- ALTER TABLE public.r_adh_evenement OWNER TO postgres;
-- ddl-end --

-- object: public.r_groupe_diffusion_adherents | type: TABLE --
-- DROP TABLE IF EXISTS public.r_groupe_diffusion_adherents CASCADE;
CREATE TABLE public.r_groupe_diffusion_adherents (
	pk_id_groupe_diffusion numeric(20,0) NOT NULL,
	pk_id_adherent numeric(9,0) NOT NULL,
	date_enregistrement timestamp with time zone,
	CONSTRAINT pk_r_groupe_diffusion_adherent PRIMARY KEY (pk_id_groupe_diffusion,pk_id_adherent)

);
-- ddl-end --
COMMENT ON TABLE public.r_groupe_diffusion_adherents IS E'relation entre un groupe de diffusion et un adhérents';
-- ddl-end --
COMMENT ON COLUMN public.r_groupe_diffusion_adherents.date_enregistrement IS E'date d''enregistrement à un groupe de diffusion';
-- ddl-end --
-- ALTER TABLE public.r_groupe_diffusion_adherents OWNER TO postgres;
-- ddl-end --

-- object: public.seq_t_adherents | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.seq_t_adherents CASCADE;
CREATE SEQUENCE public.seq_t_adherents
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.seq_t_adherents OWNER TO postgres;
-- ddl-end --

-- object: public.seq_t_adhesion | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.seq_t_adhesion CASCADE;
CREATE SEQUENCE public.seq_t_adhesion
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.seq_t_adhesion OWNER TO postgres;
-- ddl-end --

-- object: public.seq_t_detail_calendar | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.seq_t_detail_calendar CASCADE;
CREATE SEQUENCE public.seq_t_detail_calendar
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.seq_t_detail_calendar OWNER TO postgres;
-- ddl-end --

-- object: public.seq_t_evenement | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.seq_t_evenement CASCADE;
CREATE SEQUENCE public.seq_t_evenement
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.seq_t_evenement OWNER TO postgres;
-- ddl-end --

-- object: public.seq_t_groupe_diffusion | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.seq_t_groupe_diffusion CASCADE;
CREATE SEQUENCE public.seq_t_groupe_diffusion
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.seq_t_groupe_diffusion OWNER TO postgres;
-- ddl-end --

-- object: public.seq_t_share_docs | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.seq_t_share_docs CASCADE;
CREATE SEQUENCE public.seq_t_share_docs
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9999999
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.seq_t_share_docs OWNER TO postgres;
-- ddl-end --

-- object: public.t_evenement | type: TABLE --
-- DROP TABLE IF EXISTS public.t_evenement CASCADE;
CREATE TABLE public.t_evenement (
	id_evenement numeric(20,0) NOT NULL,
	description_courte character varying(100) NOT NULL,
	detail_text text,
	lieux character varying(100),
	date_debut timestamp with time zone NOT NULL,
	date_fin timestamp with time zone NOT NULL,
	fk_id_type_authority numeric(2,0),
	besoin_confirm_participation boolean NOT NULL DEFAULT false,
	demande_communication boolean NOT NULL DEFAULT false,
	CONSTRAINT pk_t_evenement PRIMARY KEY (id_evenement)

);
-- ddl-end --
COMMENT ON COLUMN public.t_evenement.description_courte IS E'description courte de l''evenement';
-- ddl-end --
COMMENT ON COLUMN public.t_evenement.date_debut IS E'date et heure de debut de l''evenement';
-- ddl-end --
COMMENT ON COLUMN public.t_evenement.date_fin IS E'date de fin de l''evenement';
-- ddl-end --
COMMENT ON COLUMN public.t_evenement.besoin_confirm_participation IS E'indique si il y a un besoin de confirmation de participation à la manifestation';
-- ddl-end --
COMMENT ON COLUMN public.t_evenement.demande_communication IS E'Demande une communication de l''évènement par mail';
-- ddl-end --
-- ALTER TABLE public.t_evenement OWNER TO postgres;
-- ddl-end --

-- object: public.t_groupe_diffusion | type: TABLE --
-- DROP TABLE IF EXISTS public.t_groupe_diffusion CASCADE;
CREATE TABLE public.t_groupe_diffusion (
	id_groupe_diffusion numeric(20,0) NOT NULL,
	description character varying(30) NOT NULL,
	fk_id_type_authority numeric(2,0),
	CONSTRAINT pk_t_groupe_diffusion PRIMARY KEY (id_groupe_diffusion)

);
-- ddl-end --
COMMENT ON TABLE public.t_groupe_diffusion IS E'liste des groupes de diffusion';
-- ddl-end --
-- ALTER TABLE public.t_groupe_diffusion OWNER TO postgres;
-- ddl-end --

-- object: public.t_share_docs | type: TABLE --
-- DROP TABLE IF EXISTS public.t_share_docs CASCADE;
CREATE TABLE public.t_share_docs (
	id_share_docs numeric(20,0) NOT NULL,
	fk_id_share_docs numeric(20,0),
	label_short character varying(30) NOT NULL,
	label_long character varying(50),
	date_save date NOT NULL,
	is_folder boolean NOT NULL,
	is_file boolean NOT NULL,
	filer character varying(50),
	fk_id_user_created numeric(9,0) NOT NULL,
	fk_id_type_authority numeric(2,0) NOT NULL,
	CONSTRAINT pk_t_share_docs PRIMARY KEY (id_share_docs)

);
-- ddl-end --
COMMENT ON TABLE public.t_share_docs IS E'table des fichiers et dossier partagés';
-- ddl-end --
COMMENT ON COLUMN public.t_share_docs.label_short IS E'description court du fichier ou dossier';
-- ddl-end --
COMMENT ON COLUMN public.t_share_docs.label_long IS E'description long du fichier ou dossier';
-- ddl-end --
COMMENT ON COLUMN public.t_share_docs.is_folder IS E'si c''est un dossier';
-- ddl-end --
COMMENT ON COLUMN public.t_share_docs.is_file IS E'su c''est un fichier';
-- ddl-end --
COMMENT ON COLUMN public.t_share_docs.filer IS E'si c''est un dossier, pas besoins de filer';
-- ddl-end --
COMMENT ON COLUMN public.t_share_docs.fk_id_type_authority IS E'type d''e profil qui peut accéder à ces documents partagés';
-- ddl-end --
-- ALTER TABLE public.t_share_docs OWNER TO postgres;
-- ddl-end --

-- object: index_annee_adh | type: INDEX --
-- DROP INDEX IF EXISTS public.index_annee_adh CASCADE;
CREATE INDEX index_annee_adh ON public.i_annee_adhesion
	USING btree
	(
	  id_annee_adhesion
	)
	WITH (FILLFACTOR = 90);
-- ddl-end --

-- object: index_2 | type: INDEX --
-- DROP INDEX IF EXISTS public.index_2 CASCADE;
CREATE INDEX index_2 ON public.i_type_adhesion
	USING btree
	(
	  id_type_adhesion
	)
	WITH (FILLFACTOR = 90);
-- ddl-end --

-- object: index_4 | type: INDEX --
-- DROP INDEX IF EXISTS public.index_4 CASCADE;
CREATE INDEX index_4 ON public.r_adh_evenement
	USING btree
	(
	  fk_id_adherent
	)
	WITH (FILLFACTOR = 90);
-- ddl-end --

-- object: index_id_adherents | type: INDEX --
-- DROP INDEX IF EXISTS public.index_id_adherents CASCADE;
CREATE INDEX index_id_adherents ON public.t_adherents
	USING btree
	(
	  id_adherents
	)
	WITH (FILLFACTOR = 90);
-- ddl-end --

-- object: index_7 | type: INDEX --
-- DROP INDEX IF EXISTS public.index_7 CASCADE;
CREATE UNIQUE INDEX index_7 ON public.t_adhesions
	USING btree
	(
	  fk_id_adherents,
	  fk_id_annee_adhesions
	)
	WITH (FILLFACTOR = 90);
-- ddl-end --

-- object: index_unique_token_adherents | type: INDEX --
-- DROP INDEX IF EXISTS public.index_unique_token_adherents CASCADE;
CREATE UNIQUE INDEX index_unique_token_adherents ON public.t_adherents
	USING btree
	(
	  token_acces
	)
	WITH (FILLFACTOR = 90);
-- ddl-end --

-- object: index_unique_mail | type: INDEX --
-- DROP INDEX IF EXISTS public.index_unique_mail CASCADE;
CREATE UNIQUE INDEX index_unique_mail ON public.t_adherents
	USING btree
	(
	  e_mail
	)
	WITH (FILLFACTOR = 90);
-- ddl-end --

-- object: index_pk_evenement | type: INDEX --
-- DROP INDEX IF EXISTS public.index_pk_evenement CASCADE;
CREATE INDEX index_pk_evenement ON public.t_evenement
	USING btree
	(
	  id_evenement
	)
	WITH (FILLFACTOR = 90);
-- ddl-end --

-- object: index_5 | type: INDEX --
-- DROP INDEX IF EXISTS public.index_5 CASCADE;
CREATE INDEX index_5 ON public.r_adh_evenement
	USING btree
	(
	  fk_id_evenement
	)
	WITH (FILLFACTOR = 90);
-- ddl-end --

-- object: public.r_relation_adherent | type: TABLE --
-- DROP TABLE IF EXISTS public.r_relation_adherent CASCADE;
CREATE TABLE public.r_relation_adherent (
	fk_id_adherent_representant numeric(9,0) NOT NULL,
	fk_id_adherent_represente numeric(9,0) NOT NULL,
	CONSTRAINT pk_r_relation_adherent PRIMARY KEY (fk_id_adherent_representant,fk_id_adherent_represente)

);
-- ddl-end --
COMMENT ON COLUMN public.r_relation_adherent.fk_id_adherent_representant IS E'Id de adherent représentant';
-- ddl-end --
COMMENT ON COLUMN public.r_relation_adherent.fk_id_adherent_represente IS E'id de adherent reprensenté';
-- ddl-end --
COMMENT ON CONSTRAINT pk_r_relation_adherent ON public.r_relation_adherent  IS E'clee primaire de la table r_relation_adherent';
-- ddl-end --
-- ALTER TABLE public.r_relation_adherent OWNER TO postgres;
-- ddl-end --

-- object: fk_t_adhesi_reference_i_annee_ | type: CONSTRAINT --
-- ALTER TABLE public.t_adhesions DROP CONSTRAINT IF EXISTS fk_t_adhesi_reference_i_annee_ CASCADE;
ALTER TABLE public.t_adhesions ADD CONSTRAINT fk_t_adhesi_reference_i_annee_ FOREIGN KEY (fk_id_annee_adhesions)
REFERENCES public.i_annee_adhesion (id_annee_adhesion) MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE RESTRICT;
-- ddl-end --

-- object: fk_t_adhesi_reference_i_type_a | type: CONSTRAINT --
-- ALTER TABLE public.t_adhesions DROP CONSTRAINT IF EXISTS fk_t_adhesi_reference_i_type_a CASCADE;
ALTER TABLE public.t_adhesions ADD CONSTRAINT fk_t_adhesi_reference_i_type_a FOREIGN KEY (fk_id_type_adhesion)
REFERENCES public.i_type_adhesion (id_type_adhesion) MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE RESTRICT;
-- ddl-end --

-- object: fk_t_adhesi_reference_t_adhere | type: CONSTRAINT --
-- ALTER TABLE public.t_adhesions DROP CONSTRAINT IF EXISTS fk_t_adhesi_reference_t_adhere CASCADE;
ALTER TABLE public.t_adhesions ADD CONSTRAINT fk_t_adhesi_reference_t_adhere FOREIGN KEY (fk_id_adherents)
REFERENCES public.t_adherents (id_adherents) MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE RESTRICT;
-- ddl-end --

-- object: fk_r_adh_ev_reference_t_adhere | type: CONSTRAINT --
-- ALTER TABLE public.r_adh_evenement DROP CONSTRAINT IF EXISTS fk_r_adh_ev_reference_t_adhere CASCADE;
ALTER TABLE public.r_adh_evenement ADD CONSTRAINT fk_r_adh_ev_reference_t_adhere FOREIGN KEY (fk_id_adherent)
REFERENCES public.t_adherents (id_adherents) MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE RESTRICT;
-- ddl-end --

-- object: fk_r_adh_ev_reference_t_evenem | type: CONSTRAINT --
-- ALTER TABLE public.r_adh_evenement DROP CONSTRAINT IF EXISTS fk_r_adh_ev_reference_t_evenem CASCADE;
ALTER TABLE public.r_adh_evenement ADD CONSTRAINT fk_r_adh_ev_reference_t_evenem FOREIGN KEY (fk_id_evenement)
REFERENCES public.t_evenement (id_evenement) MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE RESTRICT;
-- ddl-end --

-- object: fk_r_groupe_reference_t_adhere | type: CONSTRAINT --
-- ALTER TABLE public.r_groupe_diffusion_adherents DROP CONSTRAINT IF EXISTS fk_r_groupe_reference_t_adhere CASCADE;
ALTER TABLE public.r_groupe_diffusion_adherents ADD CONSTRAINT fk_r_groupe_reference_t_adhere FOREIGN KEY (pk_id_adherent)
REFERENCES public.t_adherents (id_adherents) MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE RESTRICT;
-- ddl-end --

-- object: fk_r_groupe_reference_t_groupe | type: CONSTRAINT --
-- ALTER TABLE public.r_groupe_diffusion_adherents DROP CONSTRAINT IF EXISTS fk_r_groupe_reference_t_groupe CASCADE;
ALTER TABLE public.r_groupe_diffusion_adherents ADD CONSTRAINT fk_r_groupe_reference_t_groupe FOREIGN KEY (pk_id_groupe_diffusion)
REFERENCES public.t_groupe_diffusion (id_groupe_diffusion) MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE RESTRICT;
-- ddl-end --

-- object: fk_i_type_authority | type: CONSTRAINT --
-- ALTER TABLE public.t_evenement DROP CONSTRAINT IF EXISTS fk_i_type_authority CASCADE;
ALTER TABLE public.t_evenement ADD CONSTRAINT fk_i_type_authority FOREIGN KEY (fk_id_type_authority)
REFERENCES public.i_type_authority (id_type_authority) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_i_type_authority | type: CONSTRAINT --
-- ALTER TABLE public.t_groupe_diffusion DROP CONSTRAINT IF EXISTS fk_i_type_authority CASCADE;
ALTER TABLE public.t_groupe_diffusion ADD CONSTRAINT fk_i_type_authority FOREIGN KEY (fk_id_type_authority)
REFERENCES public.i_type_authority (id_type_authority) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_t_share__reference_t_adhere | type: CONSTRAINT --
-- ALTER TABLE public.t_share_docs DROP CONSTRAINT IF EXISTS fk_t_share__reference_t_adhere CASCADE;
ALTER TABLE public.t_share_docs ADD CONSTRAINT fk_t_share__reference_t_adhere FOREIGN KEY (fk_id_user_created)
REFERENCES public.t_adherents (id_adherents) MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE RESTRICT;
-- ddl-end --

-- object: fk_i_type_authority | type: CONSTRAINT --
-- ALTER TABLE public.t_share_docs DROP CONSTRAINT IF EXISTS fk_i_type_authority CASCADE;
ALTER TABLE public.t_share_docs ADD CONSTRAINT fk_i_type_authority FOREIGN KEY (fk_id_type_authority)
REFERENCES public.i_type_authority (id_type_authority) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_t_share__reference_t_share_ | type: CONSTRAINT --
-- ALTER TABLE public.t_share_docs DROP CONSTRAINT IF EXISTS fk_t_share__reference_t_share_ CASCADE;
ALTER TABLE public.t_share_docs ADD CONSTRAINT fk_t_share__reference_t_share_ FOREIGN KEY (fk_id_share_docs)
REFERENCES public.t_share_docs (id_share_docs) MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE RESTRICT;
-- ddl-end --

-- object: fk_id_adh_representant | type: CONSTRAINT --
-- ALTER TABLE public.r_relation_adherent DROP CONSTRAINT IF EXISTS fk_id_adh_representant CASCADE;
ALTER TABLE public.r_relation_adherent ADD CONSTRAINT fk_id_adh_representant FOREIGN KEY (fk_id_adherent_representant)
REFERENCES public.t_adherents (id_adherents) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_id_adh_represente | type: CONSTRAINT --
-- ALTER TABLE public.r_relation_adherent DROP CONSTRAINT IF EXISTS fk_id_adh_represente CASCADE;
ALTER TABLE public.r_relation_adherent ADD CONSTRAINT fk_id_adh_represente FOREIGN KEY (fk_id_adherent_represente)
REFERENCES public.t_adherents (id_adherents) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


