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
 * ParticipationManifestation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-07T09:56:02.754Z[GMT]")


public class ParticipationManifestation   {
  /**
   * Types de participation. Valeurs possibles : * 1 : PARTICIPE                * 2 : PARTICIPE PAS              
   */
  public enum StatutParticipationEnum {
    NUMBER_1(1l),
    
    NUMBER_2(2l);

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

  public ParticipationManifestation statutParticipation(StatutParticipationEnum statutParticipation) {
    this.statutParticipation = statutParticipation;
    return this;
  }

  /**
   * Types de participation. Valeurs possibles : * 1 : PARTICIPE                * 2 : PARTICIPE PAS              
   * @return statutParticipation
   **/
  @Schema(example = "1", description = "Types de participation. Valeurs possibles : * 1 : PARTICIPE                * 2 : PARTICIPE PAS              ")
  
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
    ParticipationManifestation participationManifestation = (ParticipationManifestation) o;
    return Objects.equals(this.statutParticipation, participationManifestation.statutParticipation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statutParticipation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParticipationManifestation {\n");
    
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
