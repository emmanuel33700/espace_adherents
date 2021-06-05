package fr.espaceadh.authorization.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InfoAuthentification
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-05T08:55:31.930Z[GMT]")


public class InfoAuthentification   {
  @JsonProperty("idAdh")
  private Long idAdh = null;

  @JsonProperty("login")
  private String login = null;

  @JsonProperty("actif")
  private Boolean actif = null;

  public InfoAuthentification idAdh(Long idAdh) {
    this.idAdh = idAdh;
    return this;
  }

  /**
   * id de la personne
   * @return idAdh
   **/
  @Schema(description = "id de la personne")
  
    public Long getIdAdh() {
    return idAdh;
  }

  public void setIdAdh(Long idAdh) {
    this.idAdh = idAdh;
  }

  public InfoAuthentification login(String login) {
    this.login = login;
    return this;
  }

  /**
   * Login de la personnes
   * @return login
   **/
  @Schema(description = "Login de la personnes")
  
  @Size(min=3,max=50)   public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public InfoAuthentification actif(Boolean actif) {
    this.actif = actif;
    return this;
  }

  /**
   * Indique sur l'authentification est active
   * @return actif
   **/
  @Schema(description = "Indique sur l'authentification est active")
  
    public Boolean isActif() {
    return actif;
  }

  public void setActif(Boolean actif) {
    this.actif = actif;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InfoAuthentification infoAuthentification = (InfoAuthentification) o;
    return Objects.equals(this.idAdh, infoAuthentification.idAdh) &&
        Objects.equals(this.login, infoAuthentification.login) &&
        Objects.equals(this.actif, infoAuthentification.actif);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAdh, login, actif);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InfoAuthentification {\n");
    
    sb.append("    idAdh: ").append(toIndentedString(idAdh)).append("\n");
    sb.append("    login: ").append(toIndentedString(login)).append("\n");
    sb.append("    actif: ").append(toIndentedString(actif)).append("\n");
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
