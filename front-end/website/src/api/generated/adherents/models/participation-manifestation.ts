/* tslint:disable */
export interface ParticipationManifestation {

  /**
   * Id de la manifestation
   */
  id?: number;

  /**
   * Id de l'adherent
   */
  idAdherent?: number;

  /**
   * Types de participation. Valeurs possibles :
   * * 1 : PARTICIPE               
   * * 2 : PARTICIPE PAS             
   * * 3 : NE SAIS PAS
   */
  statutParticipation?: 1 | 2 | 3;
}
