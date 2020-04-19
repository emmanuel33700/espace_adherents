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
package fr.espaceadh.adherents.controller;

import fr.espaceadh.adherents.model.Adherent;
import fr.espaceadh.adherents.model.ListeAdherents;
import fr.espaceadh.adherents.model.ModelApiResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-16T13:27:11.143Z[GMT]")
@Api(value = "adherent", description = "the adherent API")
public interface AdherentApi {

    @ApiOperation(value = "Ajouter un adhérent", nickname = "ajoutAdherent", notes = "",  tags={ "adherent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Operation réussie"),
        @ApiResponse(code = 401, message = "utilisateur non authentifié"),
        @ApiResponse(code = 403, message = "Droit insufisant"),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class),
        @ApiResponse(code = 500, message = "Erreur serveur", response = ModelApiResponse.class) })
    @RequestMapping(value = "/adherent",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read') and isDansGroupe('BUREAU')")  
    ResponseEntity<Void> ajoutAdherent(@ApiParam(value = "Besoin de l'objet adhérent pour ajouter un adhérent" ,required=true )  @Valid @RequestBody Adherent body);


    @ApiOperation(value = "Supression de l'adherent par id", nickname = "deleteUser", notes = "",  tags={ "adherent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie"),
        @ApiResponse(code = 401, message = "utilisateur non authentifié"),
        @ApiResponse(code = 403, message = "Droit insufisant"),
        @ApiResponse(code = 404, message = "username non trouvée"),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class),
        @ApiResponse(code = 500, message = "Erreur serveur", response = ModelApiResponse.class) })
    @RequestMapping(value = "/adherent/{idadh}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-del') and isDansGroupe('BUREAU')") 
    ResponseEntity<Void> deleteUser(@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh);

    @ApiOperation(value = "rechercher adherent par id", nickname = "getAdherent", notes = "", response = Adherent.class, tags={ "adherent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie", response = Adherent.class),
        @ApiResponse(code = 401, message = "utilisateur non authentifié"),
        @ApiResponse(code = 403, message = "Droit insufisant"),
        @ApiResponse(code = 404, message = "username non trouvée"),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class),
        @ApiResponse(code = 500, message = "Erreur serveur", response = ModelApiResponse.class) })
    @RequestMapping(value = "/adherent/{idadh}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
   // @PreAuthorize("hasAuthority('ADMIN') or (#oauth2.hasScope('ress-adherent-read') and isMember(#idadh))")   
           @PreAuthorize("#oauth2.hasScope('ress-adherent-read-client-credentials') or  (#oauth2.hasScope('ress-adherent-read') and (isDansGroupe('BUREAU') or isProprietraireDonnee(#idadh) ))")   
   ResponseEntity<Adherent> getAdherent(@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh);


    @ApiOperation(value = "Mise à jour de l'adherent", nickname = "updateUser", notes = "Mise à jour de l'adherent", tags={ "adherent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie"),
        @ApiResponse(code = 401, message = "utilisateur non authentifié"),
        @ApiResponse(code = 403, message = "Droit insufisant"),
        @ApiResponse(code = 404, message = "username non trouvée"),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class),
        @ApiResponse(code = 500, message = "Erreur serveur", response = ModelApiResponse.class) })
    @RequestMapping(value = "/adherent/{idadh}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read') and (isDansGroupe('BUREAU') or isProprietraireDonnee(#idadh) )") 
    ResponseEntity<Void> updateUser(@ApiParam(value = "mise à jour de la personne" ,required=true )  @Valid @RequestBody Adherent body,@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh);


    @ApiOperation(value = "Récupérer l'ensemble des adhérents", nickname = "getListeAdherents", notes = "", response = ListeAdherents.class, tags={ "listing adherent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie", response = ListeAdherents.class),
        @ApiResponse(code = 401, message = "utilisateur non authentifié"),
        @ApiResponse(code = 403, message = "Droit insufisant"),
        @ApiResponse(code = 404, message = "username non trouvée"),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class),
        @ApiResponse(code = 500, message = "Erreur serveur", response = ModelApiResponse.class) })
    @RequestMapping(value = "/adherent/liste",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read') and isDansGroupe('BUREAU')")  
    ResponseEntity<ListeAdherents> getListeAdherents();


    @ApiOperation(value = "Récupérer l'ensemble des adhérents de la saison", nickname = "getListeAdherentsSaison", notes = "", response = ListeAdherents.class, tags={ "listing adherent", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation réussie", response = ListeAdherents.class),
        @ApiResponse(code = 401, message = "utilisateur non authentifié"),
        @ApiResponse(code = 403, message = "Droit insufisant"),
        @ApiResponse(code = 404, message = "username non trouvée"),
        @ApiResponse(code = 405, message = "Invalid input", response = ModelApiResponse.class),
        @ApiResponse(code = 500, message = "Erreur serveur", response = ModelApiResponse.class) })
    @RequestMapping(value = "/adherent/listeSaison",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('ress-adherent-read')")  
    ResponseEntity<ListeAdherents> getListeAdherentsSaison();

}
