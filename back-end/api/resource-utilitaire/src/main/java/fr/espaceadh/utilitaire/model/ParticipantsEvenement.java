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
 * ParticipantsEvenement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-09T20:07:09.346Z[GMT]")


public class ParticipantsEvenement   {
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
  private String dateNaissance = null;

  @JsonProperty("lienPhotoProfil")
  private String lienPhotoProfil = null;

  @JsonProperty("accordMail")
  private Boolean accordMail = null;

  @JsonProperty("publicContact")
  private Boolean publicContact = null;

  @JsonProperty("commentaire")
  private String commentaire = null;

  @JsonProperty("dateEnregistrement")
  private String dateEnregistrement = null;

  @JsonProperty("dateMiseAJour")
  private String dateMiseAJour = null;

  @JsonProperty("adhesionsSaisonCourante")
  private Boolean adhesionsSaisonCourante = null;

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

  public ParticipantsEvenement id(Long id) {
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

  public ParticipantsEvenement civilite(CiviliteEnum civilite) {
    this.civilite = civilite;
    return this;
  }

  /**
   * civilite de l'adhenrent
   * @return civilite
   **/
  @Schema(description = "civilite de l'adhenrent")
  
    public CiviliteEnum getCivilite() {
    return civilite;
  }

  public void setCivilite(CiviliteEnum civilite) {
    this.civilite = civilite;
  }

  public ParticipantsEvenement nom(String nom) {
    this.nom = nom;
    return this;
  }

  /**
   * Get nom
   * @return nom
   **/
  @Schema(example = "Dupont", description = "")
  
  @Size(min=3,max=20)   public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public ParticipantsEvenement prenom(String prenom) {
    this.prenom = prenom;
    return this;
  }

  /**
   * Get prenom
   * @return prenom
   **/
  @Schema(example = "Jean", description = "")
  
  @Size(min=3,max=20)   public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public ParticipantsEvenement adresse1(String adresse1) {
    this.adresse1 = adresse1;
    return this;
  }

  /**
   * Get adresse1
   * @return adresse1
   **/
  @Schema(example = "6 rue des adresse", description = "")
  
  @Size(min=3,max=37)   public String getAdresse1() {
    return adresse1;
  }

  public void setAdresse1(String adresse1) {
    this.adresse1 = adresse1;
  }

  public ParticipantsEvenement adresse2(String adresse2) {
    this.adresse2 = adresse2;
    return this;
  }

  /**
   * Get adresse2
   * @return adresse2
   **/
  @Schema(example = "domaine des adresse", description = "")
  
  @Size(min=3,max=37)   public String getAdresse2() {
    return adresse2;
  }

  public void setAdresse2(String adresse2) {
    this.adresse2 = adresse2;
  }

  public ParticipantsEvenement codePostal(String codePostal) {
    this.codePostal = codePostal;
    return this;
  }

  /**
   * Get codePostal
   * @return codePostal
   **/
  @Schema(example = "33127", description = "")
  
  @Size(min=5,max=5)   public String getCodePostal() {
    return codePostal;
  }

  public void setCodePostal(String codePostal) {
    this.codePostal = codePostal;
  }

  public ParticipantsEvenement ville(String ville) {
    this.ville = ville;
    return this;
  }

  /**
   * Get ville
   * @return ville
   **/
  @Schema(example = "Martignas", description = "")
  
  @Size(min=3,max=50)   public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }

  public ParticipantsEvenement telMaison(String telMaison) {
    this.telMaison = telMaison;
    return this;
  }

  /**
   * Get telMaison
   * @return telMaison
   **/
  @Schema(example = "95869805", description = "")
  
  @Size(min=10,max=10)   public String getTelMaison() {
    return telMaison;
  }

  public void setTelMaison(String telMaison) {
    this.telMaison = telMaison;
  }

  public ParticipantsEvenement telTravail(String telTravail) {
    this.telTravail = telTravail;
    return this;
  }

  /**
   * Get telTravail
   * @return telTravail
   **/
  @Schema(example = "95869805", description = "")
  
  @Size(min=10,max=10)   public String getTelTravail() {
    return telTravail;
  }

  public void setTelTravail(String telTravail) {
    this.telTravail = telTravail;
  }

  public ParticipantsEvenement telPortable(String telPortable) {
    this.telPortable = telPortable;
    return this;
  }

  /**
   * Get telPortable
   * @return telPortable
   **/
  @Schema(example = "112647021", description = "")
  
  @Size(min=10,max=10)   public String getTelPortable() {
    return telPortable;
  }

  public void setTelPortable(String telPortable) {
    this.telPortable = telPortable;
  }

  public ParticipantsEvenement email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @Schema(example = "test.test@gmail.com", description = "")
  
  @Size(min=5,max=40)   public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ParticipantsEvenement profession(String profession) {
    this.profession = profession;
    return this;
  }

  /**
   * Get profession
   * @return profession
   **/
  @Schema(example = "Astronome", description = "")
  
  @Size(min=5,max=50)   public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public ParticipantsEvenement dateNaissance(String dateNaissance) {
    this.dateNaissance = dateNaissance;
    return this;
  }

  /**
   * date de naissance au format iso8601
   * @return dateNaissance
   **/
  @Schema(example = "2020-01-18T21:00:00", description = "date de naissance au format iso8601")
  
