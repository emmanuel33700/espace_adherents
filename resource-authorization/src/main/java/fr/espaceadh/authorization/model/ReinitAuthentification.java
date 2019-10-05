package fr.espaceadh.authorization.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * ReinitAuthentification
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-28T08:29:33.965Z[GMT]")
public class ReinitAuthentification   {
  @JsonProperty("cleeValidation")
  private String cleeValidation = null;

  @JsonProperty("password")
  private String password = null;

  public ReinitAuthentification cleeValidation(String cleeValidation) {
    this.cleeValidation = cleeValidation;
    return this;
  }

  /**
   * clée de validation pour réinitialiser le compte créé
   * @return cleeValidation
  **/
  @ApiModelProperty(value = "clée de validation pour réinitialiser le compte créé")

@Size(min=3,max=50)   public String getCleeValidation() {
    return cleeValidation;
  }

  public void setCleeValidation(String cleeValidation) {
    this.cleeValidation = cleeValidation;
  }

  public ReinitAuthentification password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Mot de passe de la personne
   * @return password
  **/
  @ApiModelProperty(value = "Mot de passe de la personne")

@Size(min=5,max=15)   public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReinitAuthentification reinitAuthentification = (ReinitAuthentification) o;
    return Objects.equals(this.cleeValidation, reinitAuthentification.cleeValidation) &&
        Objects.equals(this.password, reinitAuthentification.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cleeValidation, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReinitAuthentification {\n");
    
    sb.append("    cleeValidation: ").append(toIndentedString(cleeValidation)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
