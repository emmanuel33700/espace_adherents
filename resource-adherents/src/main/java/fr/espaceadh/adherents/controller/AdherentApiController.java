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

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.adherents.dao.AdherentsDAO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import fr.espaceadh.adherents.model.Adherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-20T15:15:37.676Z[GMT]")
@Controller
public class AdherentApiController implements AdherentApi {

    private static final Logger logger = LoggerFactory.getLogger(AdherentApiController.class);  
    
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private AdherentsDAO adherentsDAO;

    @org.springframework.beans.factory.annotation.Autowired
    public AdherentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> ajoutAdherent(@ApiParam(value = "Besoin de l'objet adhérent pour ajouter un adhérent" ,required=true )  @Valid @RequestBody Adherent body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "Id de l'adhérent à récupérer",required=true) @PathVariable("idAdh") Integer idAdh) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Adherent> getAdherent(@ApiParam(value = "Id de l'adhérent à récupérer",required=true) @PathVariable("idAdh") Integer idAdh) {
       
        logger.info(" --> getAdherent");
        
        String username = "";
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        String accept = request.getHeader("Accept");
        
        Adherent adh = adherentsDAO.getAdherentByID((long)idAdh);
        
        return new ResponseEntity<Adherent>(adh,HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody Adherent body,@ApiParam(value = "name that need to be updated",required=true) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
