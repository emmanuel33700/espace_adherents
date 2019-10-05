--------------- POSTGRESQL ---------------


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

