package fr.espaceadh.authorization.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Validation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-05T20:02:22.596Z[GMT]")
public class Validation   {
  @JsonProperty("cleeValidation")
  private String cleeValidation = null;

  public Validation cleeValidation(String cleeValidation) {
    this.cleeValidation = cleeValidation;
    return this;
  }

  /**
   * clée de validation pour activer le compte créé
   * @return cleeValidation
  **/
  @ApiModelProperty(value = "clée de validation pour activer le compte créé")

@Size(min=3,max=50)   public String getCleeValidation() {
    return cleeValidation;
  }

  public void setCleeValidation(String cleeValidation) {
    this.cleeValidation = cleeValidation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Validation validation = (Validation) o;
    return Objects.equals(this.cleeValidation, validation.cleeValidation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cleeValidation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Validation {\n");
    
    sb.append("    cleeValidation: ").append(toIndentedString(cleeValidation)).append("\n");
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
