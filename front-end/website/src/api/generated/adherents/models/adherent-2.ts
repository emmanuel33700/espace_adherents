/* tslint:disable */
/* eslint-disable */
import { Adherent } from '../models/adherent';
import { Adhesion } from '../models/adhesion';
export interface Adherent2 {

  /**
   * détail info adhérents
   */
  adherent?: Adherent;

  /**
   * détail info adhésion courante
   */
  adhesion?: Adhesion;
}
