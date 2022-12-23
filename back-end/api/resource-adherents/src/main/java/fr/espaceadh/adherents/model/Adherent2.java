package fr.espaceadh.adherents.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Adherent2
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-23T09:53:28.767Z[GMT]")


public class Adherent2   {
  @JsonProperty("adherent")
  private Adherent adherent = null;

  @JsonProperty("adhesion")
  private Adhesion adhesion = null;

  public Adherent2 adherent(Adherent adherent) {
    this.adherent = adherent;
    return this;
  }

  /**
   * Get adherent
   * @return adherent
   **/
  @Schema(description = "")
  
    @Valid
    public Adherent getAdherent() {
    return adherent;
  }

  public void setAdherent(Adherent adherent) {
    this.adherent = adherent;
  }

  public Adherent2 adhesion(Adhesion adhesion) {
    this.adhesion = adhesion;
    return this;
  }

  /**
   * Get adhesion
   * @return adhesion
   **/
  @Schema(description = "")
  
    @Valid
    public Adhesion getAdhesion() {
    return adhesion;
  }

  public void setAdhesion(Adhesion adhesion) {
    this.adhesion = adhesion;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Adherent2 adherent2 = (Adherent2) o;
    return Objects.equals(this.adherent, adherent2.adherent) &&
        Objects.equals(this.adhesion, adherent2.adhesion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adherent, adhesion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Adherent2 {\n");
    
    sb.append("    adherent: ").append(toIndentedString(adherent)).append("\n");
    sb.append("    adhesion: ").append(toIndentedString(adhesion)).append("\n");
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
