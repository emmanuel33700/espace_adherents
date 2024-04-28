package fr.espaceadh.utilitaire.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Actualitelecture
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-20T19:52:41.277412232Z[GMT]")


public class Actualitelecture   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("libelleActualite")
  private String libelleActualite = null;

  @JsonProperty("dateCreation")
  private String dateCreation = null;

  @JsonProperty("idAdherent")
  private Long idAdherent = null;

  /**
   * Types d'adhersions. Valeurs possibles : * 1 : ADHERENT                * 2 : CONSEIL ADMINISTRATON              * 3 : BUREAU                * 4 : ADMIN 
   */
  public enum IdAuthorityEnum {
    NUMBER_1(1),
    
    NUMBER_2(2),
    
    NUMBER_3(3),
    
    NUMBER_4(4);

    private Integer value;

    IdAuthorityEnum(Integer value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static IdAuthorityEnum fromValue(String text) {
      for (IdAuthorityEnum b : IdAuthorityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("idAuthority")
  private IdAuthorityEnum idAuthority = null;

  public Actualitelecture id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * id de l'actualite
   * @return id
   **/
  @Schema(example = "1", description = "id de l'actualite")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Actualitelecture libelleActualite(String libelleActualite) {
    this.libelleActualite = libelleActualite;
    return this;
  }

  /**
   * libelle de l'actualite
   * @return libelleActualite
   **/
  @Schema(example = "Pas d'observation aujourd'hui", description = "libelle de l'actualite")
  
    public String getLibelleActualite() {
    return libelleActualite;
  }

  public void setLibelleActualite(String libelleActualite) {
    this.libelleActualite = libelleActualite;
  }

  public Actualitelecture dateCreation(String dateCreation) {
    this.dateCreation = dateCreation;
    return this;
  }

  /**
   * date de creation de l'actualité
   * @return dateCreation
   **/
  @Schema(example = "2020-01-18T21:00:00", description = "date de creation de l'actualité")
  
    public String getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(String dateCreation) {
    this.dateCreation = dateCreation;
  }

  public Actualitelecture idAdherent(Long idAdherent) {
    this.idAdherent = idAdherent;
    return this;
  }

  /**
   * id de l'adhérent qui a créé l'actualité
   * @return idAdherent
   **/
  @Schema(example = "1", description = "id de l'adhérent qui a créé l'actualité")
  
    public Long getIdAdherent() {
    return idAdherent;
  }

  public void setIdAdherent(Long idAdherent) {
    this.idAdherent = idAdherent;
  }

  public Actualitelecture idAuthority(IdAuthorityEnum idAuthority) {
    this.idAuthority = idAuthority;
    return this;
  }

  /**
   * Types d'adhersions. Valeurs possibles : * 1 : ADHERENT                * 2 : CONSEIL ADMINISTRATON              * 3 : BUREAU                * 4 : ADMIN 
   * @return idAuthority
   **/
  @Schema(description = "Types d'adhersions. Valeurs possibles : * 1 : ADHERENT                * 2 : CONSEIL ADMINISTRATON              * 3 : BUREAU                * 4 : ADMIN ")
  
    public IdAuthorityEnum getIdAuthority() {
    return idAuthority;
  }

  public void setIdAuthority(IdAuthorityEnum idAuthority) {
    this.idAuthority = idAuthority;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Actualitelecture actualitelecture = (Actualitelecture) o;
    return Objects.equals(this.id, actualitelecture.id) &&
        Objects.equals(this.libelleActualite, actualitelecture.libelleActualite) &&
        Objects.equals(this.dateCreation, actualitelecture.dateCreation) &&
        Objects.equals(this.idAdherent, actualitelecture.idAdherent) &&
        Objects.equals(this.idAuthority, actualitelecture.idAuthority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, libelleActualite, dateCreation, idAdherent, idAuthority);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Actualitelecture {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    libelleActualite: ").append(toIndentedString(libelleActualite)).append("\n");
    sb.append("    dateCreation: ").append(toIndentedString(dateCreation)).append("\n");
    sb.append("    idAdherent: ").append(toIndentedString(idAdherent)).append("\n");
    sb.append("    idAuthority: ").append(toIndentedString(idAuthority)).append("\n");
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
