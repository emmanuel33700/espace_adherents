/*
 * Copyright (C) 2019 emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.espaceadh.authorization.controller;

import fr.espaceadh.authorization.model.ActivationAuthentification;
import fr.espaceadh.authorization.model.Authentification;
import fr.espaceadh.authorization.model.InfoAuthentification;
import fr.espaceadh.authorization.model.Login;
import fr.espaceadh.authorization.model.ModelApiResponse;
import fr.espaceadh.authorization.model.ReinitAuthentification;
import fr.espaceadh.authorization.model.Roles;
import fr.espaceadh.authorization.model.Validation;
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
import org.springframework.security.access.prepost.PreAuthorize;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-28T08:29:33.965Z[GMT]")
@Api(value = "authentification", description = "the authentification API")
public interface AuthentificationApi {


    @Operation(summary = "activation ou désactivation d'une authentification", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-autorization-admin",
"ress-autorization-read",
"ress-autorization-write",
"ress-autorization-del"        })    }, tags={ "Authentification" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "404", description = "login non trouvée", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification/{idadh}/activation",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    @PreAuthorize("isDansGroupe('ADMIN')  or #oauth2.hasScope('ress-autorization-admin') ")          
    ResponseEntity<Void> activationAuthentification(@Parameter(in = ParameterIn.PATH, description = "id de la personne à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.DEFAULT, description = "information d'activation ou desactivation d'un authentification", required=true, schema=@Schema()) @Valid @RequestBody ActivationAuthentification body);


    @Operation(summary = "Ajouter  compte d'accès d'une personne", description = "", tags={ "Authentification" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> addAuthentification(@Parameter(in = ParameterIn.DEFAULT, description = "ajout de l'objet authentification", required=true, schema=@Schema()) @Valid @RequestBody Authentification body);


    @Operation(summary = "suppression du compte accès d'une personne", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-autorization-admin",
"ress-autorization-read",
"ress-autorization-write",
"ress-autorization-del"        })    }, tags={ "Authentification" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "404", description = "login non trouvée", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification/{idadh}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    @PreAuthorize("isDansGroupe('ADMIN') or (#oauth2.hasScope('ress-autorization-del') and #login == authentication.principal.username)")         
    ResponseEntity<Void> deleteAuthentification(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh);

        @Operation(summary = "Récupérer les informations d'authentification (login et statut)", description = "vérifier si un login existe dans la BD", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-autorization-admin",
"ress-autorization-read",
"ress-autorization-write",
"ress-autorization-del"        })    }, tags={ "Authentification" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InfoAuthentification.class))),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "404", description = "login non trouvée", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification/{idadh}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-autorization-admin') or isDansGroupe('ADMIN') ")         
    ResponseEntity<InfoAuthentification> getAuthentification(@Parameter(in = ParameterIn.PATH, description = "id de la personne à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh);
    

    @Operation(summary = "Recuperer les roles d'une personne", description = "Recuperer les roles d'une personne", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-autorization-admin",
"ress-autorization-read",
"ress-autorization-write",
"ress-autorization-del"        })    }, tags={ "Roles" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie", content = @Content(schema = @Schema(implementation = Roles.class))),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "404", description = "login non trouvée", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification/{idadh}/roles",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-autorization-admin') or ( #oauth2.hasScope('ress-autorization-write') and  isDansGroupe('ADMIN')) ")          
    ResponseEntity<Roles> getRoles(@Parameter(in = ParameterIn.PATH, description = "id de la personne à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh);


    @Operation(summary = "demander la réinitialisation du mot de passe", description = "", tags={ "Authentification" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "404", description = "login non trouvée", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification/resetPassword",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> resetPassword(@Parameter(in = ParameterIn.DEFAULT, description = "demander la réinitialisation du mot de passe", schema=@Schema()) @Valid @RequestBody Login body);


    @Operation(summary = "Mise à jour  info d'authentification d'une personne", description = "Mise à jour  info d'authentification d'une personne", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-autorization-admin",
"ress-autorization-read",
"ress-autorization-write",
"ress-autorization-del"        })    }, tags={ "Authentification" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification/{idadh}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('ress-autorization-admin') or isDansGroupe('ADMIN')")        
    ResponseEntity<Void> updateAuthentification(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.DEFAULT, description = "mise à jour du login de la personne", required=true, schema=@Schema()) @Valid @RequestBody Authentification body);


    @Operation(summary = "Mise à jour des roles d'une personne", description = "Mise à jour des roles d'une personne", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-autorization-admin",
"ress-autorization-read",
"ress-autorization-write",
"ress-autorization-del"        })    }, tags={ "Roles" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "404", description = "login non trouvée", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification/{idadh}/roles",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('ress-autorization-admin') or ( #oauth2.hasScope('ress-autorization-write') and  isDansGroupe('ADMIN')) ")         
    ResponseEntity<Void> updateRoles(@Parameter(in = ParameterIn.PATH, description = "id de la personne à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.DEFAULT, description = "mise à jour de l'objet role", required=true, schema=@Schema()) @Valid @RequestBody Roles body);


    @Operation(summary = "validation d'enregistrement du login d'une personne", description = "", tags={ "Authentification" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "404", description = "login non trouvée", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification/{idadh}/validation",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> validationAuthentification(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.DEFAULT, description = "Clée de validation reçu par mail au moment de la création du compte", required=true, schema=@Schema()) @Valid @RequestBody Validation body);


    @Operation(summary = "demander la validation de la réinitialisation du mot de passe", description = "", tags={ "Authentification" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "404", description = "login non trouvée", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification/{idadh}/validerResetPassword",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> valideResetPassword(@Parameter(in = ParameterIn.PATH, description = "id de la personne à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh, @Parameter(in = ParameterIn.DEFAULT, description = "demander la validation de la réinitialisation du mot de passe", schema=@Schema()) @Valid @RequestBody ReinitAuthentification body);
    
    
        @Operation(summary = "Désactiver l'ensemble des authentifications (hors role administrateur et représentant de l'association qui restent activé)", description = "", security = {
        @SecurityRequirement(name = "oAuth", scopes = {
            "ress-autorization-admin",
"ress-autorization-read",
"ress-autorization-write",
"ress-autorization-del"        })    }, tags={ "Authentification" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Operation réussie"),
        
        @ApiResponse(responseCode = "403", description = "Droit insufisant", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "404", description = "login non trouvée", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))) })
    @RequestMapping(value = "/authentification/all/desactiver",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('ress-autorization-admin') or isDansGroupe('ADMIN') ")              
    ResponseEntity<Void> desactiverAllAuthentification();
}
