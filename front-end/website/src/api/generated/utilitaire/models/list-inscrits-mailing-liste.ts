/* tslint:disable */
/* eslint-disable */
import { InscritsMailingListe } from '../models/inscrits-mailing-liste';
export interface ListInscritsMailingListe {

  /**
   * id de la liste de diffusion
   */
  id?: number;

  /**
   * Libelle de la liste de diffusion
   */
  libelle?: string;
  lstAdherents?: Array<InscritsMailingListe>;
}
