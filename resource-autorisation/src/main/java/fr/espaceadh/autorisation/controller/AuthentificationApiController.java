package fr.espaceadh.autorisation.controller;

import fr.espaceadh.autorisation.model.Authentification;
import fr.espaceadh.autorisation.model.Roles;
import fr.espaceadh.autorisation.model.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-05T20:02:22.596Z[GMT]")
@Controller
public class AuthentificationApiController implements AuthentificationApi {

    private static final Logger log = LoggerFactory.getLogger(AuthentificationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AuthentificationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addAuthentification(@ApiParam(value = "ajout de l'objet authentification" ,required=true )  @Valid @RequestBody Authentification body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteAuthentification(@Size(min=3,max=50) @ApiParam(value = "login de la personne encodé en base64",required=true) @PathVariable("login") String login) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateAuthentification(@ApiParam(value = "mise à jour de l'objet authentification" ,required=true )  @Valid @RequestBody Authentification body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateRoles(@ApiParam(value = "mise à jour de l'objet role" ,required=true )  @Valid @RequestBody Roles body,@Size(min=3,max=50) @ApiParam(value = "login de la personne encodé en base64",required=true) @PathVariable("login") String login) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> validationAuthentification(@ApiParam(value = "ajout de l'objet authentification" ,required=true )  @Valid @RequestBody Validation body,@Size(min=3,max=50) @ApiParam(value = "login de la personne encodé en base64",required=true) @PathVariable("login") String login) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}