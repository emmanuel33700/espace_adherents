package fr.espaceadh.adherents.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MailingListe
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-28T09:42:09.305Z[GMT]")


public class MailingListe   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("inscriptionListeDiffusion")
  private Boolean inscriptionListeDiffusion = null;

  @JsonProperty("libelle")
  private String libelle = null;

  public MailingListe id(Long id) {
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

  public MailingListe inscriptionListeDiffusion(Boolean inscriptionListeDiffusion) {
    this.inscriptionListeDiffusion = inscriptionListeDiffusion;
    return this;
  }

  /**
   * indique si l'adherent est scrire ou non à une liste de diffusion
   * @return inscriptionListeDiffusion
   **/
  @Schema(example = "true", description = "indique si l'adherent est scrire ou non à une liste de diffusion")
  
    public Boolean isInscriptionListeDiffusion() {
    return inscriptionListeDiffusion;
  }

  public void setInscriptionListeDiffusion(Boolean inscriptionListeDiffusion) {
    this.inscriptionListeDiffusion = inscriptionListeDiffusion;
  }

  public MailingListe libelle(String libelle) {
    this.libelle = libelle;
    return this;
  }

  /**
   * Libelle de la liste de diffusion
   * @return libelle
   **/
  @Schema(description = "Libelle de la liste de diffusion")
  
  @Size(min=3,max=50)   public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MailingListe mailingListe = (MailingListe) o;
    return Objects.equals(this.id, mailingListe.id) &&
        Objects.equals(this.inscriptionListeDiffusion, mailingListe.inscriptionListeDiffusion) &&
        Objects.equals(this.libelle, mailingListe.libelle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, inscriptionListeDiffusion, libelle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MailingListe {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    inscriptionListeDiffusion: ").append(toIndentedString(inscriptionListeDiffusion)).append("\n");
    sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
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
