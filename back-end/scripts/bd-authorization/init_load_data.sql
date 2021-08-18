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


/* admin@gmail.com / mot de passe : 123?456 */
insert into users (username, password, enabled, idAdherent) values ('admin@gmail.com', '$2a$10$HAPdjhc01NS9KcUhfvY5c.TLPZi4lvnhq6QwWc6P1bLdkmDKuAM52', true, 1);
insert into authorities (username, authority) values ('admin@gmail.com', 'ADMIN');
insert into authorities (username, authority) values ('admin@gmail.com', 'CONSEIL');
insert into authorities (username, authority) values ('admin@gmail.com', 'RES_ATELIER');
insert into authorities (username, authority) values ('admin@gmail.com', 'ADHERENT');

