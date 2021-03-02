package fr.espaceadh.utilitaire.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Saison
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-02T08:30:39.723Z[GMT]")


public class Saison   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("libelle")
  private String libelle = null;

  @JsonProperty("saisonActive")
  private Boolean saisonActive = null;

  public Saison id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * id de la saison
   * @return id
   **/
  @Schema(example = "1", description = "id de la saison")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Saison libelle(String libelle) {
    this.libelle = libelle;
    return this;
  }

  /**
   * libelle de la saison
   * @return libelle
   **/
  @Schema(example = "2019/2020", description = "libelle de la saison")
  
    public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public Saison saisonActive(Boolean saisonActive) {
    this.saisonActive = saisonActive;
    return this;
  }

  /**
   * indique si la saison est active
   * @return saisonActive
   **/
  @Schema(description = "indique si la saison est active")
  
    public Boolean isSaisonActive() {
    return saisonActive;
  }

  public void setSaisonActive(Boolean saisonActive) {
    this.saisonActive = saisonActive;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Saison saison = (Saison) o;
    return Objects.equals(this.id, saison.id) &&
        Objects.equals(this.libelle, saison.libelle) &&
        Objects.equals(this.saisonActive, saison.saisonActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, libelle, saisonActive);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Saison {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
    sb.append("    saisonActive: ").append(toIndentedString(saisonActive)).append("\n");
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
