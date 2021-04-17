package fr.espaceadh.utilitaire.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import fr.espaceadh.utilitaire.model.ArborescenceDocuments;
import fr.espaceadh.utilitaire.model.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ArborescenceDocuments
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-17T10:24:51.504Z[GMT]")


public class ArborescenceDocuments   {
  @JsonProperty("parent")
  private Document parent = null;

  @JsonProperty("enfants")
  @Valid
  private List<ArborescenceDocuments> enfants = null;

  public ArborescenceDocuments parent(Document parent) {
    this.parent = parent;
    return this;
  }

  /**
   * Get parent
   * @return parent
   **/
  @Schema(description = "")
  
    @Valid
    public Document getParent() {
    return parent;
  }

  public void setParent(Document parent) {
    this.parent = parent;
  }

  public ArborescenceDocuments enfants(List<ArborescenceDocuments> enfants) {
    this.enfants = enfants;
    return this;
  }

  public ArborescenceDocuments addEnfantsItem(ArborescenceDocuments enfantsItem) {
    if (this.enfants == null) {
      this.enfants = new ArrayList<ArborescenceDocuments>();
    }
    this.enfants.add(enfantsItem);
    return this;
  }

  /**
   * Get enfants
   * @return enfants
   **/
  @Schema(description = "")
      @Valid
    public List<ArborescenceDocuments> getEnfants() {
    return enfants;
  }

  public void setEnfants(List<ArborescenceDocuments> enfants) {
    this.enfants = enfants;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArborescenceDocuments arborescenceDocuments = (ArborescenceDocuments) o;
    return Objects.equals(this.parent, arborescenceDocuments.parent) &&
        Objects.equals(this.enfants, arborescenceDocuments.enfants);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parent, enfants);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArborescenceDocuments {\n");
    
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
    sb.append("    enfants: ").append(toIndentedString(enfants)).append("\n");
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
