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
 * SyntheseEvenement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-09T20:07:09.346Z[GMT]")


public class SyntheseEvenement   {
  @JsonProperty("id")
  private Long id = null;

  /**
   * Types evenement * 1 : CONFERENCE                * 2 : MANIFESTATION             * 3 : OBSERVATION * 4 : ANIMATION * 5 : REUNION * 6 : SORTIE * 7 : ATELIER * 8 : DIVERS 
   */
  public enum TypeEnum {
    NUMBER_1(1),
    
    NUMBER_2(2),
    
    NUMBER_3(3),
    
    NUMBER_4(4),
    
    NUMBER_5(5),
    
    NUMBER_6(6),
    
    NUMBER_7(7),
    
    NUMBER_8(8);

    private Integer value;

    TypeEnum(Integer value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("detail")
  private String detail = null;

  @JsonProperty("datedebut")
  private String datedebut = null;

  @JsonProperty("datefin")
  private String datefin = null;

  @JsonProperty("envoyerInfoAdherents")
  private Boolean envoyerInfoAdherents = null;

  @JsonProperty("demanderConfirmationParticipation")
  private Boolean demanderConfirmationParticipation = null;

  @JsonProperty("nbAdherentParticipant")
  private Integer nbAdherentParticipant = null;

  @JsonProperty("nbAdherentParticipantPas")
  private Integer nbAdherentParticipantPas = null;

  @JsonProperty("nbAdherentEnAttente")
  private Integer nbAdherentEnAttente = null;

  public SyntheseEvenement id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "1", description = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SyntheseEvenement type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Types evenement * 1 : CONFERENCE                * 2 : MANIFESTATION             * 3 : OBSERVATION * 4 : ANIMATION * 5 : REUNION * 6 : SORTIE * 7 : ATELIER * 8 : DIVERS 
   * @return type
   **/
  @Schema(example = "1", description = "Types evenement * 1 : CONFERENCE                * 2 : MANIFESTATION             * 3 : OBSERVATION * 4 : ANIMATION * 5 : REUNION * 6 : SORTIE * 7 : ATELIER * 8 : DIVERS ")
  
    public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public SyntheseEvenement description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "Conférence sur le soleil", description = "")
  
  @Size(min=3,max=100)   public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SyntheseEvenement detail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * Get detail
   * @return detail
   **/
  @Schema(example = "Conférence sur le soleil présenté par Monsieur Dupont", description = "")
  
  @Size(min=3,max=500)   public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public SyntheseEvenement datedebut(String datedebut) {
    this.datedebut = datedebut;
    return this;
  }

  /**
   * date de début au format iso8601
   * @return datedebut
   **/
  @Schema(example = "2020-01-18T21:00:00", description = "date de début au format iso8601")
  
    public String getDatedebut() {
    return datedebut;
  }

  public void setDatedebut(String datedebut) {
    this.datedebut = datedebut;
  }

  public SyntheseEvenement datefin(String datefin) {
    this.datefin = datefin;
    return this;
  }

  /**
   * date de fin au format iso8601
   * @return datefin
   **/
  @Schema(example = "2020-01-18T21:00:00", description = "date de fin au format iso8601")
  
    public String getDatefin() {
    return datefin;
  }

  public void setDatefin(String datefin) {
    this.datefin = datefin;
  }

  public SyntheseEvenement envoyerInfoAdherents(Boolean envoyerInfoAdherents) {
    this.envoyerInfoAdherents = envoyerInfoAdherents;
    return this;
  }

  /**
   * indique si le système doit envoyer un mail au adhérent à la suite de la création d'un évènement
   * @return envoyerInfoAdherents
   **/
  @Schema(example = "true", description = "indique si le système doit envoyer un mail au adhérent à la suite de la création d'un évènement")
  
    public Boolean isEnvoyerInfoAdherents() {
    return envoyerInfoAdherents;
  }

  public void setEnvoyerInfoAdherents(Boolean envoyerInfoAdherents) {
    this.envoyerInfoAdherents = envoyerInfoAdherents;
  }

  public SyntheseEvenement demanderConfirmationParticipation(Boolean demanderConfirmationParticipation) {
    this.demanderConfirmationParticipation = demanderConfirmationParticipation;
    return this;
  }

  /**
   * indique si il faut demander une confirmation de présence à l'adhérent
   * @return demanderConfirmationParticipation
   **/
  @Schema(example = "true", description = "indique si il faut demander une confirmation de présence à l'adhérent")
  
