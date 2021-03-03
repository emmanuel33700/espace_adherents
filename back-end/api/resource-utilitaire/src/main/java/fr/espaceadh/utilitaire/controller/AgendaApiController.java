package fr.espaceadh.utilitaire.controller;

import fr.espaceadh.utilitaire.model.Evenement;
import fr.espaceadh.utilitaire.model.ListeEvenements;
import fr.espaceadh.utilitaire.model.ModelApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.utilitaire.dto.EvenementDto;
import fr.espaceadh.utilitaire.service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-02T08:30:39.723Z[GMT]")
@RestController
public class AgendaApiController implements AgendaApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
        @Autowired
    protected AgendaService agendaService;
    

    @org.springframework.beans.factory.annotation.Autowired
    public AgendaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addEvenement(@Parameter(in = ParameterIn.DEFAULT, description = "Objet adhérent", required=true, schema=@Schema()) @Valid @RequestBody Evenement body) {
        String accept = request.getHeader("Accept");
        EvenementDto dto = new EvenementDto();
        
        boolean result = agendaService.creerEvenement(this.convertDto(body));
        
        if(result) return  new ResponseEntity<Void>(HttpStatus.CREATED);
        
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Void> deleteEvenement(@Parameter(in = ParameterIn.PATH, description = "id de l'evenement", required=true, schema=@Schema()) @PathVariable("idevenement") Long idevenement) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Evenement> getEvenement(@Parameter(in = ParameterIn.PATH, description = "id de l'evenement", required=true, schema=@Schema()) @PathVariable("idevenement") Long idevenement) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Evenement>(objectMapper.readValue("{\n  \"datedebut\" : \"2020-01-18T21:00:00\",\n  \"description\" : \"Conférence sur le soleil\",\n  \"datefin\" : \"2020-01-18T21:00:00\",\n  \"id\" : 1,\n  \"detail\" : \"Conférence sur le soleil présenté par Monsieur Dupont\",\n  \"type\" : 1\n}", Evenement.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                LOGGER.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Evenement>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Evenement>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Récupérer la list d'évènement en fonction des droits d'une personne
     * @return 
     */
    public ResponseEntity<ListeEvenements> getListeEvenements() {
        if (this.hasRole("ADMIN"))
            LOGGER.info("L'utilisateur à le role ADMIN");
                        
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                

                
                return new ResponseEntity<ListeEvenements>(objectMapper.readValue("[ {\n  \"datedebut\" : \"2020-01-18T21:00:00\",\n  \"description\" : \"Conférence sur le soleil\",\n  \"datefin\" : \"2020-01-18T21:00:00\",\n  \"id\" : 1,\n  \"detail\" : \"Conférence sur le soleil présenté par Monsieur Dupont\",\n  \"type\" : 1\n}, {\n  \"datedebut\" : \"2020-01-18T21:00:00\",\n  \"description\" : \"Conférence sur le soleil\",\n  \"datefin\" : \"2020-01-18T21:00:00\",\n  \"id\" : 1,\n  \"detail\" : \"Conférence sur le soleil présenté par Monsieur Dupont\",\n  \"type\" : 1\n} ]", ListeEvenements.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                LOGGER.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListeEvenements>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListeEvenements>(HttpStatus.NOT_IMPLEMENTED);
    }

    
    

    /**
     * Vérifier le type de role de l'utilisateur
     * @param role
     * @return 
     */
    private boolean hasRole(String role) {
        LOGGER.info("--> start hasRole");
      Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
      SecurityContextHolder.getContext().getAuthentication().getAuthorities();
      boolean hasRole = false;
      for (GrantedAuthority authority : authorities) {
         hasRole = authority.getAuthority().equals(role);
         LOGGER.info("Role : {} ",authority.getAuthority());
         if (hasRole) {
              break;
         }
      }
      return hasRole;
    }  


    
    public ResponseEntity<Void> updateEvenement(@Parameter(in = ParameterIn.PATH, description = "id de l'evenement", required=true, schema=@Schema()) @PathVariable("idevenement") Long idevenement,@Parameter(in = ParameterIn.DEFAULT, description = "Objet adherent", required=true, schema=@Schema()) @Valid @RequestBody Evenement body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    private EvenementDto convertDto(Evenement evenementModel) {
        EvenementDto dto = new EvenementDto();
        dto.setIdEvenement(evenementModel.getId());
        dto.setDescriptionCourte(evenementModel.getDescription());
        dto.setDescriptionLongue(evenementModel.getDetail());
        dto.setLieux(null); //TODO a revoir avec rajout du lieux dans le swagger utilitaire
        dto.setDateDebut(this.toDate(evenementModel.getDatedebut()));
        dto.setDateFin(this.toDate(evenementModel.getDatefin()));
        dto.setIdAuthority(1);
        
        return dto;
    }
    
    
            /** Transform ISO 8601 string to Calendar.
     * @param iso8601string
     * @return  */
    public  Date toDate(final String iso8601string) {

        if (iso8601string == null) return null;
        return Date.from( Instant.parse( iso8601string));
    }

}
