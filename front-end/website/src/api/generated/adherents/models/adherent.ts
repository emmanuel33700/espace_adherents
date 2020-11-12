/* tslint:disable */
export interface Adherent {

  /**
   * donne son accord pour recevoiur des mails
   */
  accordMail?: boolean;
  adresse1?: string;
  adresse2?: string;

  /**
   * civilite de l'adhenrent
   */
  civilite?: 'Mr' | 'Mme';
  codePostal?: string;
  commentaire?: string;
  dateEnregistrement?: string;
  dateMiseAJour?: string;
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
