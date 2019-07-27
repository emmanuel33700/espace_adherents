package fr.espaceadh.autorisation.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Adherent
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-20T15:15:37.676Z[GMT]")
public class Adherent   {
  @JsonProperty("id")
  private Long id = null;

  /**
   * civilite de l'adhenrent
   */
  public enum CiviliteEnum {
    MONSIEUR("Monsieur"),
    
    MADAME("Madame");

    private String value;

    CiviliteEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CiviliteEnum fromValue(String text) {
      for (CiviliteEnum b : CiviliteEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("civilite")
  private CiviliteEnum civilite = null;

  @JsonProperty("nom")
  private String nom = null;

  @JsonProperty("prenom")
  private String prenom = null;

  @JsonProperty("adresse1")
  private String adresse1 = null;

  @JsonProperty("adresse2")
  private String adresse2 = null;

  @JsonProperty("codePostal")
  private String codePostal = null;

  @JsonProperty("telMaison")
  private String telMaison = null;

  @JsonProperty("telTravail")
  private String telTravail = null;

  @JsonProperty("telPortail")
  private String telPortail = null;

  /**
   * privilege
   */
  public enum PrivilegeEnum {
    ADHERENT("Adherent"),
    
    CONSEIL_ADMINISTRATION("Conseil administration"),
    
    BUREAU("Bureau"),
    
    ADMIN("Admin");

    private String value;

    PrivilegeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PrivilegeEnum fromValue(String text) {
      for (PrivilegeEnum b : PrivilegeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("privilege")
  private PrivilegeEnum privilege = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("profession")
  private String profession = null;

  @JsonProperty("dateNaissance")
  private OffsetDateTime dateNaissance = null;

  @JsonProperty("telescope")
  private Boolean telescope = null;

  @JsonProperty("lunette")
  private Boolean lunette = null;

  @JsonProperty("jumelles")
  private Boolean jumelles = null;

  @JsonProperty("modelInstrument")
  private String modelInstrument = null;

  @JsonProperty("commentaire")
  private String commentaire = null;

  public Adherent id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Adherent civilite(CiviliteEnum civilite) {
    this.civilite = civilite;
    return this;
  }

  /**
   * civilite de l'adhenrent
   * @return civilite
  **/
  @ApiModelProperty(value = "civilite de l'adhenrent")

  public CiviliteEnum getCivilite() {
    return civilite;
  }

  public void setCivilite(CiviliteEnum civilite) {
    this.civilite = civilite;
  }

  public Adherent nom(String nom) {
    this.nom = nom;
    return this;
  }

  /**
   * Get nom
   * @return nom
  **/
  @ApiModelProperty(value = "")

@Size(min=3,max=20)   public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Adherent prenom(String prenom) {
    this.prenom = prenom;
    return this;
  }

  /**
   * Get prenom
   * @return prenom
  **/
  @ApiModelProperty(value = "")

@Size(min=3,max=20)   public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public Adherent adresse1(String adresse1) {
    this.adresse1 = adresse1;
    return this;
  }

  /**
   * Get adresse1
   * @return adresse1
  **/
  @ApiModelProperty(value = "")

@Size(min=3,max=37)   public String getAdresse1() {
    return adresse1;
  }

  public void setAdresse1(String adresse1) {
    this.adresse1 = adresse1;
  }

  public Adherent adresse2(String adresse2) {
    this.adresse2 = adresse2;
    return this;
  }

  /**
   * Get adresse2
   * @return adresse2
  **/
  @ApiModelProperty(value = "")

@Size(min=3,max=37)   public String getAdresse2() {
    return adresse2;
  }

  public void setAdresse2(String adresse2) {
    this.adresse2 = adresse2;
  }

  public Adherent codePostal(String codePostal) {
    this.codePostal = codePostal;
    return this;
  }

  /**
   * Get codePostal
   * @return codePostal
  **/
  @ApiModelProperty(value = "")

@Size(min=5,max=5)   public String getCodePostal() {
    return codePostal;
  }

  public void setCodePostal(String codePostal) {
    this.codePostal = codePostal;
  }

  public Adherent telMaison(String telMaison) {
    this.telMaison = telMaison;
    return this;
  }

  /**
   * Get telMaison
   * @return telMaison
  **/
  @ApiModelProperty(value = "")

@Size(min=10,max=10)   public String getTelMaison() {
    return telMaison;
  }

  public void setTelMaison(String telMaison) {
    this.telMaison = telMaison;
  }

  public Adherent telTravail(String telTravail) {
    this.telTravail = telTravail;
    return this;
  }

  /**
   * Get telTravail
   * @return telTravail
  **/
  @ApiModelProperty(value = "")

@Size(min=10,max=10)   public String getTelTravail() {
    return telTravail;
  }

  public void setTelTravail(String telTravail) {
    this.telTravail = telTravail;
  }

  public Adherent telPortail(String telPortail) {
    this.telPortail = telPortail;
    return this;
  }

  /**
   * Get telPortail
   * @return telPortail
  **/
  @ApiModelProperty(value = "")

@Size(min=10,max=10)   public String getTelPortail() {
    return telPortail;
  }

  public void setTelPortail(String telPortail) {
    this.telPortail = telPortail;
  }

  public Adherent privilege(PrivilegeEnum privilege) {
    this.privilege = privilege;
    return this;
  }

  /**
   * privilege
   * @return privilege
  **/
  @ApiModelProperty(value = "privilege")

  public PrivilegeEnum getPrivilege() {
    return privilege;
  }

  public void setPrivilege(PrivilegeEnum privilege) {
    this.privilege = privilege;
  }

  public Adherent email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")

@Size(min=5,max=40)   public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Adherent profession(String profession) {
    this.profession = profession;
    return this;
  }

  /**
   * Get profession
   * @return profession
  **/
  @ApiModelProperty(value = "")

@Size(min=5,max=50)   public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public Adherent dateNaissance(OffsetDateTime dateNaissance) {
    this.dateNaissance = dateNaissance;
    return this;
  }

  /**
   * Get dateNaissance
   * @return dateNaissance
  **/
  @ApiModelProperty(value = "")

  @Valid
  public OffsetDateTime getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance(OffsetDateTime dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  public Adherent telescope(Boolean telescope) {
    this.telescope = telescope;
    return this;
  }

  /**
   * Get telescope
   * @return telescope
  **/
  @ApiModelProperty(value = "")

  public Boolean isTelescope() {
    return telescope;
  }

  public void setTelescope(Boolean telescope) {
    this.telescope = telescope;
  }

  public Adherent lunette(Boolean lunette) {
    this.lunette = lunette;
    return this;
  }

  /**
   * Get lunette
   * @return lunette
  **/
  @ApiModelProperty(value = "")

  public Boolean isLunette() {
    return lunette;
  }

  public void setLunette(Boolean lunette) {
    this.lunette = lunette;
  }

  public Adherent jumelles(Boolean jumelles) {
    this.jumelles = jumelles;
    return this;
  }

  /**
   * Get jumelles
   * @return jumelles
  **/
  @ApiModelProperty(value = "")

  public Boolean isJumelles() {
    return jumelles;
  }

  public void setJumelles(Boolean jumelles) {
    this.jumelles = jumelles;
  }

  public Adherent modelInstrument(String modelInstrument) {
    this.modelInstrument = modelInstrument;
    return this;
  }

  /**
   * Get modelInstrument
   * @return modelInstrument
  **/
  @ApiModelProperty(value = "")

  public String getModelInstrument() {
    return modelInstrument;
  }

  public void setModelInstrument(String modelInstrument) {
    this.modelInstrument = modelInstrument;
  }

  public Adherent commentaire(String commentaire) {
    this.commentaire = commentaire;
    return this;
  }

  /**
   * Get commentaire
   * @return commentaire
  **/
  @ApiModelProperty(value = "")

@Size(min=2,max=100)   public String getCommentaire() {
    return commentaire;
  }

  public void setCommentaire(String commentaire) {
    this.commentaire = commentaire;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Adherent adherent = (Adherent) o;
    return Objects.equals(this.id, adherent.id) &&
        Objects.equals(this.civilite, adherent.civilite) &&
        Objects.equals(this.nom, adherent.nom) &&
        Objects.equals(this.prenom, adherent.prenom) &&
        Objects.equals(this.adresse1, adherent.adresse1) &&
        Objects.equals(this.adresse2, adherent.adresse2) &&
        Objects.equals(this.codePostal, adherent.codePostal) &&
        Objects.equals(this.telMaison, adherent.telMaison) &&
        Objects.equals(this.telTravail, adherent.telTravail) &&
        Objects.equals(this.telPortail, adherent.telPortail) &&
        Objects.equals(this.privilege, adherent.privilege) &&
        Objects.equals(this.email, adherent.email) &&
        Objects.equals(this.profession, adherent.profession) &&
        Objects.equals(this.dateNaissance, adherent.dateNaissance) &&
        Objects.equals(this.telescope, adherent.telescope) &&
        Objects.equals(this.lunette, adherent.lunette) &&
        Objects.equals(this.jumelles, adherent.jumelles) &&
        Objects.equals(this.modelInstrument, adherent.modelInstrument) &&
        Objects.equals(this.commentaire, adherent.commentaire);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, civilite, nom, prenom, adresse1, adresse2, codePostal, telMaison, telTravail, telPortail, privilege, email, profession, dateNaissance, telescope, lunette, jumelles, modelInstrument, commentaire);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Adherent {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    civilite: ").append(toIndentedString(civilite)).append("\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    prenom: ").append(toIndentedString(prenom)).append("\n");
    sb.append("    adresse1: ").append(toIndentedString(adresse1)).append("\n");
    sb.append("    adresse2: ").append(toIndentedString(adresse2)).append("\n");
    sb.append("    codePostal: ").append(toIndentedString(codePostal)).append("\n");
    sb.append("    telMaison: ").append(toIndentedString(telMaison)).append("\n");
    sb.append("    telTravail: ").append(toIndentedString(telTravail)).append("\n");
    sb.append("    telPortail: ").append(toIndentedString(telPortail)).append("\n");
    sb.append("    privilege: ").append(toIndentedString(privilege)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    profession: ").append(toIndentedString(profession)).append("\n");
    sb.append("    dateNaissance: ").append(toIndentedString(dateNaissance)).append("\n");
    sb.append("    telescope: ").append(toIndentedString(telescope)).append("\n");
    sb.append("    lunette: ").append(toIndentedString(lunette)).append("\n");
    sb.append("    jumelles: ").append(toIndentedString(jumelles)).append("\n");
    sb.append("    modelInstrument: ").append(toIndentedString(modelInstrument)).append("\n");
    sb.append("    commentaire: ").append(toIndentedString(commentaire)).append("\n");
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
