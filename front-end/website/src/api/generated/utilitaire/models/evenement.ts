/* tslint:disable */
export interface Evenement {

  /**
   * date de début au format iso8601
   */
  datedebut?: string;

  /**
   * date de fin au format iso8601
   */
  datefin?: string;
  description?: string;
  detail?: string;
  id?: number;

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
