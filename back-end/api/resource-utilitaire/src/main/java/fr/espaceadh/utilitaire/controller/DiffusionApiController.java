package fr.espaceadh.utilitaire.controller;

import fr.espaceadh.utilitaire.dto.MailListeDiffusionDto;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.utilitaire.dto.GroupeDiffusionDto;
import fr.espaceadh.utilitaire.model.ListeDiffusion;
import fr.espaceadh.utilitaire.model.ListeListeDiffusion;
import fr.espaceadh.utilitaire.service.DocumentService;
import fr.espaceadh.utilitaire.service.ListeDiffusionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-15T13:08:44.732Z[GMT]")
@RestController
public class DiffusionApiController implements DiffusionApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiffusionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    protected ListeDiffusionService listeDiffusionService;

    @Autowired
    private Environment env;

    @org.springframework.beans.factory.annotation.Autowired
    public DiffusionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    /**
     * Ajouter une liste de diffusion
     * @param idListe
     * @param body
     * @return 
     */
    public ResponseEntity<Void> addListe(@Parameter(in = ParameterIn.PATH, description = "id de la liste de diffusion", required = true, schema = @Schema()) @PathVariable("idListe") Long idListe, @Parameter(in = ParameterIn.DEFAULT, description = "Objet listeDiffusion", required = true, schema = @Schema()) @Valid @RequestBody ListeDiffusion body) {
        String accept = request.getHeader("Accept");
        
        boolean result = this.listeDiffusionService.addListeDiffusion(this.transformeToDto(body));
        if (result) return new ResponseEntity<Void>(HttpStatus.CREATED);
        else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Supprimer une liste de diffusion
     * @param idListe
     * @return 
     */
    public ResponseEntity<Void> delListe(@Parameter(in = ParameterIn.PATH, description = "id du fichier", required = true, schema = @Schema()) @PathVariable("idListe") Long idListe) {
        String accept = request.getHeader("Accept");
        boolean result = this.listeDiffusionService.deleteListeDiffusion(idListe);
        if (result) return new ResponseEntity<Void>(HttpStatus.OK);
        else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Récupérer l'ensemble des listes de diffusion
     * @return 
     */
    public ResponseEntity<ListeListeDiffusion> getListes() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Collection<GroupeDiffusionDto> lstGroupe = this.listeDiffusionService.getListeListeDiffusion();
            ListeListeDiffusion lst = new ListeListeDiffusion();
            if (lstGroupe != null && !lstGroupe.isEmpty()){
                
                for (GroupeDiffusionDto dto : lstGroupe){
                    lst.add(this.transformeToModel(dto));
                }
            } 
            return new ResponseEntity<ListeListeDiffusion>(lst, HttpStatus.OK);
        }

        return new ResponseEntity<ListeListeDiffusion>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * MàJ du libellé d'une liste de diffusion
     * @param idListe
     * @param body
     * @return 
     */
    public ResponseEntity<Void> majListe(@Parameter(in = ParameterIn.PATH, description = "id du fichier", required = true, schema = @Schema()) @PathVariable("idListe") Long idListe, @Parameter(in = ParameterIn.DEFAULT, description = "Objet listeDiffusion", required = true, schema = @Schema()) @Valid @RequestBody ListeDiffusion body) {
        String accept = request.getHeader("Accept");
        GroupeDiffusionDto dto = this.transformeToDto(body);
        dto.setIdGroupeDiffusion(idListe);
        boolean result = this.listeDiffusionService.updateListeDiffusion(dto);
        if (result) return new ResponseEntity<Void>(HttpStatus.OK);
        else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Envoyer un mail
     * @param typeMail
     * @param idListeDiffusion
     * @param titreEmail
     * @param email
     * @param file1
     * @param file2
     * @param file3
     * @param file4
     * @param file5
     * @param file6
     * @return
     */
    public ResponseEntity<Void> sendMail(@Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema(allowableValues={ "1", "2", "4", "10" }
    )) @RequestParam(value="typeMail", required=false)  Integer typeMail, @Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema()) @RequestParam(value="idListeDiffusion", required=false)  Long idListeDiffusion, @Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema()) @RequestParam(value="titreEmail", required=false)  String titreEmail, @Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema()) @RequestParam(value="email", required=false)  String email, @Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file1, @Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file2, @Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file3, @Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file4, @Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file5, @Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file6) {
        String accept = request.getHeader("Accept");

        MailListeDiffusionDto dto = new MailListeDiffusionDto();
        dto.setMessageHtml(email);
        dto.setSujet(titreEmail);

        Collection<MultipartFile> lstFile = new ArrayList<>();
        if (file1 != null) {
            lstFile.add(file1);
        }
        if (file2 != null) {
            lstFile.add(file2);
        }
        if (file3 != null) {
            lstFile.add(file3);
        }
        if (file4 != null) {
            lstFile.add(file4);
        }
        if (file5 != null) {
            lstFile.add(file5);
        }
        if (file6 != null) {
            lstFile.add(file6);
        }
        dto.setLstFile(lstFile);

        if (typeMail == 10) {
            LOGGER.debug("Envoyer un mail à la mailing liste {}", idListeDiffusion);
            dto.setIdListeDiffusion(idListeDiffusion);
            boolean restult = listeDiffusionService.envoyerMailListeDiffusion(dto);
            if (restult) return new ResponseEntity<Void>(HttpStatus.CREATED);
            else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }



        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

        /**
     * Transforme DTO to model
     * @param model
     * @return 
     */
    private ListeDiffusion transformeToModel(GroupeDiffusionDto dto){
        ListeDiffusion model = new ListeDiffusion();
        
        model.setId(dto.getIdGroupeDiffusion());
        model.setLibelle(dto.getLibelleGroupeDiffusion());
        
        return model;
    }
    
    /**
     * Transforme model to DTO
     * @param model
     * @return 
     */
    private GroupeDiffusionDto transformeToDto(ListeDiffusion model){
        GroupeDiffusionDto dto = new GroupeDiffusionDto();
        
        dto.setIdGroupeDiffusion(model.getId());
        dto.setLibelleGroupeDiffusion(model.getLibelle());
        
        return dto;
    }

}
