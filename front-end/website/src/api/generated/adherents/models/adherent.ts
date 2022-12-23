/* tslint:disable */
export interface Adherent {

  /**
   * donne son accord pour recevoiur des mails
   */
  accordMail?: boolean;

  /**
   * Indique si l'adhérent est adhérent dans la saison courante
   */
  adhesionsSaisonCourante?: boolean;

  /**
   * Indique si l'adhérent est adhérent dans la saison précédente
   */
  adhesionsSaisonPrecedente?: boolean;
  adresse1?: string;
  adresse2?: string;

  /**
   * civilite de l'adhenrent
   */
  civilite?: 'Mr' | 'Mme';
  codePostal?: string;
  commentaire?: string;

  /**
   * date de'enregistrement au format iso8601
   */
  dateEnregistrement?: string;

  /**
   * date de mise à jour au format iso8601
   */
  dateMiseAJour?: string;

  /**
   * date de naissance au format iso8601
   */
  dateNaissance?: string;
  email?: string;
  id?: number;

  /**
   * lien vers la photo de profile
   */
  lienPhotoProfil?: string;
  nom?: string;
  prenom?: string;
  profession?: string;

  /**
   * donne son accord afficher ces coordonnées dans l'espace adherents
   */
  publicContact?: boolean;
  telMaison?: string;
  telPortable?: string;
  telTravail?: string;
  ville?: string;
}
