/* tslint:disable */
/* eslint-disable */
export interface Actualitelecture {

  /**
   * date de creation de l'actualité
   */
  dateCreation?: string;

  /**
   * id de l'actualite
   */
  id?: number;

  /**
   * id de l'adhérent qui a créé l'actualité
   */
  idAdherent?: number;

  /**
   * Types d'adhersions. Valeurs possibles :
   * * 1 : ADHERENT               
   * * 2 : CONSEIL ADMINISTRATON             
   * * 3 : BUREAU               
   * * 4 : ADMIN
   */
  idAuthority?: 1 | 2 | 3 | 4;

  /**
   * libelle de l'actualite
   */
  libelleActualite?: string;
}
