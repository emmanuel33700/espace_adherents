/* tslint:disable */
export interface Document {

  /**
   * Date de création (format ISO8601)
   */
  dateCreation?: string;

  /**
   * id du document
   */
  id?: number;

  /**
   * Types d'adhersions. Valeurs possibles :
   * * 1 : ADHERENT               
   * * 2 : CONSEIL ADMINISTRATON             
   * * 3 : BUREAU               
   * * 4 : ADMIN
   */
  idAuthority?: 1 | 2 | 3 | 4;

  /**
   * id du dossier de rattachement
   */
  idDossierRattachement?: number;

  /**
   * libelle du document
   */
  libelleCourt?: string;

  /**
   * libelle du document
   */
  libelleLong?: string;

  /**
   * nom du fichier
   */
  nomFichier?: string;
}
