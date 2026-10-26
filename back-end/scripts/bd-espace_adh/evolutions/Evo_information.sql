{\rtf1\ansi\ansicpg1252\cocoartf2761
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww28600\viewh17440\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 -- object: public.t_information | type: TABLE --\
-- DROP TABLE IF EXISTS public.t_information CASCADE;\
CREATE TABLE public.t_information (\
	id_information numeric(9,0) NOT NULL,\
	information text,\
	date_enregistrement date NOT NULL,\
	fk_id_adherent numeric(4) NOT NULL,\
	fk_id_type_authority numeric(2) NOT NULL,\
	CONSTRAINT t_information_pk PRIMARY KEY (id_information)\
\
);\
-- ddl-end --\
COMMENT ON TABLE public.t_information IS E'table contenant les informations';\
-- ddl-end --\
COMMENT ON COLUMN public.t_information.fk_id_type_authority IS E'permet de restriendre la publication \'e0 un type de groupe d''adh\'e9rents';\
-- ddl-end --\
-- ALTER TABLE public.t_information OWNER TO postgres;\
-- ddl-end --\
\
-- object: public.seq_information | type: SEQUENCE --\
-- DROP SEQUENCE IF EXISTS public.seq_information CASCADE;\
CREATE SEQUENCE public.seq_information\
	INCREMENT BY 1\
	MINVALUE 0\
	MAXVALUE 2147483647\
	START WITH 1\
	CACHE 1\
	NO CYCLE\
	OWNED BY NONE;\
-- ddl-end --\
-- ALTER SEQUENCE public.seq_information OWNER TO postgres;\
-- ddl-end --\
COMMENT ON SEQUENCE public.seq_information IS E'sequence pour la table information';\
-- ddl-end --\
\
\
-- object: fk_id_adherent | type: CONSTRAINT --\
-- ALTER TABLE public.t_information DROP CONSTRAINT IF EXISTS fk_id_adherent CASCADE;\
ALTER TABLE public.t_information ADD CONSTRAINT fk_id_adherent FOREIGN KEY (fk_id_adherent)\
REFERENCES public.t_adherents (id_adherents) MATCH FULL\
ON DELETE NO ACTION ON UPDATE NO ACTION;\
-- ddl-end --\
\
-- object: fk_id_type_authority | type: CONSTRAINT --\
-- ALTER TABLE public.t_information DROP CONSTRAINT IF EXISTS fk_id_type_authority CASCADE;\
ALTER TABLE public.t_information ADD CONSTRAINT fk_id_type_authority FOREIGN KEY (fk_id_type_authority)\
REFERENCES public.i_type_authority (id_type_authority) MATCH FULL\
ON DELETE NO ACTION ON UPDATE NO ACTION;\
-- ddl-end --}