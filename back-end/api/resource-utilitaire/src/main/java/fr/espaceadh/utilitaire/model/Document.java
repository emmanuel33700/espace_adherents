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
 * Document
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-17T10:24:51.504Z[GMT]")


public class Document   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("idDossierRattachement")
  private Long idDossierRattachement = null;

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

  @JsonProperty("libelleCourt")
  private String libelleCourt = null;

  @JsonProperty("libelleLong")
  private String libelleLong = null;

  @JsonProperty("dateCreation")
  private String dateCreation = null;

  @JsonProperty("nomFichier")
  private String nomFichier = null;

  public Document id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * id du document
   * @return id
   **/
  @Schema(example = "1225668", description = "id du document")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Document idDossierRattachement(Long idDossierRattachement) {
    this.idDossierRattachement = idDossierRattachement;
    return this;
  }

  /**
   * id du dossier de rattachement
   * @return idDossierRattachement
   **/
  @Schema(example = "1225668", description = "id du dossier de rattachement")
  
    public Long getIdDossierRattachement() {
    return idDossierRattachement;
  }

  public void setIdDossierRattachement(Long idDossierRattachement) {
    this.idDossierRattachement = idDossierRattachement;
  }

  public Document idAuthority(IdAuthorityEnum idAuthority) {
    this.idAuthority = idAuthority;
    return this;
  }

  /**
   * Types d'adhersions. Valeurs possibles : * 1 : ADHERENT                * 2 : CONSEIL ADMINISTRATON              * 3 : BUREAU                * 4 : ADMIN 
   * @return idAuthority
   **/
  @Schema(example = "1", description = "Types d'adhersions. Valeurs possibles : * 1 : ADHERENT                * 2 : CONSEIL ADMINISTRATON              * 3 : BUREAU                * 4 : ADMIN ")
  
    public IdAuthorityEnum getIdAuthority() {
    return idAuthority;
  }

  public void setIdAuthority(IdAuthorityEnum idAuthority) {
    this.idAuthority = idAuthority;
  }

  public Document libelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
    return this;
  }

  /**
   * libelle du document
   * @return libelleCourt
   **/
  @Schema(example = "Etoiles", description = "libelle du document")
  
  @Size(min=3,max=30)   public String getLibelleCourt() {
    return libelleCourt;
  }

  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }

  public Document libelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
    return this;
  }

  /**
   * libelle du document
   * @return libelleLong
   **/
  @Schema(example = "document sur les étoiles", description = "libelle du document")
  
  @Size(min=3,max=50)   public String getLibelleLong() {
    return libelleLong;
  }

  public void setLibelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
  }

  public Document dateCreation(String dateCreation) {
    this.dateCreation = dateCreation;
    return this;
  }

  /**
   * Date de création (format ISO8601)
   * @return dateCreation
   **/
  @Schema(example = "2020-01-18T21:00:00", description = "Date de création (format ISO8601)")
  
    public String getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(String dateCreation) {
    this.dateCreation = dateCreation;
  }

  public Document nomFichier(String nomFichier) {
    this.nomFichier = nomFichier;
    return this;
  }

  /**
   * nom du fichier
   * @return nomFichier
   **/
  @Schema(example = "055555884.pdf", description = "nom du fichier")
  
  @Size(min=3,max=50)   public String getNomFichier() {
    return nomFichier;
  }

  public void setNomFichier(String nomFichier) {
    this.nomFichier = nomFichier;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return Objects.equals(this.id, document.id) &&
        Objects.equals(this.idDossierRattachement, document.idDossierRattachement) &&
        Objects.equals(this.idAuthority, document.idAuthority) &&
        Objects.equals(this.libelleCourt, document.libelleCourt) &&
        Objects.equals(this.libelleLong, document.libelleLong) &&
        Objects.equals(this.dateCreation, document.dateCreation) &&
        Objects.equals(this.nomFichier, document.nomFichier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idDossierRattachement, idAuthority, libelleCourt, libelleLong, dateCreation, nomFichier);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Document {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idDossierRattachement: ").append(toIndentedString(idDossierRattachement)).append("\n");
    sb.append("    idAuthority: ").append(toIndentedString(idAuthority)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    dateCreation: ").append(toIndentedString(dateCreation)).append("\n");
    sb.append("    nomFichier: ").append(toIndentedString(nomFichier)).append("\n");
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
