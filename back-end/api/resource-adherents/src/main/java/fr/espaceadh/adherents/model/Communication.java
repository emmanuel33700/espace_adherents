package fr.espaceadh.adherents.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Communication
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-11-14T09:31:23.328Z[GMT]")


public class Communication   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("dateArrive")
  private String dateArrive = null;

  @JsonProperty("destinataire")
  private String destinataire = null;

  @JsonProperty("sujet")
  private String sujet = null;

  /**
   * statut du mail
   */
  public enum StatutEnum {
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

    StatutEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatutEnum fromValue(String text) {
      for (StatutEnum b : StatutEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("statut")
  private StatutEnum statut = null;

  @JsonProperty("typeErreur")
  private Integer typeErreur = null;

  @JsonProperty("scoreSpam")
  private Integer scoreSpam = null;

  @JsonProperty("regleSpam")
  private String regleSpam = null;

  @JsonProperty("UUID")
  private String UUID = null;

  public Communication id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Id du message envoyé
   * @return id
   **/
  @Schema(example = "1152921509764664300", description = "Id du message envoyé")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Communication dateArrive(String dateArrive) {
    this.dateArrive = dateArrive;
    return this;
  }

  /**
   * date d'arrive du message (format ISO8601)
   * @return dateArrive
   **/
  @Schema(example = "2020-01-18T21:00:00", description = "date d'arrive du message (format ISO8601)")
  
    public String getDateArrive() {
    return dateArrive;
  }

  public void setDateArrive(String dateArrive) {
    this.dateArrive = dateArrive;
  }

  public Communication destinataire(String destinataire) {
    this.destinataire = destinataire;
    return this;
  }

  /**
   * mail de l'adherent qui recoit le message
   * @return destinataire
   **/
  @Schema(example = "user@example.comg", description = "mail de l'adherent qui recoit le message")
  
  @Size(min=2,max=100)   public String getDestinataire() {
    return destinataire;
  }

  public void setDestinataire(String destinataire) {
    this.destinataire = destinataire;
  }

  public Communication sujet(String sujet) {
    this.sujet = sujet;
    return this;
  }

  /**
   * sujet du message envoyé
   * @return sujet
   **/
  @Schema(example = "Hello", description = "sujet du message envoyé")
  
  @Size(min=2,max=100)   public String getSujet() {
    return sujet;
  }

  public void setSujet(String sujet) {
    this.sujet = sujet;
  }

  public Communication statut(StatutEnum statut) {
    this.statut = statut;
    return this;
  }

  /**
   * statut du mail
   * @return statut
   **/
  @Schema(example = "clicked", description = "statut du mail")
  
  @Size(min=3,max=50)   public StatutEnum getStatut() {
    return statut;
  }

  public void setStatut(StatutEnum statut) {
    this.statut = statut;
  }

  public Communication typeErreur(Integer typeErreur) {
    this.typeErreur = typeErreur;
    return this;
  }

  /**
   * ID numérique unique expliquant pourquoi le message n'a pas été remis avec succès au destinataire. Uniquement renvoyé si le message n'a pas été remis avec succès. Valeur possible : * 1 : user unknown (recipient) * 2 : mailbox inactive (recipient) * 3 : quota exceeded (recipient) * 4 : invalid domain (domain) * 5 : no mail host (domain) * 6 : relay/access denied (domain) * 7 : sender blocked (spam) * 8 : content blocked (spam) * 9 : policy issue (spam) * 10 : system issue (system) * 11 : protocol issue (system) * 12 : connection issue (system) * 13 : greylisted (domain) * 14 : preblocked (provider) * 15 : duplicate in campaign (provider) * 16 : spam preblocked (provider) * 17 : bad or empty template (content) * 18 : error in template language (content) * 19 : typofix (domain) * 20 : blacklisted (recipient) * 21 : spam reporter (recipient) 
   * @return typeErreur
   **/
  @Schema(example = "1", description = "ID numérique unique expliquant pourquoi le message n'a pas été remis avec succès au destinataire. Uniquement renvoyé si le message n'a pas été remis avec succès. Valeur possible : * 1 : user unknown (recipient) * 2 : mailbox inactive (recipient) * 3 : quota exceeded (recipient) * 4 : invalid domain (domain) * 5 : no mail host (domain) * 6 : relay/access denied (domain) * 7 : sender blocked (spam) * 8 : content blocked (spam) * 9 : policy issue (spam) * 10 : system issue (system) * 11 : protocol issue (system) * 12 : connection issue (system) * 13 : greylisted (domain) * 14 : preblocked (provider) * 15 : duplicate in campaign (provider) * 16 : spam preblocked (provider) * 17 : bad or empty template (content) * 18 : error in template language (content) * 19 : typofix (domain) * 20 : blacklisted (recipient) * 21 : spam reporter (recipient) ")
  
    public Integer getTypeErreur() {
    return typeErreur;
  }

  public void setTypeErreur(Integer typeErreur) {
    this.typeErreur = typeErreur;
  }

  public Communication scoreSpam(Integer scoreSpam) {
    this.scoreSpam = scoreSpam;
    return this;
  }

  /**
   * SpamassassinScore pour le message
   * @return scoreSpam
   **/
  @Schema(example = "1", description = "SpamassassinScore pour le message")
  
    public Integer getScoreSpam() {
    return scoreSpam;
  }

  public void setScoreSpam(Integer scoreSpam) {
    this.scoreSpam = scoreSpam;
  }

  public Communication regleSpam(String regleSpam) {
    this.regleSpam = regleSpam;
    return this;
  }

  /**
   * Règles de SpamAssassin correspondantes.
   * @return regleSpam
   **/
  @Schema(description = "Règles de SpamAssassin correspondantes.")
  
  @Size(min=3,max=50)   public String getRegleSpam() {
    return regleSpam;
  }

  public void setRegleSpam(String regleSpam) {
    this.regleSpam = regleSpam;
  }

  public Communication UUID(String UUID) {
    this.UUID = UUID;
    return this;
  }

  /**
   * UUID du message
   * @return UUID
   **/
  @Schema(example = "1514cfef-8334-480e-9e06-f634f564b9ac", description = "UUID du message")
  
  @Size(min=3,max=50)   public String getUUID() {
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
    Communication communication = (Communication) o;
    return Objects.equals(this.id, communication.id) &&
        Objects.equals(this.dateArrive, communication.dateArrive) &&
        Objects.equals(this.destinataire, communication.destinataire) &&
        Objects.equals(this.sujet, communication.sujet) &&
        Objects.equals(this.statut, communication.statut) &&
        Objects.equals(this.typeErreur, communication.typeErreur) &&
        Objects.equals(this.scoreSpam, communication.scoreSpam) &&
        Objects.equals(this.regleSpam, communication.regleSpam) &&
        Objects.equals(this.UUID, communication.UUID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dateArrive, destinataire, sujet, statut, typeErreur, scoreSpam, regleSpam, UUID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Communication {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dateArrive: ").append(toIndentedString(dateArrive)).append("\n");
    sb.append("    destinataire: ").append(toIndentedString(destinataire)).append("\n");
    sb.append("    sujet: ").append(toIndentedString(sujet)).append("\n");
    sb.append("    statut: ").append(toIndentedString(statut)).append("\n");
    sb.append("    typeErreur: ").append(toIndentedString(typeErreur)).append("\n");
    sb.append("    scoreSpam: ").append(toIndentedString(scoreSpam)).append("\n");
    sb.append("    regleSpam: ").append(toIndentedString(regleSpam)).append("\n");
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
