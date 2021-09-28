package fr.espaceadh.utilitaire.controller;

import fr.espaceadh.lib.mail.dto.InputStreamCustom;
import fr.espaceadh.utilitaire.dto.EMailDto;
import fr.espaceadh.utilitaire.model.MailAEnvoyer;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.utilitaire.dto.GroupeDiffusionDto;
import fr.espaceadh.utilitaire.model.ListeDiffusion;
import fr.espaceadh.utilitaire.model.ListeListeDiffusion;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;

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


    public ResponseEntity<Void> addBinaryToMail(@Parameter(in = ParameterIn.PATH, description = "id du mail à envoyer", required=true, schema=@Schema()) @PathVariable("idMail") Long idMail,@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file,@Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema()) @RequestParam(value="fileName", required=false)  String fileName) {
        String accept = request.getHeader("Accept");
        try {

            this.makeDir(Long.toString(idMail));
            StringBuilder cheminFichier = new StringBuilder();
            cheminFichier.append("/tmp/");
            cheminFichier.append("/");
            cheminFichier.append(Long.toString(idMail));
            cheminFichier.append("/");
            cheminFichier.append(fileName);

            LOGGER.info("Enregistrement du fichier : {}", cheminFichier.toString());

            Files.copy(file.getInputStream(), Paths.get(cheminFichier.toString()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            LOGGER.error("Erreur copie fichier {}", ex);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    /**
     * création d'un répertoire dans /tmp/
     * @param directoryName
     */
    private void makeDir(String directoryName)
    {
        StringBuilder cheminDossier = new StringBuilder();
        cheminDossier.append("/tmp/");
        cheminDossier.append("/");
        cheminDossier.append(directoryName);
        File directory = new File(cheminDossier.toString());
        if (directory.exists() && directory.isFile()) {
            LOGGER.info("Le répertroire {} existe déjà " , directoryName);
        } else {
            directory.mkdir();
            LOGGER.info("Creation d'un répertoire {}" , cheminDossier.toString());
        }
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
     * Envoyer un email
     * @param idMail
     * @param body
     * @return
     */
    public ResponseEntity<Void> sendMail(@Parameter(in = ParameterIn.PATH, description = "id du mail à envoyer", required=true, schema=@Schema()) @PathVariable("idMail") Long idMail,@Parameter(in = ParameterIn.DEFAULT, description = "Objet listeDiffusion", schema=@Schema()) @Valid @RequestBody MailAEnvoyer body) {

        EMailDto dto = new EMailDto();
        dto.setMessageHtml(body.getEmail());
        dto.setSujet(body.getTitreEmail());
        dto.setLstFile(this.listFilesForFolder(Long.toString(idMail)));

        // Si envoi poune liste de diffusion
        if (body.getTypeMail() == MailAEnvoyer.TypeMailEnum.NUMBER_10) {
            LOGGER.debug("Envoyer un mail à la mailing liste {}", body.getIdListeDiffusion());

            boolean restult = listeDiffusionService.envoyerMailListeDiffusion(dto, body.getIdListeDiffusion());

            if (restult) return new ResponseEntity<Void>(HttpStatus.CREATED);
            else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Si envoie pour une liste d'adherent
        else if (body.getTypeMail() != MailAEnvoyer.TypeMailEnum.NUMBER_10){
            LOGGER.debug("Envoyer un mail aux adhérents de type {}", Integer.parseInt(body.getTypeMail().toString()));

            boolean restult = listeDiffusionService.envoyerMailListeAdherent(dto, Integer.parseInt(body.getTypeMail().toString()));

            if (restult) return new ResponseEntity<Void>(HttpStatus.CREATED);
            else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Recherche les fichiers contenu dans un répertoire
     * @param folder
     */
    private Collection<InputStreamCustom> listFilesForFolder(String directoryName) {

        Collection<InputStreamCustom> lstFiles = new ArrayList<>();


        final File folder = new File("/tmp/"+directoryName);
        for (final File fileEntry : folder.listFiles()) {
            StringBuilder cheminFichier = new StringBuilder();
            cheminFichier.append("/tmp/");
            cheminFichier.append("/");
            cheminFichier.append(directoryName);
            cheminFichier.append("/");
            cheminFichier.append(fileEntry.getName());
            try {
                InputStreamCustom iptsc = new InputStreamCustom();
                iptsc.setInputStream(Files.newInputStream(Paths.get(cheminFichier.toString())));
                iptsc.setFileName(fileEntry.getName());
                iptsc.setContentType(Files.probeContentType(Paths.get(cheminFichier.toString())));
                lstFiles.add(iptsc);

                LOGGER.info("Recupérer le fichier {} de type {} avec nom {}",cheminFichier.toString() , iptsc.getContentType() , iptsc.getFileName());
            } catch (IOException e) {
                LOGGER.error("IOException" + e.getMessage());
            }
        }

        return lstFiles;
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
