/* tslint:disable */
export interface Communication {

  /**
   * UUID du message
   */
  UUID?: string;

  /**
   * date d'arrive du message (format ISO8601)
   */
  dateArrive?: string;

  /**
   * mail de l'adherent qui recoit le message
   */
  destinataire?: string;

  /**
   * Id du message envoyé
   */
  id?: number;

  /**
   * Règles de SpamAssassin correspondantes.
   */
  regleSpam?: string;

  /**
   * SpamassassinScore pour le message
   */
  scoreSpam?: number;

  /**
   * statut du mail
   */
  statut?: 'unknown' | 'queued' | 'sent' | 'opened' | 'clicked' | 'bounce' | 'spam' | 'unsub' | 'blocked' | 'hardbounced' | 'softbounced' | 'deferred';

  /**
   * sujet du message envoyé
   */
  sujet?: string;

  /**
   * ID numérique unique expliquant pourquoi le message n'a pas été remis avec succès au destinataire. Uniquement renvoyé si le message n'a pas été remis avec succès. Valeur possible :
   * * 1 : user unknown (recipient)
   * * 2 : mailbox inactive (recipient)
   * * 3 : quota exceeded (recipient)
   * * 4 : invalid domain (domain)
   * * 5 : no mail host (domain)
   * * 6 : relay/access denied (domain)
   * * 7 : sender blocked (spam)
   * * 8 : content blocked (spam)
   * * 9 : policy issue (spam)
   * * 10 : system issue (system)
   * * 11 : protocol issue (system)
   * * 12 : connection issue (system)
   * * 13 : greylisted (domain)
   * * 14 : preblocked (provider)
   * * 15 : duplicate in campaign (provider)
   * * 16 : spam preblocked (provider)
   * * 17 : bad or empty template (content)
   * * 18 : error in template language (content)
   * * 19 : typofix (domain)
   * * 20 : blacklisted (recipient)
   * * 21 : spam reporter (recipient)
   */
  typeErreur?: number;
}
