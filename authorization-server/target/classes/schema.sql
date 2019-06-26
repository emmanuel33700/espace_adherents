--------------- POSTGRESQL ---------------
/*==============================================================*/
/* Table : T_UTILISATEUR                                        */
/*==============================================================*/

CREATE TABLE IF NOT EXISTS  USERS(
	username VARCHAR(50) not null primary key,
	password VARCHAR(200) not null,
	enabled boolean not null
);

CREATE TABLE IF NOT EXISTS  AUTHORITIES (
	username VARCHAR(50) not null,
	authority VARCHAR(50) not null,
	constraint fk_authorities_users foreign key(username) references USERS(username)
);


/*
aide sur la création des droits dans PG ADMIN
https://support.chartio.com/knowledgebase/creating-a-user-with-pgadmin

*/
