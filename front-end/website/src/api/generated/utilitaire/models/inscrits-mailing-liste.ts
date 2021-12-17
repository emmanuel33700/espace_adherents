/* tslint:disable */
export interface InscritsMailingListe {

  /**
   * donne son accord pour recevoiur des mails
   */
  accordMail?: boolean;

  /**
   * Indique si l'adhérent est adhérent dans la saison courante
   */
  adhesionsSaisonCourante?: boolean;

  /**
   * civilite de l'adhenrent
   */
  civilite?: 'Mr' | 'Mme';
  email?: string;
  id?: number;

  /**
   * lien vers la photo de profile
   */
  lienPhotoProfil?: string;
  nom?: string;
  prenom?: string;

  /**
   * donne son accord afficher ces coordonnées dans l'espace adherents
   */
  publicContact?: boolean;

  /**
   * inscrit au la mailing liste
   */
  statutParticipation?: boolean;
}
