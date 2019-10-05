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

import fr.espaceadh.authorization.model.Authentification;
import fr.espaceadh.authorization.model.Login;
import fr.espaceadh.authorization.model.ModelApiResponse;
import fr.espaceadh.authorization.model.ReinitAuthentification;
import fr.espaceadh.authorization.model.Roles;
import fr.espaceadh.authorization.model.Validation;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.security.access.prepost.PreAuthorize;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-28T08:29:33.965Z[GMT]")
@Api(value = "authentification", description = "the authentification API")
public interface AuthentificationApi {

    @ApiOperation(value = "Ajouter  compte d'accès d'une personne", nickname = "addAuthentification", notes = "", tags={ "Authentification", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Operation réussie"),
        @ApiResponse(code = 403, message = "Droit insufisant", response = ModelApiResponse.class),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class) })
    @RequestMapping(value = "/authentification",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addAuthentification(@ApiParam(value = "ajout de l'objet authentification" ,required=true )  @Valid @RequestBody Authentification body);


    @ApiOperation(value = "suppression du compte accès d'une personne", nickname = "deleteAuthentification", notes = "", tags={ "Authentification", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie"),
        @ApiResponse(code = 403, message = "Droit insufisant", response = ModelApiResponse.class),
        @ApiResponse(code = 404, message = "login non trouvée", response = ModelApiResponse.class),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class) })
    @RequestMapping(value = "/authentification/{idadh}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN') or (#oauth2.hasScope('ress-autorization-del') and #login == authentication.principal.username)")                
    ResponseEntity<Void> deleteAuthentification(@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh);


    @ApiOperation(value = "Récupérer les informations d'authentification", nickname = "getAuthentification", notes = "vérifier si un login existe dans la BD", response = Authentification.class, tags={ "Authentification", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie", response = Authentification.class),
        @ApiResponse(code = 403, message = "Droit insufisant", response = ModelApiResponse.class),
        @ApiResponse(code = 404, message = "login non trouvée", response = ModelApiResponse.class),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class) })
    @RequestMapping(value = "/authentification/{idadh}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-autorization-admin') or hasRole('ADMIN') ") 
    ResponseEntity<Authentification> getAuthentification(@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh);


    @ApiOperation(value = "demander la réinitialisation du mot de passe", nickname = "resetPassword", notes = "", tags={ "Authentification", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie"),
        @ApiResponse(code = 403, message = "Droit insufisant", response = ModelApiResponse.class),
        @ApiResponse(code = 404, message = "login non trouvée", response = ModelApiResponse.class),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class) })
    @RequestMapping(value = "/authentification/resetPassword",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> resetPassword(@ApiParam(value = "demander la réinitialisation du mot de passe"  )  @Valid @RequestBody Login body);


    @ApiOperation(value = "Mise à jour du compte accès d'une personne", nickname = "updateAuthentification", notes = "Mise à jour de l'authentification d'une personne", tags={ "Authentification", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie"),
        @ApiResponse(code = 403, message = "Droit insufisant", response = ModelApiResponse.class),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class) })
    @RequestMapping(value = "/authentification",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateAuthentification(@ApiParam(value = "mise à jour de l'objet authentification" ,required=true )  @Valid @RequestBody Authentification body);


    @ApiOperation(value = "Mise à jour des roles d'une personne", nickname = "updateRoles", notes = "Mise à jour des roles d'une personne",  tags={ "Roles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie"),
        @ApiResponse(code = 403, message = "Droit insufisant", response = ModelApiResponse.class),
        @ApiResponse(code = 404, message = "login non trouvée", response = ModelApiResponse.class),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class) })
    @RequestMapping(value = "/authentification/{login}/roles",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateRoles(@ApiParam(value = "mise à jour de l'objet role" ,required=true )  @Valid @RequestBody Roles body,@Size(min=3,max=50) @ApiParam(value = "login de la personne",required=true) @PathVariable("login") String login);


    @ApiOperation(value = "validation d'enregistrement du login d'une personne", nickname = "validationAuthentification", notes = "", tags={ "Authentification", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Operation réussie"),
        @ApiResponse(code = 403, message = "Droit insufisant", response = ModelApiResponse.class),
        @ApiResponse(code = 404, message = "login non trouvée", response = ModelApiResponse.class),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class) })
    @RequestMapping(value = "/authentification/{idadh}/validation",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> validationAuthentification(@ApiParam(value = "Clée de validation reçu par mail au moment de la création du compte" ,required=true )  @Valid @RequestBody Validation body,@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh);


    @ApiOperation(value = "demander la validation de la réinitialisation du mot de passe", nickname = "valideResetPassword", notes = "", tags={ "Authentification", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie"),
        @ApiResponse(code = 403, message = "Droit insufisant", response = ModelApiResponse.class),
        @ApiResponse(code = 404, message = "login non trouvée", response = ModelApiResponse.class),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class) })
    @RequestMapping(value = "/authentification/{idadh}/validerResetPassword",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> valideResetPassword(@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh,@ApiParam(value = "demander la validation de la réinitialisation du mot de passe"  )  @Valid @RequestBody ReinitAuthentification body);

}
