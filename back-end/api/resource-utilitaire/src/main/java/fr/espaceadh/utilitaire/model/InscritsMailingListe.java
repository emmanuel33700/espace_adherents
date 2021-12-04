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
 * InscritsMailingListe
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-04T15:52:57.795Z[GMT]")


public class InscritsMailingListe   {
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

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("lienPhotoProfil")
  private String lienPhotoProfil = null;

  @JsonProperty("accordMail")
  private Boolean accordMail = null;

  @JsonProperty("publicContact")
  private Boolean publicContact = null;

  @JsonProperty("adhesionsSaisonCourante")
  private Boolean adhesionsSaisonCourante = null;

  @JsonProperty("statutParticipation")
  private Boolean statutParticipation = null;

  public InscritsMailingListe id(Long id) {
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

  public InscritsMailingListe civilite(CiviliteEnum civilite) {
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

  public InscritsMailingListe nom(String nom) {
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

  public InscritsMailingListe prenom(String prenom) {
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

  public InscritsMailingListe email(String email) {
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

  public InscritsMailingListe lienPhotoProfil(String lienPhotoProfil) {
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

  public InscritsMailingListe accordMail(Boolean accordMail) {
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

  public InscritsMailingListe publicContact(Boolean publicContact) {
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

  public InscritsMailingListe adhesionsSaisonCourante(Boolean adhesionsSaisonCourante) {
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

  public InscritsMailingListe statutParticipation(Boolean statutParticipation) {
    this.statutParticipation = statutParticipation;
    return this;
  }

  /**
   * inscrit au la mailing liste
   * @return statutParticipation
   **/
  @Schema(example = "true", description = "inscrit au la mailing liste")
  
    public Boolean isStatutParticipation() {
    return statutParticipation;
  }

  public void setStatutParticipation(Boolean statutParticipation) {
    this.statutParticipation = statutParticipation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InscritsMailingListe inscritsMailingListe = (InscritsMailingListe) o;
    return Objects.equals(this.id, inscritsMailingListe.id) &&
        Objects.equals(this.civilite, inscritsMailingListe.civilite) &&
        Objects.equals(this.nom, inscritsMailingListe.nom) &&
        Objects.equals(this.prenom, inscritsMailingListe.prenom) &&
        Objects.equals(this.email, inscritsMailingListe.email) &&
        Objects.equals(this.lienPhotoProfil, inscritsMailingListe.lienPhotoProfil) &&
        Objects.equals(this.accordMail, inscritsMailingListe.accordMail) &&
        Objects.equals(this.publicContact, inscritsMailingListe.publicContact) &&
        Objects.equals(this.adhesionsSaisonCourante, inscritsMailingListe.adhesionsSaisonCourante) &&
        Objects.equals(this.statutParticipation, inscritsMailingListe.statutParticipation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, civilite, nom, prenom, email, lienPhotoProfil, accordMail, publicContact, adhesionsSaisonCourante, statutParticipation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InscritsMailingListe {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    civilite: ").append(toIndentedString(civilite)).append("\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    prenom: ").append(toIndentedString(prenom)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    lienPhotoProfil: ").append(toIndentedString(lienPhotoProfil)).append("\n");
    sb.append("    accordMail: ").append(toIndentedString(accordMail)).append("\n");
    sb.append("    publicContact: ").append(toIndentedString(publicContact)).append("\n");
    sb.append("    adhesionsSaisonCourante: ").append(toIndentedString(adhesionsSaisonCourante)).append("\n");
    sb.append("    statutParticipation: ").append(toIndentedString(statutParticipation)).append("\n");
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
