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
 * Manifestation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-07T09:56:02.754Z[GMT]")


public class Manifestation   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("idAdherent")
  private Long idAdherent = null;

  @JsonProperty("descriptionCourte")
  private String descriptionCourte = null;

  @JsonProperty("descriptionLongue")
  private String descriptionLongue = null;

  @JsonProperty("lieux")
  private String lieux = null;

  @JsonProperty("dateDebut")
  private String dateDebut = null;

  @JsonProperty("dateFin")
  private String dateFin = null;

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

  /**
   * Types de participation. Valeurs possibles : * 1 : PARTICIPE                * 2 : PARTICIPE PAS              * 3 : NE SAIS PAS                
   */
  public enum StatutParticipationEnum {
    NUMBER_1(1l),
    
    NUMBER_2(2l),
    
    NUMBER_3(3l);

    private Long value;

    StatutParticipationEnum(Long value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatutParticipationEnum fromValue(String text) {
      for (StatutParticipationEnum b : StatutParticipationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("statutParticipation")
  private StatutParticipationEnum statutParticipation = null;

  public Manifestation id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Id de la manifestation
   * @return id
   **/
  @Schema(example = "1", description = "Id de la manifestation")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Manifestation idAdherent(Long idAdherent) {
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

  public Manifestation descriptionCourte(String descriptionCourte) {
    this.descriptionCourte = descriptionCourte;
    return this;
  }

  /**
   * Description courte de l'évènement
   * @return descriptionCourte
   **/
  @Schema(example = "Manifestation à la conférence", description = "Description courte de l'évènement")
  
  @Size(min=2,max=100)   public String getDescriptionCourte() {
    return descriptionCourte;
  }

  public void setDescriptionCourte(String descriptionCourte) {
    this.descriptionCourte = descriptionCourte;
  }

  public Manifestation descriptionLongue(String descriptionLongue) {
    this.descriptionLongue = descriptionLongue;
    return this;
  }

  /**
   * Description longue de l'évènement
   * @return descriptionLongue
   **/
  @Schema(example = "Manifestation à la conférence sur le soleil qui sera présenté par Mr dupond", description = "Description longue de l'évènement")
  
  @Size(min=2,max=500)   public String getDescriptionLongue() {
    return descriptionLongue;
  }

  public void setDescriptionLongue(String descriptionLongue) {
    this.descriptionLongue = descriptionLongue;
  }

  public Manifestation lieux(String lieux) {
    this.lieux = lieux;
    return this;
  }

  /**
   * Description du lieux de l'évènement
   * @return lieux
   **/
  @Schema(example = "Martignas", description = "Description du lieux de l'évènement")
  
  @Size(min=2,max=100)   public String getLieux() {
    return lieux;
  }

  public void setLieux(String lieux) {
    this.lieux = lieux;
  }

  public Manifestation dateDebut(String dateDebut) {
    this.dateDebut = dateDebut;
    return this;
  }

  /**
   * Date de début de l'évènement (format ISO8601)
   * @return dateDebut
   **/
  @Schema(example = "2020-01-18T21:00:00", description = "Date de début de l'évènement (format ISO8601)")
  
    public String getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(String dateDebut) {
    this.dateDebut = dateDebut;
  }

  public Manifestation dateFin(String dateFin) {
    this.dateFin = dateFin;
    return this;
  }

  /**
   * Date de fin de l'évènement
   * @return dateFin
   **/
  @Schema(example = "2020-01-18T21:00:00", description = "Date de fin de l'évènement")
  
    public String getDateFin() {
    return dateFin;
  }

  public void setDateFin(String dateFin) {
    this.dateFin = dateFin;
  }

  public Manifestation type(TypeEnum type) {
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

  public Manifestation statutParticipation(StatutParticipationEnum statutParticipation) {
    this.statutParticipation = statutParticipation;
    return this;
  }

  /**
   * Types de participation. Valeurs possibles : * 1 : PARTICIPE                * 2 : PARTICIPE PAS              * 3 : NE SAIS PAS                
   * @return statutParticipation
   **/
  @Schema(example = "1", description = "Types de participation. Valeurs possibles : * 1 : PARTICIPE                * 2 : PARTICIPE PAS              * 3 : NE SAIS PAS                ")
  
    public StatutParticipationEnum getStatutParticipation() {
    return statutParticipation;
  }

  public void setStatutParticipation(StatutParticipationEnum statutParticipation) {
    this.statutParticipation = statutParticipation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Manifestation manifestation = (Manifestation) o;
    return Objects.equals(this.id, manifestation.id) &&
        Objects.equals(this.idAdherent, manifestation.idAdherent) &&
        Objects.equals(this.descriptionCourte, manifestation.descriptionCourte) &&
        Objects.equals(this.descriptionLongue, manifestation.descriptionLongue) &&
        Objects.equals(this.lieux, manifestation.lieux) &&
        Objects.equals(this.dateDebut, manifestation.dateDebut) &&
        Objects.equals(this.dateFin, manifestation.dateFin) &&
        Objects.equals(this.type, manifestation.type) &&
        Objects.equals(this.statutParticipation, manifestation.statutParticipation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idAdherent, descriptionCourte, descriptionLongue, lieux, dateDebut, dateFin, type, statutParticipation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Manifestation {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idAdherent: ").append(toIndentedString(idAdherent)).append("\n");
    sb.append("    descriptionCourte: ").append(toIndentedString(descriptionCourte)).append("\n");
    sb.append("    descriptionLongue: ").append(toIndentedString(descriptionLongue)).append("\n");
    sb.append("    lieux: ").append(toIndentedString(lieux)).append("\n");
    sb.append("    dateDebut: ").append(toIndentedString(dateDebut)).append("\n");
    sb.append("    dateFin: ").append(toIndentedString(dateFin)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    statutParticipation: ").append(toIndentedString(statutParticipation)).append("\n");
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
