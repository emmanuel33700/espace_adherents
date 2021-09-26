package fr.espaceadh.utilitaire.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MailAEnvoyer
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-09-26T12:29:29.649Z[GMT]")


public class MailAEnvoyer   {
  /**
   * Types evenement * 1 : ADHERENT DE SAISON COURANTE                * 2 : ADHERENT DE SAISON COURANTE + SAISON -1      * 4 : ADHERENT NON RENOUVELE ADH POUR LA SAISION S * 10 : MAILING LISTE 
   */
  public enum TypeMailEnum {
    NUMBER_1(1),
    
    NUMBER_2(2),
    
    NUMBER_4(4),
    
    NUMBER_10(10);

    private Integer value;

    TypeMailEnum(Integer value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeMailEnum fromValue(String text) {
      for (TypeMailEnum b : TypeMailEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("typeMail")
  private TypeMailEnum typeMail = null;

  @JsonProperty("idListeDiffusion")
  private Long idListeDiffusion = null;

  @JsonProperty("titreEmail")
  private String titreEmail = null;

  @JsonProperty("email")
  private String email = null;

  public MailAEnvoyer typeMail(TypeMailEnum typeMail) {
    this.typeMail = typeMail;
    return this;
  }

  /**
   * Types evenement * 1 : ADHERENT DE SAISON COURANTE                * 2 : ADHERENT DE SAISON COURANTE + SAISON -1      * 4 : ADHERENT NON RENOUVELE ADH POUR LA SAISION S * 10 : MAILING LISTE 
   * @return typeMail
   **/
  @Schema(example = "1", description = "Types evenement * 1 : ADHERENT DE SAISON COURANTE                * 2 : ADHERENT DE SAISON COURANTE + SAISON -1      * 4 : ADHERENT NON RENOUVELE ADH POUR LA SAISION S * 10 : MAILING LISTE ")
  
    public TypeMailEnum getTypeMail() {
    return typeMail;
  }

  public void setTypeMail(TypeMailEnum typeMail) {
    this.typeMail = typeMail;
  }

  public MailAEnvoyer idListeDiffusion(Long idListeDiffusion) {
    this.idListeDiffusion = idListeDiffusion;
    return this;
  }

  /**
   * id de la liste de diffusion
   * @return idListeDiffusion
   **/
  @Schema(example = "1", description = "id de la liste de diffusion")
  
    public Long getIdListeDiffusion() {
    return idListeDiffusion;
  }

  public void setIdListeDiffusion(Long idListeDiffusion) {
    this.idListeDiffusion = idListeDiffusion;
  }

  public MailAEnvoyer titreEmail(String titreEmail) {
    this.titreEmail = titreEmail;
    return this;
  }

  /**
   * Titre du mail
   * @return titreEmail
   **/
  @Schema(example = "Conférence sur le soleil", description = "Titre du mail")
  
  @Size(min=3,max=200)   public String getTitreEmail() {
    return titreEmail;
  }

  public void setTitreEmail(String titreEmail) {
    this.titreEmail = titreEmail;
  }

  public MailAEnvoyer email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @Schema(example = "<h1> exemple de mail</h1>", description = "")
  
  @Size(min=3,max=50000)   public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MailAEnvoyer mailAEnvoyer = (MailAEnvoyer) o;
    return Objects.equals(this.typeMail, mailAEnvoyer.typeMail) &&
        Objects.equals(this.idListeDiffusion, mailAEnvoyer.idListeDiffusion) &&
        Objects.equals(this.titreEmail, mailAEnvoyer.titreEmail) &&
        Objects.equals(this.email, mailAEnvoyer.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeMail, idListeDiffusion, titreEmail, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MailAEnvoyer {\n");
    
    sb.append("    typeMail: ").append(toIndentedString(typeMail)).append("\n");
    sb.append("    idListeDiffusion: ").append(toIndentedString(idListeDiffusion)).append("\n");
    sb.append("    titreEmail: ").append(toIndentedString(titreEmail)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
