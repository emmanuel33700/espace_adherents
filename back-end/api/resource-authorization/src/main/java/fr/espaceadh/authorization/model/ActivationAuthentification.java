package fr.espaceadh.authorization.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ActivationAuthentification
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T09:41:12.158Z[GMT]")


public class ActivationAuthentification   {
  @JsonProperty("statutActivation")
  private Boolean statutActivation = null;

  public ActivationAuthentification statutActivation(Boolean statutActivation) {
    this.statutActivation = statutActivation;
    return this;
  }

  /**
   * Indique le statut de l'authntification  * true => Le compte est activé * false => le compte est désactivé 
   * @return statutActivation
   **/
  @Schema(example = "true", description = "Indique le statut de l'authntification  * true => Le compte est activé * false => le compte est désactivé ")
  
    public Boolean isStatutActivation() {
    return statutActivation;
  }

  public void setStatutActivation(Boolean statutActivation) {
    this.statutActivation = statutActivation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActivationAuthentification activationAuthentification = (ActivationAuthentification) o;
    return Objects.equals(this.statutActivation, activationAuthentification.statutActivation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statutActivation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActivationAuthentification {\n");
    
    sb.append("    statutActivation: ").append(toIndentedString(statutActivation)).append("\n");
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
