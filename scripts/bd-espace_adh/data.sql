--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5 (Ubuntu 11.5-0ubuntu0.19.04.1)
-- Dumped by pg_dump version 11.5 (Ubuntu 11.5-0ubuntu0.19.04.1)

-- Started on 2019-08-17 09:55:50 CEST

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
-- TOC entry 2985 (class 0 OID 17292)
-- Dependencies: 197
-- Data for Name: t_adherents; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_adherents (id_adherents, e_mail, civilite, nom, premon, adresse1, adresse2, code_postal, ville, tel1, tel2, tel3, date_maissance, profession, link_picture, public_contact, accord_mail, token_acces, commentaire, date_enregistrement, fk_id_adherents_update, update_date) FROM stdin;
3	admin.admin@gmail.com	Mr	ADMIN	Admin	6 rue des admin	Admin	\N	Merignac	0000000000	0000000000	0000000000	\N	administrateur	\N	f	t	\N	Compte d'administration pour démarer l'application	2019-08-17	\N	2019-08-17 09:53:45.442+02
\.


-- Completed on 2019-08-17 09:55:51 CEST

--
-- PostgreSQL database dump complete
--

