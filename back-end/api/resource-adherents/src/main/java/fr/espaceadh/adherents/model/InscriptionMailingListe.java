package fr.espaceadh.adherents.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * InscriptionMailingListe
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-15T13:01:26.073Z[GMT]")


public class InscriptionMailingListe   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("inscriptionListeDiffusion")
  private Boolean inscriptionListeDiffusion = null;

  public InscriptionMailingListe id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Id de la mailing liste
   * @return id
   **/
  @Schema(example = "1", description = "Id de la mailing liste")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public InscriptionMailingListe inscriptionListeDiffusion(Boolean inscriptionListeDiffusion) {
    this.inscriptionListeDiffusion = inscriptionListeDiffusion;
    return this;
  }

  /**
   * indique si l'adherent souhaite s'inscrire ou non à une liste de diffusion
   * @return inscriptionListeDiffusion
   **/
  @Schema(example = "true", description = "indique si l'adherent souhaite s'inscrire ou non à une liste de diffusion")
  
    public Boolean isInscriptionListeDiffusion() {
    return inscriptionListeDiffusion;
  }

  public void setInscriptionListeDiffusion(Boolean inscriptionListeDiffusion) {
    this.inscriptionListeDiffusion = inscriptionListeDiffusion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InscriptionMailingListe inscriptionMailingListe = (InscriptionMailingListe) o;
    return Objects.equals(this.id, inscriptionMailingListe.id) &&
        Objects.equals(this.inscriptionListeDiffusion, inscriptionMailingListe.inscriptionListeDiffusion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, inscriptionListeDiffusion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InscriptionMailingListe {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    inscriptionListeDiffusion: ").append(toIndentedString(inscriptionListeDiffusion)).append("\n");
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
