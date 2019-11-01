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
import fr.espaceadh.authorization.model.ReinitAuthentification;
import fr.espaceadh.authorization.model.Roles;
import fr.espaceadh.authorization.model.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.authorization.dto.UserDto;
import fr.espaceadh.authorization.model.ModelApiResponse;
import fr.espaceadh.authorization.service.AuthentificationService;
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
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-28T08:29:33.965Z[GMT]")
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

    /**
     * Ajout d'un utilisateur
     * @param body
     * @return 
     */
    public ResponseEntity<Void> addAuthentification(@ApiParam(value = "ajout de l'objet authentification" ,required=true )  @Valid @RequestBody Authentification body) {
        String accept = request.getHeader("Accept");
        
        
        UserDto dto = this.convertDto(body);

        authentificationService.creerUser(dto);
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteAuthentification(@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Authentification> getAuthentification(@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Authentification>(objectMapper.readValue("{\n  \"password\" : \"password\",\n  \"idAdh\" : 0,\n  \"login\" : \"login\"\n}", Authentification.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                LOGGER.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Authentification>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Authentification>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> resetPassword(@ApiParam(value = "demander la réinitialisation du mot de passe"  )  @Valid @RequestBody Login body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    
    public ResponseEntity<Void> updateAuthentification(@ApiParam(value = "mise à jour de l'objet authentification" ,required=true )  @Valid @RequestBody Authentification body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateRoles(@ApiParam(value = "mise à jour de l'objet role" ,required=true )  @Valid @RequestBody Roles body,@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Validation de la création d'un compte utilisateur
     * @param body
     * @param idadh
     * @return 
     */
    public ResponseEntity<Void> validationAuthentification(@ApiParam(value = "Clée de validation reçu par mail au moment de la création du compte" ,required=true )  @Valid @RequestBody Validation body,@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh) {

        boolean result = authentificationService.validationCreationUser(idadh.intValue(), body.getCleeValidation());
        
        if (result) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else {
            ModelApiResponse apiResponse =  new ModelApiResponse();
            apiResponse.setMessage("Validation non effectué, id de l'utilisateur  ou clee de validation incorrecte");
            return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED );
        }
            
    }

    public ResponseEntity<Void> valideResetPassword(@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh,@ApiParam(value = "demander la validation de la réinitialisation du mot de passe"  )  @Valid @RequestBody ReinitAuthentification body) {
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
        dto.setIdAdherent(body.getIdAdh().intValue()); 
        dto.setUsername(body.getLogin());
        dto.setPasswordEncode(passwordEncoder().encode(body.getPassword()));
        
        return dto;
    }
    
        public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
