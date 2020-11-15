/* tslint:disable */
export interface Adhesion {

  /**
   * Indique si paiement par cheque
   */
  cheque?: boolean;

  /**
   * Nom de la banque si paiement par cheque
   */
  comptaBanque?: string;

  /**
   * Num du cheque si paiement par cheque
   */
  comptaNumCheque?: string;

  /**
   * Somme payé par l'adherent pour cette adhesion
   */
  comptaSomme?: number;

  /**
   * Indique si paiement par espece
   */
  espace?: boolean;

  /**
   * Id de l'adhesion
   */
  id?: number;

  /**
   * Id de l'adherent
   */
  idAdherent?: number;

  /**
   * Id anne adhesion
   */
  idAnneeAdhesion?: number;

  /**
   * Types d'adhersions. Valeurs possibles :
   * * 1 : ADULTE               
   * * 2 : FAMILLE             
   * * 3 : RESPONSABLE DE FAMILLE                 
   * * 4 : ENFANT
   * * 5 : BIENFAITEUR                  
   * * 6 : HONNEUR 
   * * 7 : ETUDIANT          
   * * 8 : DEMANDEUR EMPLOI
   */
  idTypeAdhesion?: 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8;
}
