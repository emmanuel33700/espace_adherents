/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.10).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package fr.espaceadh.autorisation.web.controller;

import fr.espaceadh.autorisation.model.Authentification;
import fr.espaceadh.autorisation.model.Roles;
import fr.espaceadh.autorisation.model.Validation;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.security.access.prepost.PreAuthorize;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-15T10:40:38.150Z[GMT]")
@Api(value = "authentification", description = "the authentification API")
public interface AuthentificationApi {

    @ApiOperation(value = "Ajouter  compte d'accès d'une personne", nickname = "addAuthentification", notes = "")
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/authentification",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addAuthentification(@ApiParam(value = "ajout de l'objet authentification" ,required=true )  @Valid @RequestBody Authentification body);


    @ApiOperation(value = "suppression du compte accès d'une personne", nickname = "deleteAuthentification", notes = "", tags={ "Autorisations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 403, message = "Droit insufisant pour suppriler une authentification"),
        @ApiResponse(code = 404, message = "Personne non trouvée"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/authentification/{login}",
        method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN') or (#oauth2.hasScope('ress-autorization-del') and #login == authentication.principal.username)")                
    ResponseEntity<Void> deleteAuthentification(@Size(min=3,max=50) @ApiParam(value = "login de la personne",required=true) @PathVariable("login") String login);


    @ApiOperation(value = "vérifier si un login existe dans la BD", nickname = "getAuthentification", notes = "vérifier si un login existe dans la BD", tags={ "Autorisations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 403, message = "Droit insufisant pour accéder a cette ressource"),
        @ApiResponse(code = 404, message = "Login non trouvée"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/authentification/{login}",
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-autorization-admin') or hasRole('ADMIN') ") 
    ResponseEntity<Void> getAuthentification(@Size(min=3,max=50) @ApiParam(value = "login de la personne",required=true) @PathVariable("login") String login);


    @ApiOperation(value = "Mise à jour du compte accès d'une personne", nickname = "updateAuthentification", notes = "Mise à jour de l'authentification d'une personne", tags={ "Autorisations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 403, message = "Droit insufisant pour modifier une authentification"),
        @ApiResponse(code = 404, message = "Personne non trouvée"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/authentification",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('ress-autorization-write') or hasRole('ADMIN')") 
    ResponseEntity<Void> updateAuthentification(@ApiParam(value = "mise à jour de l'objet authentification" ,required=true )  @Valid @RequestBody Authentification body);


    @ApiOperation(value = "Mise à jour des roles d'une personne", nickname = "updateRoles", notes = "Mise à jour des roles d'une personne", tags={ "Roles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 403, message = "Droit insufisant pour modifier une authentification"),
        @ApiResponse(code = 404, message = "Personne non trouvée"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/authentification/{login}/roles",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')") 
    ResponseEntity<Void> updateRoles(@ApiParam(value = "mise à jour de l'objet role" ,required=true )  @Valid @RequestBody Roles body,@Size(min=3,max=50) @ApiParam(value = "login de la personne",required=true) @PathVariable("login") String login);


    @ApiOperation(value = "validation d'enregistrement du login d'une personne", nickname = "validationAuthentification", notes = "", tags={ "Autorisations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/authentification/{login}/validation",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> validationAuthentification(@ApiParam(value = "ajout de l'objet authentification" ,required=true )  @Valid @RequestBody Validation body,@Size(min=3,max=50) @ApiParam(value = "login de la personne",required=true) @PathVariable("login") String login);

}
