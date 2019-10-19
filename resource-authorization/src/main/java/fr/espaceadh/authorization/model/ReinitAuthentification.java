/*
 * Copyright (C) 2019 emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
 * ReinitAuthentification
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T08:19:59.368Z[GMT]")
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
  
  @Pattern(regexp="^[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}$") @Size(min=3,max=50)   public String getCleeValidation() {
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
  
  @Pattern(regexp="^[a-zA-Z0-9._%!?#\\u00e1\\u00e0\\u00e2\\u00e4\\u00e3\\u00e5\\u00e7\\u00e9\\u00e8\\u00ea\\u00eb\\u00ed\\u00ec\\u00ee\\u00ef\\u00f1\\u00f3\\u00f2\\u00f4\\u00f6\\u00f5\\u00fa\\u00f9\\u00fb\\u00fc\\u00fd\\u00ff\\u00e6\\u0153\\u00c1\\u00c0\\u00c2\\u00c4\\u00c3\\u00c5\\u00c7\\u00c9\\u00c8\\u00ca\\u00cb\\u00cd\\u00cc\\u00ce\\u00cf\\u00d1\\u00d3\\u00d2\\u00d4\\u00d6\\u00d5\\u00da\\u00d9\\u00db\\u00dc\\u00dd\\u0178-]{5,15}$") @Size(min=5,max=15)   public String getPassword() {
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
