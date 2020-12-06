package fr.espaceadh.adherents.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Adhesion
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-11-14T09:31:23.328Z[GMT]")


public class Adhesion   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("idAdherent")
  private Long idAdherent = null;

  @JsonProperty("idAnneeAdhesion")
  private Long idAnneeAdhesion = null;

  @JsonProperty("libelleAnneeAdhesion")
  private String libelleAnneeAdhesion = null;

  /**
   * Types d'adhersions. Valeurs possibles : * 1 : ADULTE                * 2 : FAMILLE              * 3 : RESPONSABLE DE FAMILLE                  * 4 : ENFANT * 5 : BIENFAITEUR                   * 6 : HONNEUR  * 7 : ETUDIANT           * 8 : DEMANDEUR EMPLOI   
   */
  public enum IdTypeAdhesionEnum {
    NUMBER_1(1l),
    
    NUMBER_2(2l),
    
    NUMBER_3(3l),
    
    NUMBER_4(4l),
    
    NUMBER_5(5l),
    
    NUMBER_6(6l),
    
    NUMBER_7(7l),
    
    NUMBER_8(8l);

    private Long value;

    IdTypeAdhesionEnum(Long value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static IdTypeAdhesionEnum fromValue(String text) {
      for (IdTypeAdhesionEnum b : IdTypeAdhesionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("idTypeAdhesion")
  private IdTypeAdhesionEnum idTypeAdhesion = null;

  @JsonProperty("comptaSomme")
  private Long comptaSomme = null;

  @JsonProperty("comptaBanque")
  private String comptaBanque = null;

  @JsonProperty("comptaNumCheque")
  private String comptaNumCheque = null;

  @JsonProperty("cheque")
  private Boolean cheque = null;

  @JsonProperty("espace")
  private Boolean espace = null;

  public Adhesion id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Id de l'adhesion
   * @return id
   **/
  @Schema(example = "1", description = "Id de l'adhesion")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Adhesion idAdherent(Long idAdherent) {
    this.idAdherent = idAdherent;
    return this;
  }

  /**
   * Id de l'adherent
   * @return idAdherent
   **/
  @Schema(example = "1", description = "Id de l'adherent")
  
    public Long getIdAdherent() {
    return idAdherent;
  }

  public void setIdAdherent(Long idAdherent) {
    this.idAdherent = idAdherent;
  }

  public Adhesion idAnneeAdhesion(Long idAnneeAdhesion) {
    this.idAnneeAdhesion = idAnneeAdhesion;
    return this;
  }

  /**
   * Id anne adhesion
   * @return idAnneeAdhesion
   **/
  @Schema(example = "1", description = "Id anne adhesion")
  
    public Long getIdAnneeAdhesion() {
    return idAnneeAdhesion;
  }

  public void setIdAnneeAdhesion(Long idAnneeAdhesion) {
    this.idAnneeAdhesion = idAnneeAdhesion;
  }

  public Adhesion libelleAnneeAdhesion(String libelleAnneeAdhesion) {
    this.libelleAnneeAdhesion = libelleAnneeAdhesion;
    return this;
  }

  /**
   * Libellé anne adhesion
   * @return libelleAnneeAdhesion
   **/
  @Schema(example = "2019/2020", description = "Libellé anne adhesion")
  
    public String getLibelleAnneeAdhesion() {
    return libelleAnneeAdhesion;
  }

  public void setLibelleAnneeAdhesion(String libelleAnneeAdhesion) {
    this.libelleAnneeAdhesion = libelleAnneeAdhesion;
  }

  public Adhesion idTypeAdhesion(IdTypeAdhesionEnum idTypeAdhesion) {
    this.idTypeAdhesion = idTypeAdhesion;
    return this;
  }

  /**
   * Types d'adhersions. Valeurs possibles : * 1 : ADULTE                * 2 : FAMILLE              * 3 : RESPONSABLE DE FAMILLE                  * 4 : ENFANT * 5 : BIENFAITEUR                   * 6 : HONNEUR  * 7 : ETUDIANT           * 8 : DEMANDEUR EMPLOI   
   * @return idTypeAdhesion
   **/
  @Schema(description = "Types d'adhersions. Valeurs possibles : * 1 : ADULTE                * 2 : FAMILLE              * 3 : RESPONSABLE DE FAMILLE                  * 4 : ENFANT * 5 : BIENFAITEUR                   * 6 : HONNEUR  * 7 : ETUDIANT           * 8 : DEMANDEUR EMPLOI   ")
  
    public IdTypeAdhesionEnum getIdTypeAdhesion() {
    return idTypeAdhesion;
  }

  public void setIdTypeAdhesion(IdTypeAdhesionEnum idTypeAdhesion) {
    this.idTypeAdhesion = idTypeAdhesion;
  }

  public Adhesion comptaSomme(Long comptaSomme) {
    this.comptaSomme = comptaSomme;
    return this;
  }

  /**
   * Somme payé par l'adherent pour cette adhesion
   * @return comptaSomme
   **/
  @Schema(example = "45", description = "Somme payé par l'adherent pour cette adhesion")
  
    public Long getComptaSomme() {
    return comptaSomme;
  }

  public void setComptaSomme(Long comptaSomme) {
    this.comptaSomme = comptaSomme;
  }

  public Adhesion comptaBanque(String comptaBanque) {
    this.comptaBanque = comptaBanque;
    return this;
  }

  /**
   * Nom de la banque si paiement par cheque
   * @return comptaBanque
   **/
  @Schema(example = "Credit agricole", description = "Nom de la banque si paiement par cheque")
  
  @Size(min=2,max=35)   public String getComptaBanque() {
    return comptaBanque;
  }

  public void setComptaBanque(String comptaBanque) {
    this.comptaBanque = comptaBanque;
  }

  public Adhesion comptaNumCheque(String comptaNumCheque) {
    this.comptaNumCheque = comptaNumCheque;
    return this;
  }

  /**
   * Num du cheque si paiement par cheque
   * @return comptaNumCheque
   **/
  @Schema(example = "15gg778", description = "Num du cheque si paiement par cheque")
  
  @Size(min=2,max=35)   public String getComptaNumCheque() {
    return comptaNumCheque;
  }

  public void setComptaNumCheque(String comptaNumCheque) {
    this.comptaNumCheque = comptaNumCheque;
  }

  public Adhesion cheque(Boolean cheque) {
    this.cheque = cheque;
    return this;
  }

  /**
   * Indique si paiement par cheque
   * @return cheque
   **/
  @Schema(example = "true", description = "Indique si paiement par cheque")
  
    public Boolean isCheque() {
    return cheque;
  }

  public void setCheque(Boolean cheque) {
    this.cheque = cheque;
  }

  public Adhesion espace(Boolean espace) {
    this.espace = espace;
    return this;
  }

  /**
   * Indique si paiement par espece
   * @return espace
   **/
  @Schema(example = "false", description = "Indique si paiement par espece")
  
    public Boolean isEspace() {
    return espace;
  }

  public void setEspace(Boolean espace) {
    this.espace = espace;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Adhesion adhesion = (Adhesion) o;
    return Objects.equals(this.id, adhesion.id) &&
        Objects.equals(this.idAdherent, adhesion.idAdherent) &&
        Objects.equals(this.idAnneeAdhesion, adhesion.idAnneeAdhesion) &&
        Objects.equals(this.libelleAnneeAdhesion, adhesion.libelleAnneeAdhesion) &&
        Objects.equals(this.idTypeAdhesion, adhesion.idTypeAdhesion) &&
        Objects.equals(this.comptaSomme, adhesion.comptaSomme) &&
        Objects.equals(this.comptaBanque, adhesion.comptaBanque) &&
        Objects.equals(this.comptaNumCheque, adhesion.comptaNumCheque) &&
        Objects.equals(this.cheque, adhesion.cheque) &&
        Objects.equals(this.espace, adhesion.espace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idAdherent, idAnneeAdhesion, libelleAnneeAdhesion, idTypeAdhesion, comptaSomme, comptaBanque, comptaNumCheque, cheque, espace);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Adhesion {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idAdherent: ").append(toIndentedString(idAdherent)).append("\n");
    sb.append("    idAnneeAdhesion: ").append(toIndentedString(idAnneeAdhesion)).append("\n");
    sb.append("    libelleAnneeAdhesion: ").append(toIndentedString(libelleAnneeAdhesion)).append("\n");
    sb.append("    idTypeAdhesion: ").append(toIndentedString(idTypeAdhesion)).append("\n");
    sb.append("    comptaSomme: ").append(toIndentedString(comptaSomme)).append("\n");
    sb.append("    comptaBanque: ").append(toIndentedString(comptaBanque)).append("\n");
    sb.append("    comptaNumCheque: ").append(toIndentedString(comptaNumCheque)).append("\n");
    sb.append("    cheque: ").append(toIndentedString(cheque)).append("\n");
    sb.append("    espace: ").append(toIndentedString(espace)).append("\n");
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
