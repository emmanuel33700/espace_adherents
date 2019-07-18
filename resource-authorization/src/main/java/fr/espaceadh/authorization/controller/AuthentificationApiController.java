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
import fr.espaceadh.authorization.model.Roles;
import fr.espaceadh.authorization.model.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.authorization.dto.UserDto;
import fr.espaceadh.authorization.service.AuthentificationService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-17T15:25:36.817Z[GMT]")
@Controller
public class AuthentificationApiController implements AuthentificationApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthentificationApiController.class);

    @Autowired
    protected AuthentificationService authentificationService;
    
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AuthentificationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<Void> addAuthentification(@ApiParam(value = "ajout de l'objet authentification" ,required=true )  @Valid @RequestBody Authentification body) {
        String accept = request.getHeader("Accept");
        
        
        UserDto dto = this.convertDto(body);

        authentificationService.creerUser(dto);
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteAuthentification(@Size(min=3,max=50) @ApiParam(value = "login de la personne",required=true) @PathVariable("login") String login) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Authentification> getAuthentification(@Size(min=3,max=50) @ApiParam(value = "login de la personne",required=true) @PathVariable("login") String login) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Authentification>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> resetPassword(@Size(min=3,max=50) @ApiParam(value = "login de la personne",required=true) @PathVariable("login") String login) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateAuthentification(@ApiParam(value = "mise à jour de l'objet authentification" ,required=true )  @Valid @RequestBody Authentification body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateRoles(@ApiParam(value = "mise à jour de l'objet role" ,required=true )  @Valid @RequestBody Roles body,@Size(min=3,max=50) @ApiParam(value = "login de la personne",required=true) @PathVariable("login") String login) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> validationAuthentification(@ApiParam(value = "Clée de validation reçu par mail au moment de la création du compte" ,required=true )  @Valid @RequestBody Validation body,@Size(min=3,max=50) @ApiParam(value = "login de la personne",required=true) @PathVariable("login") String login) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    
    /**
     * Transforme Authentification model eb UsersDTO
     * @param Authentification
     * @return UserDTO
     */
    private UserDto convertDto(Authentification body) {
        UserDto dto = new UserDto();
        dto.setUsername(body.getLogin());
        dto.setPasswordEncode(passwordEncoder().encode(body.getPassword()));
        
        return dto;
    }
    
    
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
