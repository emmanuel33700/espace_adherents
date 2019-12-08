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
 * Validation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T08:19:59.368Z[GMT]")
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
  
  @Pattern(regexp="^[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}$") @Size(min=3,max=50)   public String getCleeValidation() {
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
