package fr.espaceadh.authorization.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Authentification
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-28T08:29:33.965Z[GMT]")
public class Authentification   {
  @JsonProperty("idAdh")
  private Long idAdh = null;

  @JsonProperty("login")
  private String login = null;

  @JsonProperty("password")
  private String password = null;

  public Authentification idAdh(Long idAdh) {
    this.idAdh = idAdh;
    return this;
  }

  /**
   * id de la personne
   * @return idAdh
  **/
  @ApiModelProperty(value = "id de la personne")

  public Long getIdAdh() {
    return idAdh;
  }

  public void setIdAdh(Long idAdh) {
    this.idAdh = idAdh;
  }

  public Authentification login(String login) {
    this.login = login;
    return this;
  }

  /**
   * Login de la personnes
   * @return login
  **/
  @ApiModelProperty(value = "Login de la personnes")

@Size(min=3,max=50)   public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Authentification password(String password) {
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
    Authentification authentification = (Authentification) o;
    return Objects.equals(this.idAdh, authentification.idAdh) &&
        Objects.equals(this.login, authentification.login) &&
        Objects.equals(this.password, authentification.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAdh, login, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Authentification {\n");
    
    sb.append("    idAdh: ").append(toIndentedString(idAdh)).append("\n");
    sb.append("    login: ").append(toIndentedString(login)).append("\n");
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
