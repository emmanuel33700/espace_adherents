/* tslint:disable */
export interface MailAEnvoyer {
  email?: string;

  /**
   * id de la liste de diffusion
   */
  idListeDiffusion?: number;

  /**
   * Titre du mail
   */
  titreEmail?: string;

  /**
   * Types evenement
   * * 1 : ADHERENT DE SAISON COURANTE               
   * * 2 : ADHERENT DE SAISON COURANTE + SAISON -1     
   * * 4 : ADHERENT NON RENOUVELE ADH POUR LA SAISION S
   * * 10 : MAILING LISTE
   */
  typeMail?: 1 | 2 | 4 | 10;
}
