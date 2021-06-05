package fr.espaceadh.utilitaire.controller;

import fr.espaceadh.utilitaire.model.ListeSaison;
import fr.espaceadh.utilitaire.model.Saison;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.utilitaire.dto.SaisonDto;
import fr.espaceadh.utilitaire.service.SaisonService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-02T08:30:39.723Z[GMT]")
@RestController
public class SaisonApiController implements SaisonApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaisonApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    protected SaisonService saisonService;

    @org.springframework.beans.factory.annotation.Autowired
    public SaisonApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    /**
     * Récupérer la liste des saisons
     * @return 
     */
    public ResponseEntity<ListeSaison> getListeSaison() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
             Collection<SaisonDto> lstSaison = this.saisonService.getLstSaison();
             
             ListeSaison lstSaisonModel = new ListeSaison();
             for (SaisonDto dto : lstSaison) {
                 lstSaisonModel.add(this.translateToModel(dto));
             }
             return new ResponseEntity<>(lstSaisonModel, HttpStatus.OK);
        }

        return new ResponseEntity<ListeSaison>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Récupérer la saison courante
     * @return 
     */
    public ResponseEntity<Saison> getSaisonCourante() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            SaisonDto saisonDto = this.saisonService.getSaisonCourant();
            return new ResponseEntity<>(this.translateToModel(saisonDto), HttpStatus.OK);
        }

        return new ResponseEntity<Saison>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Mise à jour de la saison courante
     * @param idSaison
     * @return 
     */
    public ResponseEntity<Void> updateSaisonCourante(@Parameter(in = ParameterIn.PATH, description = "id de la saison", required=true, schema=@Schema()) @PathVariable("idSaison") Integer idSaison) {
        String accept = request.getHeader("Accept");
        this.saisonService.changerSaisonCourant(idSaison);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Transforme l'objet saisondto en saison model
     * @param dto
     * @return 
     */
    private Saison translateToModel(SaisonDto dto) {
        Saison saisonModel = new Saison();
        saisonModel.setId(dto.getId());
        saisonModel.setLibelle(dto.getLibelle());
        saisonModel.setSaisonActive(dto.isActive());
        
        return saisonModel;
    }

}
