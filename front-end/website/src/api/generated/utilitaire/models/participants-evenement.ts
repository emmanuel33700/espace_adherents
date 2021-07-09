/* tslint:disable */
export interface ParticipantsEvenement {

  /**
   * donne son accord pour recevoiur des mails
   */
  accordMail?: boolean;

  /**
   * Indique si l'adhérent est adhérent dans la saison courante
   */
  adhesionsSaisonCourante?: boolean;
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

  /**
   * Types de participation. Valeurs possibles :
   * * 1 : PARTICIPE               
   * * 2 : PARTICIPE PAS             
   * * 3 : NE SAIS PAS
   */
  statutParticipation?: 1 | 2 | 3;
  telMaison?: string;
  telPortable?: string;
  telTravail?: string;
  ville?: string;
}
