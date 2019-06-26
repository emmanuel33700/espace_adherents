package fr.espaceadh.adherent.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import fr.espaceadh.adherents.dto.Adherent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-20T15:15:37.676Z[GMT]")
@Controller
public class AdherentApiController implements AdherentApi {

    private static final Logger log = LoggerFactory.getLogger(AdherentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

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
       
        String username = "";
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        String accept = request.getHeader("Accept");
        Adherent adh = new Adherent();
        adh.setId((long)idAdh);
        adh.setNom("chenais");
        adh.setPrenom("emmanuel");
        adh.setEmail(username);
        
        return new ResponseEntity<Adherent>(adh,HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody Adherent body,@ApiParam(value = "name that need to be updated",required=true) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