    public String getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance(String dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  public ParticipantsEvenement lienPhotoProfil(String lienPhotoProfil) {
    this.lienPhotoProfil = lienPhotoProfil;
    return this;
  }

  /**
   * lien vers la photo de profile
   * @return lienPhotoProfil
   **/
  @Schema(example = "photo.jpg", description = "lien vers la photo de profile")
  
    public String getLienPhotoProfil() {
    return lienPhotoProfil;
  }

  public void setLienPhotoProfil(String lienPhotoProfil) {
    this.lienPhotoProfil = lienPhotoProfil;
  }

  public ParticipantsEvenement accordMail(Boolean accordMail) {
    this.accordMail = accordMail;
    return this;
  }

  /**
   * donne son accord pour recevoiur des mails
   * @return accordMail
   **/
  @Schema(example = "true", description = "donne son accord pour recevoiur des mails")
  
    public Boolean isAccordMail() {
    return accordMail;
  }

  public void setAccordMail(Boolean accordMail) {
    this.accordMail = accordMail;
  }

  public ParticipantsEvenement publicContact(Boolean publicContact) {
    this.publicContact = publicContact;
    return this;
  }

  /**
   * donne son accord afficher ces coordonnées dans l'espace adherents
   * @return publicContact
   **/
  @Schema(example = "false", description = "donne son accord afficher ces coordonnées dans l'espace adherents")
  
    public Boolean isPublicContact() {
    return publicContact;
  }

  public void setPublicContact(Boolean publicContact) {
    this.publicContact = publicContact;
  }

  public ParticipantsEvenement commentaire(String commentaire) {
    this.commentaire = commentaire;
    return this;
  }

  /**
   * Get commentaire
   * @return commentaire
   **/
  @Schema(example = "voici un commentaire", description = "")
  
  @Size(min=2,max=100)   public String getCommentaire() {
    return commentaire;
  }

  public void setCommentaire(String commentaire) {
    this.commentaire = commentaire;
  }

  public ParticipantsEvenement dateEnregistrement(String dateEnregistrement) {
    this.dateEnregistrement = dateEnregistrement;
    return this;
  }

  /**
   * date de'enregistrement au format iso8601
   * @return dateEnregistrement
   **/
  @Schema(example = "2020-01-18T21:00:00", description = "date de'enregistrement au format iso8601")
  
    public String getDateEnregistrement() {
    return dateEnregistrement;
  }

  public void setDateEnregistrement(String dateEnregistrement) {
    this.dateEnregistrement = dateEnregistrement;
  }

  public ParticipantsEvenement dateMiseAJour(String dateMiseAJour) {
    this.dateMiseAJour = dateMiseAJour;
    return this;
  }

  /**
   * date de mise à jour au format iso8601
   * @return dateMiseAJour
   **/
  @Schema(example = "2020-01-18T21:00:00", description = "date de mise à jour au format iso8601")
  
    public String getDateMiseAJour() {
    return dateMiseAJour;
  }

  public void setDateMiseAJour(String dateMiseAJour) {
    this.dateMiseAJour = dateMiseAJour;
  }

  public ParticipantsEvenement adhesionsSaisonCourante(Boolean adhesionsSaisonCourante) {
    this.adhesionsSaisonCourante = adhesionsSaisonCourante;
    return this;
  }

  /**
   * Indique si l'adhérent est adhérent dans la saison courante
   * @return adhesionsSaisonCourante
   **/
  @Schema(example = "true", description = "Indique si l'adhérent est adhérent dans la saison courante")
  
    public Boolean isAdhesionsSaisonCourante() {
    return adhesionsSaisonCourante;
  }

  public void setAdhesionsSaisonCourante(Boolean adhesionsSaisonCourante) {
    this.adhesionsSaisonCourante = adhesionsSaisonCourante;
  }

  public ParticipantsEvenement statutParticipation(StatutParticipationEnum statutParticipation) {
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
    ParticipantsEvenement participantsEvenement = (ParticipantsEvenement) o;
    return Objects.equals(this.id, participantsEvenement.id) &&
        Objects.equals(this.civilite, participantsEvenement.civilite) &&
        Objects.equals(this.nom, participantsEvenement.nom) &&
        Objects.equals(this.prenom, participantsEvenement.prenom) &&
        Objects.equals(this.adresse1, participantsEvenement.adresse1) &&
        Objects.equals(this.adresse2, participantsEvenement.adresse2) &&
        Objects.equals(this.codePostal, participantsEvenement.codePostal) &&
        Objects.equals(this.ville, participantsEvenement.ville) &&
        Objects.equals(this.telMaison, participantsEvenement.telMaison) &&
        Objects.equals(this.telTravail, participantsEvenement.telTravail) &&
        Objects.equals(this.telPortable, participantsEvenement.telPortable) &&
        Objects.equals(this.email, participantsEvenement.email) &&
        Objects.equals(this.profession, participantsEvenement.profession) &&
        Objects.equals(this.dateNaissance, participantsEvenement.dateNaissance) &&
        Objects.equals(this.lienPhotoProfil, participantsEvenement.lienPhotoProfil) &&
        Objects.equals(this.accordMail, participantsEvenement.accordMail) &&
        Objects.equals(this.publicContact, participantsEvenement.publicContact) &&
        Objects.equals(this.commentaire, participantsEvenement.commentaire) &&
        Objects.equals(this.dateEnregistrement, participantsEvenement.dateEnregistrement) &&
        Objects.equals(this.dateMiseAJour, participantsEvenement.dateMiseAJour) &&
        Objects.equals(this.adhesionsSaisonCourante, participantsEvenement.adhesionsSaisonCourante) &&
        Objects.equals(this.statutParticipation, participantsEvenement.statutParticipation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, civilite, nom, prenom, adresse1, adresse2, codePostal, ville, telMaison, telTravail, telPortable, email, profession, dateNaissance, lienPhotoProfil, accordMail, publicContact, commentaire, dateEnregistrement, dateMiseAJour, adhesionsSaisonCourante, statutParticipation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParticipantsEvenement {\n");
    
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
    sb.append("    adhesionsSaisonCourante: ").append(toIndentedString(adhesionsSaisonCourante)).append("\n");
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
