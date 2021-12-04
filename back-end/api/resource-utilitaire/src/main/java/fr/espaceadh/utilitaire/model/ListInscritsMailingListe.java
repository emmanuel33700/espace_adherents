package fr.espaceadh.utilitaire.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ListInscritsMailingListe
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-04T15:52:57.795Z[GMT]")


public class ListInscritsMailingListe   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("libelle")
  private String libelle = null;

  @JsonProperty("lstAdherents")
  @Valid
  private List<InscritsMailingListe> lstAdherents = null;

  public ListInscritsMailingListe id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * id de la liste de diffusion
   * @return id
   **/
  @Schema(example = "1", description = "id de la liste de diffusion")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ListInscritsMailingListe libelle(String libelle) {
    this.libelle = libelle;
    return this;
  }

  /**
   * Libelle de la liste de diffusion
   * @return libelle
   **/
  @Schema(example = "Atelier soleil", description = "Libelle de la liste de diffusion")
  
    public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public ListInscritsMailingListe lstAdherents(List<InscritsMailingListe> lstAdherents) {
    this.lstAdherents = lstAdherents;
    return this;
  }

  public ListInscritsMailingListe addLstAdherentsItem(InscritsMailingListe lstAdherentsItem) {
    if (this.lstAdherents == null) {
      this.lstAdherents = new ArrayList<InscritsMailingListe>();
    }
    this.lstAdherents.add(lstAdherentsItem);
    return this;
  }

  /**
   * Get lstAdherents
   * @return lstAdherents
   **/
  @Schema(description = "")
      @Valid
    public List<InscritsMailingListe> getLstAdherents() {
    return lstAdherents;
  }

  public void setLstAdherents(List<InscritsMailingListe> lstAdherents) {
    this.lstAdherents = lstAdherents;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListInscritsMailingListe listInscritsMailingListe = (ListInscritsMailingListe) o;
    return Objects.equals(this.id, listInscritsMailingListe.id) &&
        Objects.equals(this.libelle, listInscritsMailingListe.libelle) &&
        Objects.equals(this.lstAdherents, listInscritsMailingListe.lstAdherents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, libelle, lstAdherents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListInscritsMailingListe {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
    sb.append("    lstAdherents: ").append(toIndentedString(lstAdherents)).append("\n");
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
