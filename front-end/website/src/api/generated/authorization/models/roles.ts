/* tslint:disable */
export interface Roles {
  login?: string;

  /**
   * Role de la personnes
   */
  roles?: Array<'ADHERENT' | 'RES_ATELIER' | 'CONSEIL' | 'ADMIN'>;
}
