/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.24).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package fr.espaceadh.utilitaire.controller;

import fr.espaceadh.utilitaire.model.ListeSaison;
import fr.espaceadh.utilitaire.model.ModelApiResponse;
import fr.espaceadh.utilitaire.model.Saison;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-02T08:30:39.723Z[GMT]")
public interface SaisonApi {

    @Operation(summary = "récupérer la liste des saisons", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "Saison" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = ListeSaison.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "saison non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/saison/liste",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("isDansGroupe('CONSEIL')")           
    ResponseEntity<ListeSaison> getListeSaison();


    @Operation(summary = "récupérer la saison courante", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            ""        })    }, tags={ "Saison" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = Saison.class))),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "saison non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/saison/active",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("isDansGroupe('ADHERENT')")           
    ResponseEntity<Saison> getSaisonCourante();


    @Operation(summary = "Mise à jour de la saison courante", description = "Mise à jour de la saison courante", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-adherent-admin"        })    }, tags={ "Saison" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "401", description = "utilisateur non authentifié"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant"),
        
        @ApiResponse(responseCode = "404", description = "saison non trouvée"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/saison/{idSaison}/active",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    @PreAuthorize("isDansGroupe('ADMIN')")          
    ResponseEntity<Void> updateSaisonCourante(@Parameter(in = ParameterIn.PATH, description = "id de la saison", required=true, schema=@Schema()) @PathVariable("idSaison") Integer idSaison);
}

