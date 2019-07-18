--------------- POSTGRESQL ---------------
/*==============================================================*/
/* Table : T_UTILISATEUR                                        */
/*==============================================================*/

CREATE TABLE IF NOT EXISTS  USERS(
	username VARCHAR(50) not null primary key,
	password VARCHAR(200) not null,
	enabled boolean not null,
        idAdherent NUMERIC(9,0) NOT NULL,
        dateCreation timestamp with time zone,
        dateModif timestamp with time zone,
        dateConnexion timestamp with time zone,
        cleeModification VARCHAR(50),
        dateModificationClee timestamp with time zone
);

CREATE TABLE IF NOT EXISTS  AUTHORITIES (
	username VARCHAR(50) not null,
	authority VARCHAR(50) not null,
	constraint fk_authorities_users foreign key(username) references USERS(username)
);

GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE public.authorities TO GROUP authentification;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE public.users TO GROUP authentification;

/*
aide sur la création des droits dans PG ADMIN
https://support.chartio.com/knowledgebase/creating-a-user-with-pgadmin

*/
