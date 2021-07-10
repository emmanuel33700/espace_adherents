/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.24).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package fr.espaceadh.utilitaire.controller;

import fr.espaceadh.utilitaire.model.Evenement;
import fr.espaceadh.utilitaire.model.ListeEvenements;
import fr.espaceadh.utilitaire.model.ListeParticipantsEvenement;
import fr.espaceadh.utilitaire.model.ListeSyntheseEvenements;
import fr.espaceadh.utilitaire.model.ModelApiResponse;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-02T08:30:39.723Z[GMT]")
public interface AgendaApi {

    @Operation(summary = "Ajouter un évenement", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "Agenda" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/agenda/evenement",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    @PreAuthorize("isDansGroupe('RES_ATELIER')")         
    ResponseEntity<Void> addEvenement(@Parameter(in = ParameterIn.DEFAULT, description = "Objet adhérent", required=true, schema=@Schema()) @Valid @RequestBody Evenement body);


    @Operation(summary = "Supprimer un evenement", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "Agenda" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "evenement non trouvé"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/agenda/evenement/{idevenement}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    @PreAuthorize("isDansGroupe('CONSEIL')")          
    ResponseEntity<Void> deleteEvenement(@Parameter(in = ParameterIn.PATH, description = "id de l'evenement", required=true, schema=@Schema()) @PathVariable("idevenement") Long idevenement);


    @Operation(summary = "rechercher un evenement", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "Agenda" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = Evenement.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "evenement non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/agenda/evenement/{idevenement}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("isDansGroupe('ADHERENT')")          
    ResponseEntity<Evenement> getEvenement(@Parameter(in = ParameterIn.PATH, description = "id de l'evenement", required=true, schema=@Schema()) @PathVariable("idevenement") Long idevenement);


    @Operation(summary = "rechercher la liste d'évenement", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "Agenda" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = ListeEvenements.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "evenement non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/agenda/evenements",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("isDansGroupe('ADHERENT')")            
    ResponseEntity<ListeEvenements> getListeEvenements();

    
        @Operation(summary = "rechercher la synthese des participations à un évènement", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin"        })    }, tags={ "Agenda" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListeParticipantsEvenement.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "evenement non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/agenda/evenement/{idevenement}/synthese",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ListeParticipantsEvenement> getSyntheseEvenement(@Parameter(in = ParameterIn.PATH, description = "id de l'evenement", required=true, schema=@Schema()) @PathVariable("idevenement") Long idevenement);


    @Operation(summary = "rechercher la synthèse des participations aux évènement", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin"        })    }, tags={ "Agenda" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListeSyntheseEvenements.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "evenement non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/agenda/synthese",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ListeSyntheseEvenements> getSyntheseEvenements(@Parameter(in = ParameterIn.QUERY, description = "date de début" ,schema=@Schema()) @Valid @RequestParam(value = "datedebut", required = false) String datedebut, @Parameter(in = ParameterIn.QUERY, description = "date de fin" ,schema=@Schema()) @Valid @RequestParam(value = "datefin", required = false) String datefin);

    @Operation(summary = "Mise à jour d'une evenement", description = "Mise à jour d'un evenement", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "Agenda" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "evenement non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/agenda/evenement/{idevenement}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    @PreAuthorize("isDansGroupe('CONSEIL')")          
    ResponseEntity<Void> updateEvenement(@Parameter(in = ParameterIn.PATH, description = "id de l'evenement", required=true, schema=@Schema()) @PathVariable("idevenement") Long idevenement, @Parameter(in = ParameterIn.DEFAULT, description = "Objet adherent", required=true, schema=@Schema()) @Valid @RequestBody Evenement body);

}

