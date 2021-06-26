package fr.espaceadh.adherents.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * LiensAdherentInner
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-26T08:37:56.470Z[GMT]")


public class LiensAdherentInner   {
  @JsonProperty("idAdhRepresentant")
  private Long idAdhRepresentant = null;

  @JsonProperty("idAdhRepresente")
  private Long idAdhRepresente = null;

  @JsonProperty("nomAdhRepresente")
  private String nomAdhRepresente = null;

  @JsonProperty("prenomAdhRepresente")
  private String prenomAdhRepresente = null;

  @JsonProperty("emailAdhRepresente")
  private String emailAdhRepresente = null;

  public LiensAdherentInner idAdhRepresentant(Long idAdhRepresentant) {
    this.idAdhRepresentant = idAdhRepresentant;
    return this;
  }

  /**
   * id de l'adherent representant un autre adherent
   * @return idAdhRepresentant
   **/
  @Schema(example = "1", description = "id de l'adherent representant un autre adherent")
  
    public Long getIdAdhRepresentant() {
    return idAdhRepresentant;
  }

  public void setIdAdhRepresentant(Long idAdhRepresentant) {
    this.idAdhRepresentant = idAdhRepresentant;
  }

  public LiensAdherentInner idAdhRepresente(Long idAdhRepresente) {
    this.idAdhRepresente = idAdhRepresente;
    return this;
  }

  /**
   * id de l'adherent représenté un autre adherent
   * @return idAdhRepresente
   **/
  @Schema(example = "1", description = "id de l'adherent représenté un autre adherent")
  
    public Long getIdAdhRepresente() {
    return idAdhRepresente;
  }

  public void setIdAdhRepresente(Long idAdhRepresente) {
    this.idAdhRepresente = idAdhRepresente;
  }

  public LiensAdherentInner nomAdhRepresente(String nomAdhRepresente) {
    this.nomAdhRepresente = nomAdhRepresente;
    return this;
  }

  /**
   * non de l'adherent représenté un autre adherent
   * @return nomAdhRepresente
   **/
  @Schema(example = "Dupont", description = "non de l'adherent représenté un autre adherent")
  
  @Size(min=3,max=20)   public String getNomAdhRepresente() {
    return nomAdhRepresente;
  }

  public void setNomAdhRepresente(String nomAdhRepresente) {
    this.nomAdhRepresente = nomAdhRepresente;
  }

  public LiensAdherentInner prenomAdhRepresente(String prenomAdhRepresente) {
    this.prenomAdhRepresente = prenomAdhRepresente;
    return this;
  }

  /**
   * prenom de l'adherent représenté un autre adherent
   * @return prenomAdhRepresente
   **/
  @Schema(example = "Jean", description = "prenom de l'adherent représenté un autre adherent")
  
  @Size(min=3,max=20)   public String getPrenomAdhRepresente() {
    return prenomAdhRepresente;
  }

  public void setPrenomAdhRepresente(String prenomAdhRepresente) {
    this.prenomAdhRepresente = prenomAdhRepresente;
  }

  public LiensAdherentInner emailAdhRepresente(String emailAdhRepresente) {
    this.emailAdhRepresente = emailAdhRepresente;
    return this;
  }

  /**
   * email de l'adherent représenté un autre adherent
   * @return emailAdhRepresente
   **/
  @Schema(example = "test.test@gmail.com", description = "email de l'adherent représenté un autre adherent")
  
  @Size(min=5,max=40)   public String getEmailAdhRepresente() {
    return emailAdhRepresente;
  }

  public void setEmailAdhRepresente(String emailAdhRepresente) {
    this.emailAdhRepresente = emailAdhRepresente;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LiensAdherentInner liensAdherentInner = (LiensAdherentInner) o;
    return Objects.equals(this.idAdhRepresentant, liensAdherentInner.idAdhRepresentant) &&
        Objects.equals(this.idAdhRepresente, liensAdherentInner.idAdhRepresente) &&
        Objects.equals(this.nomAdhRepresente, liensAdherentInner.nomAdhRepresente) &&
        Objects.equals(this.prenomAdhRepresente, liensAdherentInner.prenomAdhRepresente) &&
        Objects.equals(this.emailAdhRepresente, liensAdherentInner.emailAdhRepresente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAdhRepresentant, idAdhRepresente, nomAdhRepresente, prenomAdhRepresente, emailAdhRepresente);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LiensAdherentInner {\n");
    
    sb.append("    idAdhRepresentant: ").append(toIndentedString(idAdhRepresentant)).append("\n");
    sb.append("    idAdhRepresente: ").append(toIndentedString(idAdhRepresente)).append("\n");
    sb.append("    nomAdhRepresente: ").append(toIndentedString(nomAdhRepresente)).append("\n");
    sb.append("    prenomAdhRepresente: ").append(toIndentedString(prenomAdhRepresente)).append("\n");
    sb.append("    emailAdhRepresente: ").append(toIndentedString(emailAdhRepresente)).append("\n");
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
