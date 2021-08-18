--
-- PostgreSQL database dump
--

-- Dumped from database version 10.14 (Ubuntu 10.14-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.14 (Ubuntu 10.14-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


--
-- Data for Name: i_annee_adhesion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (2, '1998/1999', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (3, '1999/2000', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (4, '2000/2001', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (5, '2001/2002', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (6, '2002/2003', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (7, '2003/2004', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (8, '2004/2005', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (10, '2006/2007', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (11, '2007/2008', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (9, '2005/2006', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (12, '2008/2009', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (13, '2009/2010', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (14, '2010/2011', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (15, '2011/2012', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (16, '2012/2013', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (17, '2013/2014', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (18, '2014/2015', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (19, '2015/2016', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (20, '2016/2017', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (25, '2021/2022', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (21, '2017/2018', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (22, '2018/2019', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (26, '2022/2023', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (27, '2023/2024', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (28, '2024/2025', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (29, '2025/2026', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (30, '2026/2027', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (23, '2019/2020', false);
INSERT INTO public.i_annee_adhesion (id_annee_adhesion, libelle_annee, annee_courante) VALUES (24, '2020/2021', true);



--
-- Data for Name: i_type_adhesion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.i_type_adhesion (id_type_adhesion, label) VALUES (1, 'adulte');
INSERT INTO public.i_type_adhesion (id_type_adhesion, label) VALUES (2, 'Familiale');
INSERT INTO public.i_type_adhesion (id_type_adhesion, label) VALUES (3, 'Res Famille');
INSERT INTO public.i_type_adhesion (id_type_adhesion, label) VALUES (4, 'Enfant');
INSERT INTO public.i_type_adhesion (id_type_adhesion, label) VALUES (5, 'Bienfaiteur');
INSERT INTO public.i_type_adhesion (id_type_adhesion, label) VALUES (6, 'Honneur');
INSERT INTO public.i_type_adhesion (id_type_adhesion, label) VALUES (7, 'Etudiant');
INSERT INTO public.i_type_adhesion (id_type_adhesion, label) VALUES (8, 'Demandeur demploi');


--
-- Data for Name: i_type_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.i_type_authority (id_type_authority, authority) VALUES (1, 'NON ADH');
INSERT INTO public.i_type_authority (id_type_authority, authority) VALUES (2, 'ADHERENT');
INSERT INTO public.i_type_authority (id_type_authority, authority) VALUES (3, 'CONSEIL');
INSERT INTO public.i_type_authority (id_type_authority, authority) VALUES (4, 'BUREAU');
INSERT INTO public.i_type_authority (id_type_authority, authority) VALUES (5, 'ADMIN');


--
-- Data for Name: t_adherents; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.t_adherents (id_adherents, e_mail, civilite, nom, premon, adresse1, adresse2, code_postal, ville, tel1, tel2, tel3, date_maissance, profession, link_picture, public_contact, accord_mail, token_acces, commentaire, date_enregistrement, fk_id_adherents_update, update_date) VALUES (1, 'admin.admin@gmail.com', 'Mme', 'DELP.', 'Mireille', 'Rue sans nom', NULL, NULL, 'Saint-Médard-en-Jalles', NULL, '0707060677', NULL, NULL, NULL, NULL, true, false, NULL, NULL, '2021-03-01', 24, '2021-08-16 12:10:34.205+02');



--
-- Data for Name: t_share_docs; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.t_share_docs (id_share_docs, fk_id_share_docs, label_short, label_long, date_save, is_folder, is_file, filer, fk_id_user_created, fk_id_type_authority) VALUES (-1, NULL, 'racine', 'racine', '2021-04-18', false, false, NULL, 24, 2);


--
-- Name: seq_t_adherents; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_t_adherents', 2, true);