    public Boolean isDemanderConfirmationParticipation() {
    return demanderConfirmationParticipation;
  }

  public void setDemanderConfirmationParticipation(Boolean demanderConfirmationParticipation) {
    this.demanderConfirmationParticipation = demanderConfirmationParticipation;
  }

  public SyntheseEvenement nbAdherentParticipant(Integer nbAdherentParticipant) {
    this.nbAdherentParticipant = nbAdherentParticipant;
    return this;
  }

  /**
   * indique le nombre de participant à l evenement
   * @return nbAdherentParticipant
   **/
  @Schema(example = "50", description = "indique le nombre de participant à l evenement")
  
    public Integer getNbAdherentParticipant() {
    return nbAdherentParticipant;
  }

  public void setNbAdherentParticipant(Integer nbAdherentParticipant) {
    this.nbAdherentParticipant = nbAdherentParticipant;
  }

  public SyntheseEvenement nbAdherentParticipantPas(Integer nbAdherentParticipantPas) {
    this.nbAdherentParticipantPas = nbAdherentParticipantPas;
    return this;
  }

  /**
   * indique le nombre de participant pas à l evenement
   * @return nbAdherentParticipantPas
   **/
  @Schema(example = "50", description = "indique le nombre de participant pas à l evenement")
  
    public Integer getNbAdherentParticipantPas() {
    return nbAdherentParticipantPas;
  }

  public void setNbAdherentParticipantPas(Integer nbAdherentParticipantPas) {
    this.nbAdherentParticipantPas = nbAdherentParticipantPas;
  }

  public SyntheseEvenement nbAdherentEnAttente(Integer nbAdherentEnAttente) {
    this.nbAdherentEnAttente = nbAdherentEnAttente;
    return this;
  }

  /**
   * indique le nombre de participant en attente de retour
   * @return nbAdherentEnAttente
   **/
  @Schema(example = "50", description = "indique le nombre de participant en attente de retour")
  
    public Integer getNbAdherentEnAttente() {
    return nbAdherentEnAttente;
  }

  public void setNbAdherentEnAttente(Integer nbAdherentEnAttente) {
    this.nbAdherentEnAttente = nbAdherentEnAttente;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SyntheseEvenement syntheseEvenement = (SyntheseEvenement) o;
    return Objects.equals(this.id, syntheseEvenement.id) &&
        Objects.equals(this.type, syntheseEvenement.type) &&
        Objects.equals(this.description, syntheseEvenement.description) &&
        Objects.equals(this.detail, syntheseEvenement.detail) &&
        Objects.equals(this.datedebut, syntheseEvenement.datedebut) &&
        Objects.equals(this.datefin, syntheseEvenement.datefin) &&
        Objects.equals(this.envoyerInfoAdherents, syntheseEvenement.envoyerInfoAdherents) &&
        Objects.equals(this.demanderConfirmationParticipation, syntheseEvenement.demanderConfirmationParticipation) &&
        Objects.equals(this.nbAdherentParticipant, syntheseEvenement.nbAdherentParticipant) &&
        Objects.equals(this.nbAdherentParticipantPas, syntheseEvenement.nbAdherentParticipantPas) &&
        Objects.equals(this.nbAdherentEnAttente, syntheseEvenement.nbAdherentEnAttente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, description, detail, datedebut, datefin, envoyerInfoAdherents, demanderConfirmationParticipation, nbAdherentParticipant, nbAdherentParticipantPas, nbAdherentEnAttente);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SyntheseEvenement {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    datedebut: ").append(toIndentedString(datedebut)).append("\n");
    sb.append("    datefin: ").append(toIndentedString(datefin)).append("\n");
    sb.append("    envoyerInfoAdherents: ").append(toIndentedString(envoyerInfoAdherents)).append("\n");
    sb.append("    demanderConfirmationParticipation: ").append(toIndentedString(demanderConfirmationParticipation)).append("\n");
    sb.append("    nbAdherentParticipant: ").append(toIndentedString(nbAdherentParticipant)).append("\n");
    sb.append("    nbAdherentParticipantPas: ").append(toIndentedString(nbAdherentParticipantPas)).append("\n");
    sb.append("    nbAdherentEnAttente: ").append(toIndentedString(nbAdherentEnAttente)).append("\n");
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
