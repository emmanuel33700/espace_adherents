/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.23).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package fr.espaceadh.adherents.controller;

import fr.espaceadh.adherents.model.Adherent;
import fr.espaceadh.adherents.model.Adhesion;
import fr.espaceadh.adherents.model.LiensAdherent;
import fr.espaceadh.adherents.model.ListeAdherents;
import fr.espaceadh.adherents.model.ListeAdhesions;
import fr.espaceadh.adherents.model.ListeManifestations;
import fr.espaceadh.adherents.model.ListeCommunications;
import fr.espaceadh.adherents.model.Manifestation;
import fr.espaceadh.adherents.model.ModelApiResponse;
import fr.espaceadh.adherents.model.ParticipationManifestation;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-11-14T09:31:23.328Z[GMT]")
@Api(value = "adherent", description = "the adherent API")
public interface AdherentApi {

    @Operation(summary = "Ajouter un adhérent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "adherent" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read') and isDansGroupe('CONSEIL')")         
    ResponseEntity<Void> ajoutAdherent(@Parameter(in = ParameterIn.DEFAULT, description = "Objet adhérent", required=true, schema=@Schema()) @Valid @RequestBody Adherent body);


    @Operation(summary = "Ajouter une adhesions pour un adherent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "adhesion" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/adhesion",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    @PreAuthorize("#oauth2.hasScope('ress-adhesion-write') and isDansGroupe('CONSEIL')")     
    ResponseEntity<Void> ajoutAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.DEFAULT, description = "Objet adhesion", required=true, schema=@Schema()) @Valid @RequestBody Adhesion body);

    @Operation(summary = "Ajouter un lien entre 2 adherents", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin",
"ress-adherent-read",
"ress-adherent-write",
"ress-adherent-del",
"ress-adhesion-admin",
"ress-adhesion-read",
"ress-adhesion-write",
"ress-adhesion-del",
"ress-manifestation-admin",
"ress-manifestation-read",
"ress-manifestation-write",
"ress-manifestation-del",
"ress-communication-read"        })    }, tags={ "liens adherents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/lien/{idAdhLien}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    @PreAuthorize("#oauth2.hasScope('ress-adhesion-write') and isDansGroupe('CONSEIL')")             
    ResponseEntity<Void> ajoutLienAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent representant", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.PATH, description = "id de l'adherent representé", required=true, schema=@Schema()) @PathVariable("idAdhLien") Long idAdhLien);
    
    
    @Operation(summary = "Ajouter la participation à une manifestation pour un adherent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin",
"ress-adherent-read",
"ress-adherent-write",
"ress-adherent-del",
"ress-adhesion-admin",
"ress-adhesion-read",
"ress-adhesion-write",
"ress-adhesion-del",
"ress-manifestation-admin",
"ress-manifestation-read",
"ress-manifestation-write",
"ress-manifestation-del",
"ress-communication-read"        })    }, tags={ "Manifestation" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/manifestion/{idManifestation}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    @PreAuthorize(" (#oauth2.hasScope('ress-adherent-read') and isDansGroupe('CONSEIL')) or isProprietraireOuPeutAgirSurDonnee(#idadh) ")         
    ResponseEntity<Void> ajoutManifestationAdherent(@Parameter(in = ParameterIn.PATH, description = "id l'adherent à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.PATH, description = "id de la manifestation à modifier", required=true, schema=@Schema()) @PathVariable("idManifestation") Long idManifestation, @Parameter(in = ParameterIn.DEFAULT, description = "Besoin de l'objet manifestation le lier à un adherents", required=true, schema=@Schema()) @Valid @RequestBody ParticipationManifestation body);

    @Operation(summary = "Supression d'une adhesion pour cet adherent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "adhesion" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/adhesion/{idAdhesion}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.hasScope('ress-adhesion-del') and isDansGroupe('ADMIN')")  
    ResponseEntity<Void> deleteAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.PATH, description = "id de l'adhesion à supprimer", required=true, schema=@Schema()) @PathVariable("idAdhesion") Long idAdhesion);


        @Operation(summary = "Supprimer un lien adherent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin",
"ress-adherent-read",
"ress-adherent-write",
"ress-adherent-del",
"ress-adhesion-admin",
"ress-adhesion-read",
"ress-adhesion-write",
"ress-adhesion-del",
"ress-manifestation-admin",
"ress-manifestation-read",
"ress-manifestation-write",
"ress-manifestation-del",
"ress-communication-read"        })    }, tags={ "liens adherents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/lien/{idAdhLien}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.hasScope('ress-adhesion-del') and isDansGroupe('CONSEIL')")          
    ResponseEntity<Void> deleteLienAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent representant", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.PATH, description = "id de l'adherent representé", required=true, schema=@Schema()) @PathVariable("idAdhLien") Long idAdhLien);
    
    @Operation(summary = "Supression d'une manifestation pour un adherent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin",
"ress-adherent-read",
"ress-adherent-write",
"ress-adherent-del",
"ress-adhesion-admin",
"ress-adhesion-read",
"ress-adhesion-write",
"ress-adhesion-del",
"ress-manifestation-admin",
"ress-manifestation-read",
"ress-manifestation-write",
"ress-manifestation-del",
"ress-communication-read"        })    }, tags={ "Manifestation" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/manifestion/{idManifestation}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    @PreAuthorize(" (#oauth2.hasScope('ress-adherent-read') and isDansGroupe('CONSEIL')) or isProprietraireOuPeutAgirSurDonnee(#idadh) ")         
    ResponseEntity<Void> deleteManifestationAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.PATH, description = "id de la manifestation à supprimer", required=true, schema=@Schema()) @PathVariable("idManifestation") Long idManifestation);


    @Operation(summary = "Supression de l'adherent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "adherent" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-del') and isDansGroupe('ADMIN')")         
    ResponseEntity<Void> deleteUser(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh);


    @Operation(summary = "rechercher adherent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "adherent" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = Adherent.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read-client-credentials') or  (#oauth2.hasScope('ress-adherent-read') and (isDansGroupe('CONSEIL') or isProprietraireOuPeutAgirSurDonnee(#idadh) ))")          
    ResponseEntity<Adherent> getAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh);

    
        @Operation(summary = "rechercher adherent via son mail", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin",
"ress-adherent-read",
"ress-adherent-write",
"ress-adherent-del",
"ress-adhesion-admin",
"ress-adhesion-read",
"ress-adhesion-write",
"ress-adhesion-del",
"ress-manifestation-admin",
"ress-manifestation-read",
"ress-manifestation-write",
"ress-manifestation-del",
"ress-communication-read"        })    }, tags={ "adherent" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = Adherent.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read-client-credentials') or isDansGroupe('CONSEIL')")                  
    ResponseEntity<Adherent> getAdherentByMail(@NotNull @Parameter(in = ParameterIn.QUERY, description = "mail de l'adhérent" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "mailadherent", required = true) String mailadherent);

    @Operation(summary = "rechercher une adhésion pour un adherent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "adhesion" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = Adhesion.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/adhesion/{idAdhesion}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("(#oauth2.hasScope('ress-adhesion-read') and isDansGroupe('CONSEIL')) or isProprietraireOuPeutAgirSurDonnee(#idadh) ")                
    ResponseEntity<Adhesion> getAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.PATH, description = "id de l'adhesion à recuperer", required=true, schema=@Schema()) @PathVariable("idAdhesion") Long idAdhesion);

        @Operation(summary = "recuperer  les liens d'un adherent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin",
"ress-adherent-read",
"ress-adherent-write",
"ress-adherent-del",
"ress-adhesion-admin",
"ress-adhesion-read",
"ress-adhesion-write",
"ress-adhesion-del",
"ress-manifestation-admin",
"ress-manifestation-read",
"ress-manifestation-write",
"ress-manifestation-del",
"ress-communication-read"        })    }, tags={ "liens adherents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LiensAdherent.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/liens",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("(#oauth2.hasScope('ress-adhesion-read') and isDansGroupe('CONSEIL')) or isProprietraireDonnee(#idadh) ")          
    ResponseEntity<LiensAdherent> getLienAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh);

    @Operation(summary = "Récupérer l'ensemble des adhérents", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "listing adherent" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = ListeAdherents.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/liste",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read') and isDansGroupe('CONSEIL')")          
    ResponseEntity<ListeAdherents> getListeAdherents();


    @Operation(summary = "Récupérer l'ensemble des adhérents de la saison", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "listing adherent" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = ListeAdherents.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/listeSaison",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read') ") //TODO pour test => a compélter avec   and isDansGroupe('CONSEIL')        
    ResponseEntity<ListeAdherents> getListeAdherentsSaison();


    @Operation(summary = "rechercher la liste des adhésions pour un adherent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "adhesion" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = ListeAdhesions.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/adhesions",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("(#oauth2.hasScope('ress-adhesion-read') and isDansGroupe('CONSEIL')) or isProprietraireOuPeutAgirSurDonnee(#idadh) ")  
    ResponseEntity<ListeAdhesions> getListeAdhesionsAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh);


    @Operation(summary = "rechercher la liste de communications que le client a recu", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "Communication" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = ListeCommunications.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/communications",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-communication-read') and isDansGroupe('CONSEIL')")     
    ResponseEntity<ListeCommunications> getListeCommunicationAdhrent(@Parameter(in = ParameterIn.PATH, description = "id d'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh);


   @Operation(summary = "rechercher la liste des manifestations affectée au un adhérents", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin",
"ress-adherent-read",
"ress-adherent-write",
"ress-adherent-del",
"ress-adhesion-admin",
"ress-adhesion-read",
"ress-adhesion-write",
"ress-adhesion-del",
"ress-manifestation-admin",
"ress-manifestation-read",
"ress-manifestation-write",
"ress-manifestation-del",
"ress-communication-read"        })    }, tags={ "Manifestation" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = ListeManifestations.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/manifestions",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize(" (#oauth2.hasScope('ress-adherent-read') and isDansGroupe('CONSEIL')) or isProprietraireOuPeutAgirSurDonnee(#idadh) ")           
    ResponseEntity<ListeManifestations> getListeManifestationsAdherent(@Parameter(in = ParameterIn.PATH, description = "id d'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.QUERY, description = "date de début" ,schema=@Schema()) @Valid @RequestParam(value = "datedebut", required = false) String datedebut, @Parameter(in = ParameterIn.QUERY, description = "date de fin" ,schema=@Schema()) @Valid @RequestParam(value = "datefin", required = false) String datefin, @Parameter(in = ParameterIn.QUERY, description = "indique si il faut récupérer uniquement les manifestations avec une demande de participation" ,schema=@Schema()) @Valid @RequestParam(value = "retourParticipationAdh", required = false) Boolean retourParticipationAdh);
    
    
    
        @Operation(summary = "rechercher le d'étail d'une  manifestations affectée au un adhérent", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin",
"ress-adherent-read",
"ress-adherent-write",
"ress-adherent-del",
"ress-adhesion-admin",
"ress-adhesion-read",
"ress-adhesion-write",
"ress-adhesion-del",
"ress-manifestation-admin",
"ress-manifestation-read",
"ress-manifestation-write",
"ress-manifestation-del",
"ress-communication-read"        })    }, tags={ "Manifestation" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = Manifestation.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/manifestion/{idManifestation}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize(" (#oauth2.hasScope('ress-adherent-read') and isDansGroupe('CONSEIL')) or isProprietraireOuPeutAgirSurDonnee(#idadh) ")          
    ResponseEntity<Manifestation> getManifestationsAdherent(@Parameter(in = ParameterIn.PATH, description = "id d'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.PATH, description = "id de la manifestation à recuperer", required=true, schema=@Schema()) @PathVariable("idManifestation") Long idManifestation);
    
    
    @Operation(summary = "Mise à jour d'une adhesion pour cet adherent", description = "Mise à jour d'une adhesion pour cet adherent", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "adhesion" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/adhesion/{idAdhesion}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('ress-adhesion-write') and isDansGroupe('CONSEIL')")  
    ResponseEntity<Void> updateAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.PATH, description = "id de l'adhesion de modifier", required=true, schema=@Schema()) @PathVariable("idAdhesion") Long idAdhesion, @Parameter(in = ParameterIn.DEFAULT, description = "mise à jour d'une adhesion", required=true, schema=@Schema()) @Valid @RequestBody Adhesion body);


    @Operation(summary = "Mise à jour d'une manifestation pour un adherent", description = "Mise à jour d'une manifestation pour un adherent", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin",
"ress-adherent-read",
"ress-adherent-write",
"ress-adherent-del",
"ress-adhesion-admin",
"ress-adhesion-read",
"ress-adhesion-write",
"ress-adhesion-del",
"ress-manifestation-admin",
"ress-manifestation-read",
"ress-manifestation-write",
"ress-manifestation-del",
"ress-communication-read"        })    }, tags={ "Manifestation" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/manifestion/{idManifestation}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    @PreAuthorize("(#oauth2.hasScope('ress-adhesion-read') and isDansGroupe('CONSEIL')) or isProprietraireOuPeutAgirSurDonnee(#idadh) ")            
    ResponseEntity<Void> updateManifestationAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.PATH, description = "id de la manifestation à modifier", required=true, schema=@Schema()) @PathVariable("idManifestation") Long idManifestation, @Parameter(in = ParameterIn.DEFAULT, description = "mise à jour d'une adhesion", required=true, schema=@Schema()) @Valid @RequestBody ParticipationManifestation body);


    @Operation(summary = "Mise à jour de l'adherent", description = "Mise à jour de l'adherent", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "adherent" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read') and (isDansGroupe('CONSEIL') or isProprietraireOuPeutAgirSurDonnee(#idadh) )")         
    ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.DEFAULT, description = "Objet adherent", required=true, schema=@Schema()) @Valid @RequestBody Adherent body);

    
        @Operation(summary = "Mise à jour de la photo de l'adhérent", description = "Mise à jour de la photo de l'adhérent", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin",
"ress-adherent-read",
"ress-adherent-write",
"ress-adherent-del",
"ress-adhesion-admin",
"ress-adhesion-read",
"ress-adhesion-write",
"ress-adhesion-del",
"ress-manifestation-admin",
"ress-manifestation-read",
"ress-manifestation-write",
"ress-manifestation-del",
"ress-communication-read"        })    }, tags={ "adherent" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "username non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/adherent/{idadh}/photo",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" }, 
        method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read') and (isDansGroupe('CONSEIL') or isProprietraireOuPeutAgirSurDonnee(#idadh) )")  
    ResponseEntity<Void> updateUserPhoto(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="fileName", required=true)  String fileName, @Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file);
    
}

