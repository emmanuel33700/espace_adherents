COPY
( 
SELECT id_adherents FROM t_adherents
) TO '/var/lib/postgresql/rgpd/adh_actif_2024.csv' DELIMITER ';' CSV HEADER;
