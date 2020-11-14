package fr.espaceadh.adherents.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Communication
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-11-14T09:31:23.328Z[GMT]")


public class Communication   {
  @JsonProperty("messageID")
  private String messageID = null;

  @JsonProperty("dateEnvoi")
  private OffsetDateTime dateEnvoi = null;

  @JsonProperty("messageTo")
  private String messageTo = null;

  @JsonProperty("messageFrom")
  private String messageFrom = null;

  @JsonProperty("messageSujet")
  private String messageSujet = null;

  /**
   * Types d'envement. Valeurs possibles : * accepted : Provider accepted the request to send/forward the email and the message has been placed in queue. * rejected : Provider rejected the request to send/forward the email. * delivered : Provider sent the email and it was accepted by the recipient email server. * failed : Provider could not deliver the email to the recipient email server. * opened : The email recipient opened the email and enabled image viewing. Open tracking must be enabled in the Mailgun control panel, and the CNAME record must be pointing to Provider * clicked : The email recipient clicked on a link in the email. Click tracking must be enabled in the Provider control panel, and the CNAME record must be pointing to Provider  * unsubscribed : The email recipient clicked on the unsubscribe link. Unsubscribe tracking must be enabled in the Provider  control panel. * complained : The email recipient clicked on the spam complaint button within their email client. Feedback loops enable the notification to be received by Provider  * stored : Provider has stored an incoming message * list_member_uploaded : This event occurs after successfully adding a member to a mailing list. * list_member_upload_error : This event occurs if an error occurs adding a member to a mailing list. * list_uploaded : This event occurs after successfully uploading a large list of members to a mailing list. 
   */
  public enum EvenementEnum {
    ACCEPTED("accepted"),
    
    REJECTED("rejected"),
    
    DELIVERED("delivered"),
    
    FAILED("failed"),
    
    OPENED("opened"),
    
    CLICKED("clicked"),
    
    UNSUBSCRIBED("unsubscribed"),
    
    COMPLAINED("complained"),
    
    STORED("stored"),
    
    LIST_MEMBER_UPLOADED("list_member_uploaded"),
    
    LIST_MEMBER_UPLOAD_ERROR("list_member_upload_error"),
    
    LIST_UPLOADED("list_uploaded");

    private String value;

    EvenementEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EvenementEnum fromValue(String text) {
      for (EvenementEnum b : EvenementEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("evenement")
  private EvenementEnum evenement = null;

  public Communication messageID(String messageID) {
    this.messageID = messageID;
    return this;
  }

  /**
   * id du message
   * @return messageID
   **/
  @Schema(example = "20130812164300.28108.52546@samples.mailgun.org", description = "id du message")
  
  @Size(min=2,max=100)   public String getMessageID() {
    return messageID;
  }

  public void setMessageID(String messageID) {
    this.messageID = messageID;
  }

  public Communication dateEnvoi(OffsetDateTime dateEnvoi) {
    this.dateEnvoi = dateEnvoi;
    return this;
  }

  /**
   * Date d'envoi
   * @return dateEnvoi
   **/
  @Schema(description = "Date d'envoi")
  
    @Valid
    public OffsetDateTime getDateEnvoi() {
    return dateEnvoi;
  }

  public void setDateEnvoi(OffsetDateTime dateEnvoi) {
    this.dateEnvoi = dateEnvoi;
  }

  public Communication messageTo(String messageTo) {
    this.messageTo = messageTo;
    return this;
  }

  /**
   * mail de l'adherent qui recoit le message
   * @return messageTo
   **/
  @Schema(example = "user@example.comg", description = "mail de l'adherent qui recoit le message")
  
  @Size(min=2,max=100)   public String getMessageTo() {
    return messageTo;
  }

  public void setMessageTo(String messageTo) {
    this.messageTo = messageTo;
  }

  public Communication messageFrom(String messageFrom) {
    this.messageFrom = messageFrom;
    return this;
  }

  /**
   * mail de l'adherent qui recoit le message
   * @return messageFrom
   **/
  @Schema(example = "me@samples.mailgun.org", description = "mail de l'adherent qui recoit le message")
  
  @Size(min=2,max=100)   public String getMessageFrom() {
    return messageFrom;
  }

  public void setMessageFrom(String messageFrom) {
    this.messageFrom = messageFrom;
  }

  public Communication messageSujet(String messageSujet) {
    this.messageSujet = messageSujet;
    return this;
  }

  /**
   * sujet du message
   * @return messageSujet
   **/
  @Schema(example = "Hello", description = "sujet du message")
  
  @Size(min=2,max=100)   public String getMessageSujet() {
    return messageSujet;
  }

  public void setMessageSujet(String messageSujet) {
    this.messageSujet = messageSujet;
  }

  public Communication evenement(EvenementEnum evenement) {
    this.evenement = evenement;
    return this;
  }

  /**
   * Types d'envement. Valeurs possibles : * accepted : Provider accepted the request to send/forward the email and the message has been placed in queue. * rejected : Provider rejected the request to send/forward the email. * delivered : Provider sent the email and it was accepted by the recipient email server. * failed : Provider could not deliver the email to the recipient email server. * opened : The email recipient opened the email and enabled image viewing. Open tracking must be enabled in the Mailgun control panel, and the CNAME record must be pointing to Provider * clicked : The email recipient clicked on a link in the email. Click tracking must be enabled in the Provider control panel, and the CNAME record must be pointing to Provider  * unsubscribed : The email recipient clicked on the unsubscribe link. Unsubscribe tracking must be enabled in the Provider  control panel. * complained : The email recipient clicked on the spam complaint button within their email client. Feedback loops enable the notification to be received by Provider  * stored : Provider has stored an incoming message * list_member_uploaded : This event occurs after successfully adding a member to a mailing list. * list_member_upload_error : This event occurs if an error occurs adding a member to a mailing list. * list_uploaded : This event occurs after successfully uploading a large list of members to a mailing list. 
   * @return evenement
   **/
  @Schema(example = "opened", description = "Types d'envement. Valeurs possibles : * accepted : Provider accepted the request to send/forward the email and the message has been placed in queue. * rejected : Provider rejected the request to send/forward the email. * delivered : Provider sent the email and it was accepted by the recipient email server. * failed : Provider could not deliver the email to the recipient email server. * opened : The email recipient opened the email and enabled image viewing. Open tracking must be enabled in the Mailgun control panel, and the CNAME record must be pointing to Provider * clicked : The email recipient clicked on a link in the email. Click tracking must be enabled in the Provider control panel, and the CNAME record must be pointing to Provider  * unsubscribed : The email recipient clicked on the unsubscribe link. Unsubscribe tracking must be enabled in the Provider  control panel. * complained : The email recipient clicked on the spam complaint button within their email client. Feedback loops enable the notification to be received by Provider  * stored : Provider has stored an incoming message * list_member_uploaded : This event occurs after successfully adding a member to a mailing list. * list_member_upload_error : This event occurs if an error occurs adding a member to a mailing list. * list_uploaded : This event occurs after successfully uploading a large list of members to a mailing list. ")
  
  @Size(min=2,max=100)   public EvenementEnum getEvenement() {
    return evenement;
  }

  public void setEvenement(EvenementEnum evenement) {
    this.evenement = evenement;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Communication communication = (Communication) o;
    return Objects.equals(this.messageID, communication.messageID) &&
        Objects.equals(this.dateEnvoi, communication.dateEnvoi) &&
        Objects.equals(this.messageTo, communication.messageTo) &&
        Objects.equals(this.messageFrom, communication.messageFrom) &&
        Objects.equals(this.messageSujet, communication.messageSujet) &&
        Objects.equals(this.evenement, communication.evenement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageID, dateEnvoi, messageTo, messageFrom, messageSujet, evenement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Communication {\n");
    
    sb.append("    messageID: ").append(toIndentedString(messageID)).append("\n");
    sb.append("    dateEnvoi: ").append(toIndentedString(dateEnvoi)).append("\n");
    sb.append("    messageTo: ").append(toIndentedString(messageTo)).append("\n");
    sb.append("    messageFrom: ").append(toIndentedString(messageFrom)).append("\n");
    sb.append("    messageSujet: ").append(toIndentedString(messageSujet)).append("\n");
    sb.append("    evenement: ").append(toIndentedString(evenement)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
