package fr.espaceadh.utilitaire.controller;

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.service.AdherentService;
import fr.espaceadh.utilitaire.dto.ActualiteDto;
import fr.espaceadh.utilitaire.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.utilitaire.service.ActualiteService;
import fr.espaceadh.utilitaire.service.AgendaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-20T19:52:41.277412232Z[GMT]")
@RestController
public class ActualiteApiController implements ActualiteApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActualiteApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    protected ActualiteService actualiteService;


    @Autowired
    protected AdherentService adherentService;

    @org.springframework.beans.factory.annotation.Autowired
    public ActualiteApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addActualite(@Parameter(in = ParameterIn.DEFAULT, description = "Objet Actualite", required=true, schema=@Schema()) @Valid @RequestBody Actualiteecriture body
) {
        String accept = request.getHeader("Accept");

        AdherentDto adh = this.getIdAdherentConnecte();

        ActualiteDto dto = new ActualiteDto();
        dto.setLibelleActualite(body.getLibelleActualite());
        dto.setIdAdherent(adh.getId());
        dto.setDateCreation(new Date());
        dto.setIdAuthority(1);

        boolean result = this.actualiteService.ajouterNouvelleActualite(dto);

        if(result) return  new ResponseEntity<Void>(HttpStatus.CREATED);

        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Void> delActualite(@Parameter(in = ParameterIn.PATH, description = "id dde l'actualité à supprimer", required=true, schema=@Schema()) @PathVariable("idActualite") Long idActualite
) {
        String accept = request.getHeader("Accept");

         boolean result = this.actualiteService.supprimerActualite(idActualite.intValue());

        if(result) return  new ResponseEntity<Void>(HttpStatus.CREATED);

        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ListeActualite> getListeActualite() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Collection<ActualiteDto> lstDto = this.actualiteService.recupererActualiteActive();

            ListeActualite lstModel = new ListeActualite();
            for (ActualiteDto dto : lstDto){
                lstModel.add(this.convertDto(dto));
            }

            return new ResponseEntity<ListeActualite>(lstModel, HttpStatus.OK);

        } else {
            LOGGER.error(" requete http ne contient pas application/json");
        }

        return new ResponseEntity<ListeActualite>(HttpStatus.NOT_IMPLEMENTED);
    }

    private Actualitelecture convertDto(ActualiteDto dto) {
        Actualitelecture actualitelecture = new Actualitelecture();
        actualitelecture.setId(dto.getId());
        actualitelecture.setIdAuthority(Actualitelecture.IdAuthorityEnum.NUMBER_1);//TODO a revoir le type d'authority
        actualitelecture.setLibelleActualite(dto.getLibelleActualite());
        actualitelecture.setDateCreation(this.dateToString(dto.getDateCreation()));
        actualitelecture.setIdAdherent(dto.getIdAdherent());

        return actualitelecture;
    }


    /**
     * Vérifier le type de role de l'utilisateur
     * @param role
     * @return
     */
    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains(role)) {
                return true;
            }

        }
        return false;
    }


    private AdherentDto getIdAdherentConnecte() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username != null) {
            AdherentDto dto = this.adherentService.recupererAdherent(username);
            return dto;
        }
        LOGGER.error("Aucun username recupérer dans le jeton d'authentification");
        return null;
    }


    /**
     * convertir date en string format ISO
     *
     * @param date
     * @return
     */
    private String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        LOGGER.debug("date {} ", date.toString());
        //sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        return sdf.format(date);
    }

}
