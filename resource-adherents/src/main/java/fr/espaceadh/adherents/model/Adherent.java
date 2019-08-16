package fr.espaceadh.adherents.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Adherent
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-16T13:27:11.143Z[GMT]")
public class Adherent   {
  @JsonProperty("id")
  private Long id = null;

  /**
   * civilite de l'adhenrent
   */
  public enum CiviliteEnum {
    MR("Mr"),
    
    MME("Mme");

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

  @JsonProperty("ville")
  private String ville = null;

  @JsonProperty("telMaison")
  private String telMaison = null;

  @JsonProperty("telTravail")
  private String telTravail = null;

  @JsonProperty("telPortable")
  private String telPortable = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("profession")
  private String profession = null;

  @JsonProperty("dateNaissance")
  private OffsetDateTime dateNaissance = null;

  @JsonProperty("lienPhotoProfil")
  private String lienPhotoProfil = null;

  @JsonProperty("accordMail")
  private Boolean accordMail = null;

  @JsonProperty("publicContact")
  private Boolean publicContact = null;

  @JsonProperty("commentaire")
  private String commentaire = null;

  @JsonProperty("dateEnregistrement")
  private OffsetDateTime dateEnregistrement = null;

  @JsonProperty("dateMiseAJour")
  private OffsetDateTime dateMiseAJour = null;

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

  public Adherent ville(String ville) {
    this.ville = ville;
    return this;
  }

  /**
   * Get ville
   * @return ville
  **/
  @ApiModelProperty(value = "")

@Size(min=3,max=50)   public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
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

  public Adherent telPortable(String telPortable) {
    this.telPortable = telPortable;
    return this;
  }

  /**
   * Get telPortable
   * @return telPortable
  **/
  @ApiModelProperty(value = "")

@Size(min=10,max=10)   public String getTelPortable() {
    return telPortable;
  }

  public void setTelPortable(String telPortable) {
    this.telPortable = telPortable;
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

  public Adherent lienPhotoProfil(String lienPhotoProfil) {
    this.lienPhotoProfil = lienPhotoProfil;
    return this;
  }

  /**
   * lien vers la photo de profile
   * @return lienPhotoProfil
  **/
  @ApiModelProperty(value = "lien vers la photo de profile")

  public String getLienPhotoProfil() {
    return lienPhotoProfil;
  }

  public void setLienPhotoProfil(String lienPhotoProfil) {
    this.lienPhotoProfil = lienPhotoProfil;
  }

  public Adherent accordMail(Boolean accordMail) {
    this.accordMail = accordMail;
    return this;
  }

  /**
   * donne son accord pour recevoiur des mails
   * @return accordMail
  **/
  @ApiModelProperty(value = "donne son accord pour recevoiur des mails")

  public Boolean isAccordMail() {
    return accordMail;
  }

  public void setAccordMail(Boolean accordMail) {
    this.accordMail = accordMail;
  }

  public Adherent publicContact(Boolean publicContact) {
    this.publicContact = publicContact;
    return this;
  }

  /**
   * donne son accord afficher ces coordonnées dans l'espace adherents
   * @return publicContact
  **/
  @ApiModelProperty(value = "donne son accord afficher ces coordonnées dans l'espace adherents")

  public Boolean isPublicContact() {
    return publicContact;
  }

  public void setPublicContact(Boolean publicContact) {
    this.publicContact = publicContact;
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

  public Adherent dateEnregistrement(OffsetDateTime dateEnregistrement) {
    this.dateEnregistrement = dateEnregistrement;
    return this;
  }

  /**
   * Get dateEnregistrement
   * @return dateEnregistrement
  **/
  @ApiModelProperty(value = "")

  @Valid
  public OffsetDateTime getDateEnregistrement() {
    return dateEnregistrement;
  }

  public void setDateEnregistrement(OffsetDateTime dateEnregistrement) {
    this.dateEnregistrement = dateEnregistrement;
  }

  public Adherent dateMiseAJour(OffsetDateTime dateMiseAJour) {
    this.dateMiseAJour = dateMiseAJour;
    return this;
  }

  /**
   * Get dateMiseAJour
   * @return dateMiseAJour
  **/
  @ApiModelProperty(value = "")

  @Valid
  public OffsetDateTime getDateMiseAJour() {
    return dateMiseAJour;
  }

  public void setDateMiseAJour(OffsetDateTime dateMiseAJour) {
    this.dateMiseAJour = dateMiseAJour;
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
        Objects.equals(this.ville, adherent.ville) &&
        Objects.equals(this.telMaison, adherent.telMaison) &&
        Objects.equals(this.telTravail, adherent.telTravail) &&
        Objects.equals(this.telPortable, adherent.telPortable) &&
        Objects.equals(this.email, adherent.email) &&
        Objects.equals(this.profession, adherent.profession) &&
        Objects.equals(this.dateNaissance, adherent.dateNaissance) &&
        Objects.equals(this.lienPhotoProfil, adherent.lienPhotoProfil) &&
        Objects.equals(this.accordMail, adherent.accordMail) &&
        Objects.equals(this.publicContact, adherent.publicContact) &&
        Objects.equals(this.commentaire, adherent.commentaire) &&
        Objects.equals(this.dateEnregistrement, adherent.dateEnregistrement) &&
        Objects.equals(this.dateMiseAJour, adherent.dateMiseAJour);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, civilite, nom, prenom, adresse1, adresse2, codePostal, ville, telMaison, telTravail, telPortable, email, profession, dateNaissance, lienPhotoProfil, accordMail, publicContact, commentaire, dateEnregistrement, dateMiseAJour);
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
    sb.append("    ville: ").append(toIndentedString(ville)).append("\n");
    sb.append("    telMaison: ").append(toIndentedString(telMaison)).append("\n");
    sb.append("    telTravail: ").append(toIndentedString(telTravail)).append("\n");
    sb.append("    telPortable: ").append(toIndentedString(telPortable)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    profession: ").append(toIndentedString(profession)).append("\n");
    sb.append("    dateNaissance: ").append(toIndentedString(dateNaissance)).append("\n");
    sb.append("    lienPhotoProfil: ").append(toIndentedString(lienPhotoProfil)).append("\n");
    sb.append("    accordMail: ").append(toIndentedString(accordMail)).append("\n");
    sb.append("    publicContact: ").append(toIndentedString(publicContact)).append("\n");
    sb.append("    commentaire: ").append(toIndentedString(commentaire)).append("\n");
    sb.append("    dateEnregistrement: ").append(toIndentedString(dateEnregistrement)).append("\n");
    sb.append("    dateMiseAJour: ").append(toIndentedString(dateMiseAJour)).append("\n");
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
