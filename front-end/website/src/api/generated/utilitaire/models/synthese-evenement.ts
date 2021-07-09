/* tslint:disable */
export interface SyntheseEvenement {

  /**
   * date de début au format iso8601
   */
  datedebut?: string;

  /**
   * date de fin au format iso8601
   */
  datefin?: string;

  /**
   * indique si il faut demander une confirmation de présence à l'adhérent
   */
  demanderConfirmationParticipation?: boolean;
  description?: string;
  detail?: string;

  /**
   * indique si le système doit envoyer un mail au adhérent à la suite de la création d'un évènement
   */
  envoyerInfoAdherents?: boolean;
  id?: number;

  /**
   * indique le nombre de participant en attente de retour
   */
  nbAdherentEnAttente?: number;

  /**
   * indique le nombre de participant à l evenement
   */
  nbAdherentParticipant?: number;

  /**
   * indique le nombre de participant pas à l evenement
   */
  nbAdherentParticipantPas?: number;

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
