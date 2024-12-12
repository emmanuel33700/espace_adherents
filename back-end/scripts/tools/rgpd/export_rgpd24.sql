COPY
(
SELECT id_adherents, nom, premon, code_postal, ville, date_enregistrement, civilite, i_annee_adhesion.libelle_annee
  FROM t_adherents, t_adhesions, i_annee_adhesion
  where id_adherents not in (
                  SELECT fk_id_adherents
                  FROM public.t_adhesions
                  where (fk_id_annee_adhesions = 27 OR fk_id_annee_adhesions = 28)
    )
        and  t_adhesions.fk_id_adherents = t_adherents.id_adherents
        and i_annee_adhesion.id_annee_adhesion = t_adhesions.fk_id_annee_adhesions
UNION		
SELECT id_adherents, nom, premon, code_postal, ville, date_enregistrement, civilite, 'NA' as libelle_annee
  FROM t_adherents
  where id_adherents not in (
                  SELECT fk_id_adherents
                  FROM public.t_adhesions
    )
        and  t_adherents.date_enregistrement < '2024-09-30'
		ORDER BY nom
) TO '/var/lib/postgresql/export_rgpd_2024.csv' DELIMITER ';' CSV HEADER;
