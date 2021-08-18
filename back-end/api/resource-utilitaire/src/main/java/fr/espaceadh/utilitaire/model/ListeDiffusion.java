package fr.espaceadh.utilitaire.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * ListeDiffusion
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-15T13:08:44.732Z[GMT]")


public class ListeDiffusion   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("libelle")
  private String libelle = null;

  public ListeDiffusion id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * id de la liste de diffusion
   * @return id
   **/
  @Schema(example = "1", description = "id de la liste de diffusion")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ListeDiffusion libelle(String libelle) {
    this.libelle = libelle;
    return this;
  }

  /**
   * Libelle de la liste de diffusion
   * @return libelle
   **/
  @Schema(example = "Atelier soleil", description = "Libelle de la liste de diffusion")
  
    public String getLibelle() {
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
    ListeDiffusion listeDiffusion = (ListeDiffusion) o;
    return Objects.equals(this.id, listeDiffusion.id) &&
        Objects.equals(this.libelle, listeDiffusion.libelle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, libelle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListeDiffusion {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
