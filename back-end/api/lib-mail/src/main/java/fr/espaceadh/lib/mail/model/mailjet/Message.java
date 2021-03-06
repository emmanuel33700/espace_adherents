/*
 * Swagger mailjet
 * Swagger mailjet
 *
 * OpenAPI spec version: 1.0.0
 * Contact: manu.chenais@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package fr.espaceadh.lib.mail.model.mailjet;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;
/**
 * Message
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-11-15T20:39:15.462Z[GMT]")
public class Message {
  @SerializedName("ArrivedAt")
  private OffsetDateTime arrivedAt = null;

  @SerializedName("AttachmentCount")
  private Integer attachmentCount = null;

  @SerializedName("AttemptCount")
  private Integer attemptCount = null;

  @SerializedName("CampaignID")
  private Long campaignID = null;

  @SerializedName("ContactAlt")
  private String contactAlt = null;

  @SerializedName("ContactID")
  private Long contactID = null;

  @SerializedName("Delay")
  private Integer delay = null;

  @SerializedName("DestinationID")
  private Integer destinationID = null;

  @SerializedName("FilterTime")
  private Integer filterTime = null;

  @SerializedName("ID")
  private Long ID = null;

  @SerializedName("IsClickTracked")
  private Boolean isClickTracked = null;

  @SerializedName("IsHTMLPartIncluded")
  private Boolean isHTMLPartIncluded = null;

  @SerializedName("IsOpenTracked")
  private Boolean isOpenTracked = null;

  @SerializedName("IsTextPartIncluded")
  private Boolean isTextPartIncluded = null;

  @SerializedName("IsUnsubTracked")
  private Boolean isUnsubTracked = null;

  @SerializedName("MessageSize")
  private Integer messageSize = null;

  @SerializedName("SenderID")
  private Long senderID = null;

  @SerializedName("SpamassassinScore")
  private Integer spamassassinScore = null;

  @SerializedName("SpamassRules")
  private String spamassRules = null;

  @SerializedName("StateID")
  private Integer stateID = null;

  @SerializedName("StatePermanent")
  private Boolean statePermanent = null;

  /**
   * statut du mail
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    UNKNOWN("unknown"),
    QUEUED("queued"),
    SENT("sent"),
    OPENED("opened"),
    CLICKED("clicked"),
    BOUNCE("bounce"),
    SPAM("spam"),
    UNSUB("unsub"),
    BLOCKED("blocked"),
    HARDBOUNCED("hardbounced"),
    SOFTBOUNCED("softbounced"),
    DEFERRED("deferred");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return StatusEnum.fromValue(String.valueOf(value));
      }
    }
  }  @SerializedName("Status")
  private StatusEnum status = null;

  @SerializedName("Subject")
  private String subject = null;

  @SerializedName("UUID")
  private String UUID = null;

  public Message arrivedAt(OffsetDateTime arrivedAt) {
    this.arrivedAt = arrivedAt;
    return this;
  }

   /**
   * date d&#x27;arrive du message
   * @return arrivedAt
  **/
  @Schema(description = "date d'arrive du message")
  public OffsetDateTime getArrivedAt() {
    return arrivedAt;
  }

  public void setArrivedAt(OffsetDateTime arrivedAt) {
    this.arrivedAt = arrivedAt;
  }

  public Message attachmentCount(Integer attachmentCount) {
    this.attachmentCount = attachmentCount;
    return this;
  }

   /**
   * Nombre de fichier attaché
   * minimum: 0
   * maximum: 20
   * @return attachmentCount
  **/
  @Schema(example = "1", description = "Nombre de fichier attaché")
  public Integer getAttachmentCount() {
    return attachmentCount;
  }

  public void setAttachmentCount(Integer attachmentCount) {
    this.attachmentCount = attachmentCount;
  }

  public Message attemptCount(Integer attemptCount) {
    this.attemptCount = attemptCount;
    return this;
  }

   /**
   * Nombre de tentatice de remise de message
   * minimum: 0
   * maximum: 100
   * @return attemptCount
  **/
  @Schema(example = "1", description = "Nombre de tentatice de remise de message")
  public Integer getAttemptCount() {
    return attemptCount;
  }

  public void setAttemptCount(Integer attemptCount) {
    this.attemptCount = attemptCount;
  }

  public Message campaignID(Long campaignID) {
    this.campaignID = campaignID;
    return this;
  }

   /**
   * id de la camapgne
   * @return campaignID
  **/
  @Schema(example = "1", description = "id de la camapgne")
  public Long getCampaignID() {
    return campaignID;
  }

  public void setCampaignID(Long campaignID) {
    this.campaignID = campaignID;
  }

  public Message contactAlt(String contactAlt) {
    this.contactAlt = contactAlt;
    return this;
  }

   /**
   * mail du destinataire
   * @return contactAlt
  **/
  @Schema(example = "test.test@gmail.com", description = "mail du destinataire")
  public String getContactAlt() {
    return contactAlt;
  }

  public void setContactAlt(String contactAlt) {
    this.contactAlt = contactAlt;
  }

  public Message contactID(Long contactID) {
    this.contactID = contactID;
    return this;
  }

   /**
   * ID numérique unique du contact auquel le message a été envoyé
   * @return contactID
  **/
  @Schema(example = "1", description = "ID numérique unique du contact auquel le message a été envoyé")
  public Long getContactID() {
    return contactID;
  }

  public void setContactID(Long contactID) {
    this.contactID = contactID;
  }

  public Message delay(Integer delay) {
    this.delay = delay;
    return this;
  }

   /**
   * Delais en seconde entre le traitement du message et sa remise
   * minimum: 0
   * @return delay
  **/
  @Schema(example = "1", description = "Delais en seconde entre le traitement du message et sa remise")
  public Integer getDelay() {
    return delay;
  }

  public void setDelay(Integer delay) {
    this.delay = delay;
  }

  public Message destinationID(Integer destinationID) {
    this.destinationID = destinationID;
    return this;
  }

   /**
   * ID numérique unique du domaine de l&#x27;e-mail du destinataire.
   * minimum: 0
   * @return destinationID
  **/
  @Schema(example = "1", description = "ID numérique unique du domaine de l'e-mail du destinataire.")
  public Integer getDestinationID() {
    return destinationID;
  }

  public void setDestinationID(Integer destinationID) {
    this.destinationID = destinationID;
  }

  public Message filterTime(Integer filterTime) {
    this.filterTime = filterTime;
    return this;
  }

   /**
   * Temps passé à traiter le texte du message (en millisecondes).
   * minimum: 0
   * @return filterTime
  **/
  @Schema(example = "1", description = "Temps passé à traiter le texte du message (en millisecondes).")
  public Integer getFilterTime() {
    return filterTime;
  }

  public void setFilterTime(Integer filterTime) {
    this.filterTime = filterTime;
  }

  public Message ID(Long ID) {
    this.ID = ID;
    return this;
  }

   /**
   * Id du message envoyé
   * @return ID
  **/
  @Schema(example = "1152921509764664300", description = "Id du message envoyé")
  public Long getID() {
    return ID;
  }

  public void setID(Long ID) {
    this.ID = ID;
  }

  public Message isClickTracked(Boolean isClickTracked) {
    this.isClickTracked = isClickTracked;
    return this;
  }

   /**
   * Indique si les clics sont suivis pour ce message ou non.
   * @return isClickTracked
  **/
  @Schema(example = "true", description = "Indique si les clics sont suivis pour ce message ou non.")
  public Boolean isIsClickTracked() {
    return isClickTracked;
  }

  public void setIsClickTracked(Boolean isClickTracked) {
    this.isClickTracked = isClickTracked;
  }

  public Message isHTMLPartIncluded(Boolean isHTMLPartIncluded) {
    this.isHTMLPartIncluded = isHTMLPartIncluded;
    return this;
  }

   /**
   * Indique si le message inclut du contenu HTML
   * @return isHTMLPartIncluded
  **/
  @Schema(example = "true", description = "Indique si le message inclut du contenu HTML")
  public Boolean isIsHTMLPartIncluded() {
    return isHTMLPartIncluded;
  }

  public void setIsHTMLPartIncluded(Boolean isHTMLPartIncluded) {
    this.isHTMLPartIncluded = isHTMLPartIncluded;
  }

  public Message isOpenTracked(Boolean isOpenTracked) {
    this.isOpenTracked = isOpenTracked;
    return this;
  }

   /**
   * indique si les ouvertures sont suivies pour ce message ou non.
   * @return isOpenTracked
  **/
  @Schema(example = "true", description = "indique si les ouvertures sont suivies pour ce message ou non.")
  public Boolean isIsOpenTracked() {
    return isOpenTracked;
  }

  public void setIsOpenTracked(Boolean isOpenTracked) {
    this.isOpenTracked = isOpenTracked;
  }

  public Message isTextPartIncluded(Boolean isTextPartIncluded) {
    this.isTextPartIncluded = isTextPartIncluded;
    return this;
  }

   /**
   * indique si le message comprend une partie de texte brut
   * @return isTextPartIncluded
  **/
  @Schema(example = "true", description = "indique si le message comprend une partie de texte brut")
  public Boolean isIsTextPartIncluded() {
    return isTextPartIncluded;
  }

  public void setIsTextPartIncluded(Boolean isTextPartIncluded) {
    this.isTextPartIncluded = isTextPartIncluded;
  }

  public Message isUnsubTracked(Boolean isUnsubTracked) {
    this.isUnsubTracked = isUnsubTracked;
    return this;
  }

   /**
   * Indicates whether unsubscriptions are tracked for this message or not.
   * @return isUnsubTracked
  **/
  @Schema(example = "true", description = "Indicates whether unsubscriptions are tracked for this message or not.")
  public Boolean isIsUnsubTracked() {
    return isUnsubTracked;
  }

  public void setIsUnsubTracked(Boolean isUnsubTracked) {
    this.isUnsubTracked = isUnsubTracked;
  }

  public Message messageSize(Integer messageSize) {
    this.messageSize = messageSize;
    return this;
  }

   /**
   * taille du message (en byte)
   * minimum: 0
   * @return messageSize
  **/
  @Schema(example = "1", description = "taille du message (en byte)")
  public Integer getMessageSize() {
    return messageSize;
  }

  public void setMessageSize(Integer messageSize) {
    this.messageSize = messageSize;
  }

  public Message senderID(Long senderID) {
    this.senderID = senderID;
    return this;
  }

   /**
   * ID numérique unique de l&#x27;adresse e-mail de l&#x27;expéditeur.
   * @return senderID
  **/
  @Schema(example = "1", description = "ID numérique unique de l'adresse e-mail de l'expéditeur.")
  public Long getSenderID() {
    return senderID;
  }

  public void setSenderID(Long senderID) {
    this.senderID = senderID;
  }

  public Message spamassassinScore(Integer spamassassinScore) {
    this.spamassassinScore = spamassassinScore;
    return this;
  }

   /**
   * SpamassassinScore pour le message
   * @return spamassassinScore
  **/
  @Schema(example = "1", description = "SpamassassinScore pour le message")
  public Integer getSpamassassinScore() {
    return spamassassinScore;
  }

  public void setSpamassassinScore(Integer spamassassinScore) {
    this.spamassassinScore = spamassassinScore;
  }

  public Message spamassRules(String spamassRules) {
    this.spamassRules = spamassRules;
    return this;
  }

   /**
   * Règles de SpamAssassin correspondantes.
   * @return spamassRules
  **/
  @Schema(description = "Règles de SpamAssassin correspondantes.")
  public String getSpamassRules() {
    return spamassRules;
  }

  public void setSpamassRules(String spamassRules) {
    this.spamassRules = spamassRules;
  }

  public Message stateID(Integer stateID) {
    this.stateID = stateID;
    return this;
  }

   /**
   * ID numérique unique expliquant pourquoi le message n&#x27;a pas été remis avec succès au destinataire. Uniquement renvoyé si le message n&#x27;a pas été remis avec succès. Valeur possible : * 1 : user unknown (recipient) * 2 : mailbox inactive (recipient) * 3 : quota exceeded (recipient) * 4 : invalid domain (domain) * 5 : no mail host (domain) * 6 : relay/access denied (domain) * 7 : sender blocked (spam) * 8 : content blocked (spam) * 9 : policy issue (spam) * 10 : system issue (system) * 11 : protocol issue (system) * 12 : connection issue (system) * 13 : greylisted (domain) * 14 : preblocked (Mailjet) * 15 : duplicate in campaign (Mailjet) * 16 : spam preblocked (Mailjet) * 17 : bad or empty template (content) * 18 : error in template language (content) * 19 : typofix (domain) * 20 : blacklisted (recipient) * 21 : spam reporter (recipient) 
   * @return stateID
  **/
  @Schema(example = "1", description = "ID numérique unique expliquant pourquoi le message n'a pas été remis avec succès au destinataire. Uniquement renvoyé si le message n'a pas été remis avec succès. Valeur possible : * 1 : user unknown (recipient) * 2 : mailbox inactive (recipient) * 3 : quota exceeded (recipient) * 4 : invalid domain (domain) * 5 : no mail host (domain) * 6 : relay/access denied (domain) * 7 : sender blocked (spam) * 8 : content blocked (spam) * 9 : policy issue (spam) * 10 : system issue (system) * 11 : protocol issue (system) * 12 : connection issue (system) * 13 : greylisted (domain) * 14 : preblocked (Mailjet) * 15 : duplicate in campaign (Mailjet) * 16 : spam preblocked (Mailjet) * 17 : bad or empty template (content) * 18 : error in template language (content) * 19 : typofix (domain) * 20 : blacklisted (recipient) * 21 : spam reporter (recipient) ")
  public Integer getStateID() {
    return stateID;
  }

  public void setStateID(Integer stateID) {
    this.stateID = stateID;
  }

  public Message statePermanent(Boolean statePermanent) {
    this.statePermanent = statePermanent;
    return this;
  }

   /**
   * Indique si l&#x27;état actuel du message est permanent (c&#x27;est-à-dire qu&#x27;il ne peut plus être changé) ou non.
   * @return statePermanent
  **/
  @Schema(example = "true", description = "Indique si l'état actuel du message est permanent (c'est-à-dire qu'il ne peut plus être changé) ou non.")
  public Boolean isStatePermanent() {
    return statePermanent;
  }

  public void setStatePermanent(Boolean statePermanent) {
    this.statePermanent = statePermanent;
  }

  public Message status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * statut du mail
   * @return status
  **/
  @Schema(example = "clicked", description = "statut du mail")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Message subject(String subject) {
    this.subject = subject;
    return this;
  }

   /**
   * Sujet du mail
   * @return subject
  **/
  @Schema(example = "un exemple de mail", description = "Sujet du mail")
  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Message UUID(String UUID) {
    this.UUID = UUID;
    return this;
  }

   /**
   * UUID du message
   * @return UUID
  **/
  @Schema(example = "1514cfef-8334-480e-9e06-f634f564b9ac", description = "UUID du message")
  public String getUUID() {
    return UUID;
  }

  public void setUUID(String UUID) {
    this.UUID = UUID;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Message message = (Message) o;
    return Objects.equals(this.arrivedAt, message.arrivedAt) &&
        Objects.equals(this.attachmentCount, message.attachmentCount) &&
        Objects.equals(this.attemptCount, message.attemptCount) &&
        Objects.equals(this.campaignID, message.campaignID) &&
        Objects.equals(this.contactAlt, message.contactAlt) &&
        Objects.equals(this.contactID, message.contactID) &&
        Objects.equals(this.delay, message.delay) &&
        Objects.equals(this.destinationID, message.destinationID) &&
        Objects.equals(this.filterTime, message.filterTime) &&
        Objects.equals(this.ID, message.ID) &&
        Objects.equals(this.isClickTracked, message.isClickTracked) &&
        Objects.equals(this.isHTMLPartIncluded, message.isHTMLPartIncluded) &&
        Objects.equals(this.isOpenTracked, message.isOpenTracked) &&
        Objects.equals(this.isTextPartIncluded, message.isTextPartIncluded) &&
        Objects.equals(this.isUnsubTracked, message.isUnsubTracked) &&
        Objects.equals(this.messageSize, message.messageSize) &&
        Objects.equals(this.senderID, message.senderID) &&
        Objects.equals(this.spamassassinScore, message.spamassassinScore) &&
        Objects.equals(this.spamassRules, message.spamassRules) &&
        Objects.equals(this.stateID, message.stateID) &&
        Objects.equals(this.statePermanent, message.statePermanent) &&
        Objects.equals(this.status, message.status) &&
        Objects.equals(this.subject, message.subject) &&
        Objects.equals(this.UUID, message.UUID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(arrivedAt, attachmentCount, attemptCount, campaignID, contactAlt, contactID, delay, destinationID, filterTime, ID, isClickTracked, isHTMLPartIncluded, isOpenTracked, isTextPartIncluded, isUnsubTracked, messageSize, senderID, spamassassinScore, spamassRules, stateID, statePermanent, status, subject, UUID);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Message {\n");
    
    sb.append("    arrivedAt: ").append(toIndentedString(arrivedAt)).append("\n");
    sb.append("    attachmentCount: ").append(toIndentedString(attachmentCount)).append("\n");
    sb.append("    attemptCount: ").append(toIndentedString(attemptCount)).append("\n");
    sb.append("    campaignID: ").append(toIndentedString(campaignID)).append("\n");
    sb.append("    contactAlt: ").append(toIndentedString(contactAlt)).append("\n");
    sb.append("    contactID: ").append(toIndentedString(contactID)).append("\n");
    sb.append("    delay: ").append(toIndentedString(delay)).append("\n");
    sb.append("    destinationID: ").append(toIndentedString(destinationID)).append("\n");
    sb.append("    filterTime: ").append(toIndentedString(filterTime)).append("\n");
    sb.append("    ID: ").append(toIndentedString(ID)).append("\n");
    sb.append("    isClickTracked: ").append(toIndentedString(isClickTracked)).append("\n");
    sb.append("    isHTMLPartIncluded: ").append(toIndentedString(isHTMLPartIncluded)).append("\n");
    sb.append("    isOpenTracked: ").append(toIndentedString(isOpenTracked)).append("\n");
    sb.append("    isTextPartIncluded: ").append(toIndentedString(isTextPartIncluded)).append("\n");
    sb.append("    isUnsubTracked: ").append(toIndentedString(isUnsubTracked)).append("\n");
    sb.append("    messageSize: ").append(toIndentedString(messageSize)).append("\n");
    sb.append("    senderID: ").append(toIndentedString(senderID)).append("\n");
    sb.append("    spamassassinScore: ").append(toIndentedString(spamassassinScore)).append("\n");
    sb.append("    spamassRules: ").append(toIndentedString(spamassRules)).append("\n");
    sb.append("    stateID: ").append(toIndentedString(stateID)).append("\n");
    sb.append("    statePermanent: ").append(toIndentedString(statePermanent)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    UUID: ").append(toIndentedString(UUID)).append("\n");
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
