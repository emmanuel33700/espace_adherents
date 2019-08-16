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
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.dto.CiviliteEnum;
import fr.espaceadh.adherents.service.AdherentService;
import io.swagger.annotations.*;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-16T13:27:11.143Z[GMT]")
@Controller
public class AdherentApiController implements AdherentApi {

    private static final Logger log = LoggerFactory.getLogger(AdherentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    protected AdherentService adherentService;

    @org.springframework.beans.factory.annotation.Autowired
    public AdherentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> ajoutAdherent(@ApiParam(value = "Besoin de l'objet adhérent pour ajouter un adhérent" ,required=true )  @Valid @RequestBody Adherent body) {
        
        AdherentDto dto = this.translateModel(body);
        
        //TODO ajouter l'id de l'utilisateur qui a créé l'adhérent
        
        int result = adherentService.creerAdherent(dto);
        
        if(result == 0) return  new ResponseEntity<Void>(HttpStatus.CREATED);
        else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
    

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "email de la personne à modifier",required=true) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Adherent> getAdherent(@ApiParam(value = "email de la personne à modifier",required=true) @PathVariable("username") String username) {
        return new ResponseEntity<Adherent>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "mise à jour de la personne" ,required=true )  @Valid @RequestBody Adherent body,@ApiParam(value = "email de la personne à modifier",required=true) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    /**
     * transformer le model adherent en dto adherent
     */
    private AdherentDto translateModel(Adherent model){
        AdherentDto adh = new AdherentDto();
        
        adh.setId(model.getId());
        adh.setCivilite(CiviliteEnum.MONSIEUR); //TODO a replacer l'enum
        adh.setNom(model.getNom());
        adh.setPrenom(model.getPrenom());
        adh.setAdresse1(model.getAdresse1());
        adh.setAdresse2(model.getAdresse2());
        adh.setVille(model.getVille());
        adh.setTelMaison(model.getTelMaison());
        adh.setTelPortable(model.getTelPortable());
        adh.setTelTravail(model.getTelTravail());
        adh.setEmail(model.getEmail());
        adh.setProfession(model.getProfession());
        long epochMilli = model.getDateNaissance().toInstant().toEpochMilli();
        Date dateNaissance = new Date(epochMilli);
        adh.setDateNaissance(dateNaissance);
        adh.setAccordMail(model.isAccordMail());
        adh.setPublicContact(model.isPublicContact());
        adh.setCommentaire(model.getCommentaire());
        adh.setDateEnregistrement(new Date());
        adh.setDateMiseAJour(new Date());

        
        return adh;
    }

}
