/* tslint:disable */
export interface Manifestation {

  /**
   * Date de début de l'évènement (format ISO8601)
   */
  dateDebut?: string;

  /**
   * Date de fin de l'évènement
   */
  dateFin?: string;

  /**
   * indique si il faut demander une confirmation de présence à l'adhérent
   */
  demanderConfirmationParticipation?: boolean;

  /**
   * Description courte de l'évènement
   */
  descriptionCourte?: string;

  /**
   * Description longue de l'évènement
   */
  descriptionLongue?: string;

  /**
   * indique si le système doit envoyer un mail au adhérent à la suite de la création d'un évènement
   */
  envoyerInfoAdherents?: boolean;

  /**
   * Id de la manifestation
   */
  id?: number;

  /**
   * Id de l'adherent
   */
  idAdherent?: number;

  /**
   * Description du lieux de l'évènement
   */
  lieux?: string;

  /**
   * Types de participation. Valeurs possibles :
   * * 1 : PARTICIPE               
   * * 2 : PARTICIPE PAS             
   * * 3 : NE SAIS PAS
   */
  statutParticipation?: 1 | 2 | 3;

  /**
   * Types evenement
   * * 1 : CONFERENCE               
   * * 2 : MANIFESTATION            
   * * 3 : OBSERVATION
   * * 4 : ANIMATION
   * * 5 : REUNION
   * * 6 : SORTIE
   * * 7 : ATELIER
   * * 8 : DIVERS
   */
  type?: 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8;
}
