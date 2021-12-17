/* tslint:disable */
export interface ListeDiffusion {

  /**
   * id de la liste de diffusion
   */
  id?: number;

  /**
   * Types d'adhersions pouvant accéder à la liste de diffusion. Valeurs possibles :
   * * 1 : ADHERENT               
   * * 2 : CONSEIL ADMINISTRATON             
   * * 3 : BUREAU               
   * * 4 : ADMIN
   */
  idAuthority?: number;

  /**
   * Libelle de la liste de diffusion
   */
  libelle?: string;
}
