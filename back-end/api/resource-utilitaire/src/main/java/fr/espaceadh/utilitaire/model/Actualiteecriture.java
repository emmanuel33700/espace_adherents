package fr.espaceadh.utilitaire.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Actualiteecriture
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-20T19:52:41.277412232Z[GMT]")


public class Actualiteecriture   {
  @JsonProperty("libelleActualite")
  private String libelleActualite = null;

  public Actualiteecriture libelleActualite(String libelleActualite) {
    this.libelleActualite = libelleActualite;
    return this;
  }

  /**
   * libelle de l'actualite
   * @return libelleActualite
   **/
  @Schema(example = "Pas d'observation aujourd'hui", description = "libelle de l'actualite")
  
    public String getLibelleActualite() {
    return libelleActualite;
  }

  public void setLibelleActualite(String libelleActualite) {
    this.libelleActualite = libelleActualite;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Actualiteecriture actualiteecriture = (Actualiteecriture) o;
    return Objects.equals(this.libelleActualite, actualiteecriture.libelleActualite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(libelleActualite);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Actualiteecriture {\n");
    
    sb.append("    libelleActualite: ").append(toIndentedString(libelleActualite)).append("\n");
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
