-- ATTENTION : Changer les variables :
--          => Au niveau de la 1er requete : préciser les id des saisions a conserver
--          => Aux niveau des dernieres requetes : préciser la borne avant laquelle les évènements doivent être supprimée


-- Création de la table qui contient les adhérents à supprimer
CREATE TABLE ADH_A_SUPP AS
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
    and  t_adherents.date_enregistrement < '2024-09-30';


--supression des participation dans  les groupes de diffusions
DELETE FROM r_groupe_diffusion_adherents
USING ADH_A_SUPP
where r_groupe_diffusion_adherents.pk_id_adherent = ADH_A_SUPP.id_adherents;

--supression de la participation aux évènements
DELETE FROM r_adh_evenement
USING ADH_A_SUPP
where r_adh_evenement.fk_id_adherent = ADH_A_SUPP.id_adherents;

--affectation à JA des documents créé par un adhérents à supprimer
UPDATE public.t_share_docs
   SET fk_id_user_created=382
 where fk_id_user_created in ( select id_adherents from ADH_A_SUPP);

-- supression des liens entres adhérents pour un adhérents représenté
DELETE FROM  r_relation_adherent
USING ADH_A_SUPP
 where r_relation_adherent.fk_id_adherent_represente = ADH_A_SUPP.id_adherents;
 
 -- supression des liens entres adhérents pour un adhérents représentant
DELETE FROM  r_relation_adherent
USING ADH_A_SUPP
 where r_relation_adherent.fk_id_adherent_representant = ADH_A_SUPP.id_adherents;

-- Supression des participation aux évènements pour les évènements à suprimer
DELETE FROM  r_adh_evenement 
where r_adh_evenement.fk_id_evenement IN (select id_evenement from t_evenement where date_fin <= '2023-12-31');

-- supression des évènements
DELETE FROM public.t_evenement
where date_fin <= '2023-12-31';

--supression des adhésions
DELETE FROM public.t_adhesions
USING ADH_A_SUPP
where t_adhesions.fk_id_adherents = ADH_A_SUPP.id_adherents;

-- supression des adhérents à supprimer
DELETE FROM public.t_adherents
USING ADH_A_SUPP
where t_adherents.id_adherents = ADH_A_SUPP.id_adherents;

-- supression de la table ADH_A_SUPP_2019
DROP TABLE ADH_A_SUPP;

