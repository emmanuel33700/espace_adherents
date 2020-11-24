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
   * Description courte de l'évènement
   */
  descriptionCourte?: string;

  /**
   * Description longue de l'évènement
   */
  descriptionLongue?: string;

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
}
