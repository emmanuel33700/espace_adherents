/* tslint:disable */
export interface Communication {

  /**
   * Date d'envoi
   */
  dateEnvoi?: string;

  /**
   * Types d'envement. Valeurs possibles :
   * * accepted : Provider accepted the request to send/forward the email and the message has been placed in queue.
   * * rejected : Provider rejected the request to send/forward the email.
   * * delivered : Provider sent the email and it was accepted by the recipient email server.
   * * failed : Provider could not deliver the email to the recipient email server.
   * * opened : The email recipient opened the email and enabled image viewing. Open tracking must be enabled in the Mailgun control panel, and the CNAME record must be pointing to Provider
   * * clicked : The email recipient clicked on a link in the email. Click tracking must be enabled in the Provider control panel, and the CNAME record must be pointing to Provider 
   * * unsubscribed : The email recipient clicked on the unsubscribe link. Unsubscribe tracking must be enabled in the Provider  control panel.
   * * complained : The email recipient clicked on the spam complaint button within their email client. Feedback loops enable the notification to be received by Provider 
   * * stored : Provider has stored an incoming message
   * * list_member_uploaded : This event occurs after successfully adding a member to a mailing list.
   * * list_member_upload_error : This event occurs if an error occurs adding a member to a mailing list.
   * * list_uploaded : This event occurs after successfully uploading a large list of members to a mailing list.
   */
  evenement?: 'accepted' | 'rejected' | 'delivered' | 'failed' | 'opened' | 'clicked' | 'unsubscribed' | 'complained' | 'stored' | 'list_member_uploaded' | 'list_member_upload_error' | 'list_uploaded';

  /**
   * mail de l'adherent qui recoit le message
   */
  messageFrom?: string;

  /**
   * id du message
   */
  messageID?: string;

  /**
   * sujet du message
   */
  messageSujet?: string;

  /**
   * mail de l'adherent qui recoit le message
   */
  messageTo?: string;
}
