CREATE TABLE IF NOT EXISTS adh_actif_24 (
id_adherents numeric(9,0) NOT NULL
);

COPY adh_actif_24(id_adherents) from  '/var/lib/postgresql/rgpd/adh_actif_2024.csv' DELIMITER ';' CSV HEADER;

DELETE FROM USERS
Where USERS.username not in (
	Select distinct USERS.username
	From USERS, authorities, adh_actif_24
	Where USERS.username = authorities.username
	and USERS.idAdherent = adh_actif_24.id_adherents
);

DELETE FROM authorities
Where authorities.username not in (
	Select distinct USERS.username
	From USERS, authorities, adh_actif_24
	Where USERS.username = authorities.username
	and USERS.idAdherent = adh_actif_24.id_adherents
);
