package fr.espaceadh.utilitaire.controller;

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.service.AdherentService;
import fr.espaceadh.utilitaire.model.ArborescenceDocuments;
import fr.espaceadh.utilitaire.model.Document;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.utilitaire.dto.DocumentDto;
import fr.espaceadh.utilitaire.model.ArborescenceDocumentsInit;
import fr.espaceadh.utilitaire.model.ListeDocuments;
import fr.espaceadh.utilitaire.service.DocumentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-11T08:45:53.348Z[GMT]")
@RestController
public class DocumentsApiController implements DocumentsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    protected DocumentService documentService;

    @Autowired
    protected AdherentService adherentService;

    @Autowired
    private Environment env;

    @org.springframework.beans.factory.annotation.Autowired
    public DocumentsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    /**
     * Créer un dossier
     *
     * @param body
     * @return
     */
    public ResponseEntity<Void> addDossier(@Parameter(in = ParameterIn.DEFAULT, description = "Objet dossier", required = true, schema = @Schema()) @Valid @RequestBody Document body) {
        String accept = request.getHeader("Accept");

        DocumentDto documentDto = this.transformeDto(body);
        documentDto.setDossier(true);
        documentDto.setFichier(false);

        boolean result = documentService.creerDocument(documentDto);

        if (result) {
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }

        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Créer un fichier
     *
     * @param body
     * @return
     */
    public ResponseEntity<Void> addFichier(@Parameter(in = ParameterIn.DEFAULT, description = "Objet fichier", required = true, schema = @Schema()) @Valid @RequestBody Document body) {
        try {
            String accept = request.getHeader("Accept");
            DocumentDto documentDto = this.transformeDto(body);
            documentDto.setDossier(false);
            documentDto.setFichier(true);

            boolean result = documentService.creerDocument(documentDto);

            if (result) {
                StringBuilder cheminFichierSource = new StringBuilder();
                cheminFichierSource.append(env.getProperty("folder.path.document"));
                cheminFichierSource.append("/");
                cheminFichierSource.append(body.getId());
                cheminFichierSource.append(".tmp");

                StringBuilder cheminFichierDestination = new StringBuilder();
                cheminFichierDestination.append(env.getProperty("folder.path.document"));
                cheminFichierDestination.append("/");
                cheminFichierDestination.append(body.getNomFichier());

                Files.move(Paths.get(cheminFichierSource.toString()), Paths.get(cheminFichierDestination.toString()), StandardCopyOption.REPLACE_EXISTING);

                Set<PosixFilePermission> perms = new HashSet<>();
                perms.add(PosixFilePermission.OWNER_READ);
                perms.add(PosixFilePermission.OWNER_WRITE);

                perms.add(PosixFilePermission.OTHERS_READ);

                perms.add(PosixFilePermission.GROUP_READ);

                Files.setPosixFilePermissions(Paths.get(cheminFichierDestination.toString()), perms);

                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } else {
                LOGGER.error("Erreur d'enregistrement du document");
            }

        } catch (IOException ex) {
            LOGGER.error("IOException" + ex);
        }

        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Copier un fichier recu sur le File système
     *
     * @param idFichier
     * @param orderId
     * @param userId
     * @param file
     * @return
     */
    public ResponseEntity<Void> addFichierBinaire(@Parameter(in = ParameterIn.PATH, description = "id du fichier", required = true, schema = @Schema()) @PathVariable("idFichier") Long idFichier, @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @RequestParam(value = "orderId", required = false) Integer orderId, @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @RequestParam(value = "userId", required = false) Integer userId, @Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file) {
        String accept = request.getHeader("Accept");

        LOGGER.info("userid {}, fileName {}", idFichier, file);

        try {

            StringBuilder cheminFichier = new StringBuilder();
            cheminFichier.append(env.getProperty("folder.path.document"));
            cheminFichier.append("/");
            cheminFichier.append(idFichier);
            cheminFichier.append(".tmp");

            LOGGER.info("Enregistrement du fichier : {}", cheminFichier.toString());

            Files.copy(file.getInputStream(), Paths.get(cheminFichier.toString()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            LOGGER.error("Erreur copie fichier {}", ex);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * Supprimer un dossier
     * @param idDossier
     * @return 
     */
    public ResponseEntity<Void> delDossier(@Parameter(in = ParameterIn.PATH, description = "id du dossier", required=true, schema=@Schema()) @PathVariable("idDossier") Long idDossier) {
        String accept = request.getHeader("Accept");
        boolean result = this.documentService.supprimerDocument(idDossier);
         if(result) {
              return  new ResponseEntity<Void>(HttpStatus.OK);
         }
         
         return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Supression d'un fichier
     * @param idFichier
     * @return 
     */
    public ResponseEntity<Void> delFichier(@Parameter(in = ParameterIn.PATH, description = "id du fichier", required=true, schema=@Schema()) @PathVariable("idFichier") Long idFichier) {
        String accept = request.getHeader("Accept");
        final DocumentDto documentASupp = this.documentService.getDocument(idFichier);
        boolean result = this.documentService.supprimerDocument(idFichier);
        if(result) {
            
            StringBuilder cheminFichier = new StringBuilder();
            cheminFichier.append(env.getProperty("folder.path.document"));
            cheminFichier.append("/");
            cheminFichier.append(documentASupp.getNonFichier());
            try {

                
                Files.delete(Paths.get(cheminFichier.toString()));
                
                
                return  new ResponseEntity<Void>(HttpStatus.OK);
            } catch (IOException ex) {
                LOGGER.error("IOException : Error lors la supression du fichier {} ", cheminFichier.toString());
                return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Recupérer l'arboresence
     *
     * @return
     */
    public ResponseEntity<ArborescenceDocumentsInit> getArboresence() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            int typeAutority = 1;
            if (this.hasRole("ADHERENT")) {
                typeAutority = 2;
            } else if (this.hasRole("RES_ATELIER")) {
                typeAutority = 3;
            } else if (this.hasRole("CONSEIL")) {
                typeAutority = 4;
            } else if (this.hasRole("ADMIN")) {
                typeAutority = 5;
            }

            Collection<DocumentDto> arborescence = this.documentService.getArboresenceDocuments(typeAutority);

            List<ArborescenceDocuments> lstArborescenceDocuments = new ArrayList<>();

            this.transformeToModel(arborescence, lstArborescenceDocuments);

            ArborescenceDocumentsInit arborescenceDocumentsModel = new ArborescenceDocumentsInit();
            arborescenceDocumentsModel.addAll(lstArborescenceDocuments);

            return new ResponseEntity<>(arborescenceDocumentsModel, HttpStatus.OK);

        } else {
            LOGGER.error(" requete http ne contient pas application/json");
        }

        return new ResponseEntity<ArborescenceDocumentsInit>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Récupérer des documents
     * @param minDateCreation
     * @param maxDateCreation
     * @return 
     */
    public ResponseEntity<ListeDocuments> getDocuments(@Parameter(in = ParameterIn.QUERY, description = "borne min de date de création du document" ,schema=@Schema()) @Valid @RequestParam(value = "minDateCreation", required = false) String minDateCreation,@Parameter(in = ParameterIn.QUERY, description = "borne min de date de création du document" ,schema=@Schema()) @Valid @RequestParam(value = "maxDateCreation", required = false) String maxDateCreation) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            
            int typeAutority = 1;
            if (this.hasRole("ADHERENT")) {
                typeAutority = 2;
            } else if (this.hasRole("CONSEIL")) {
                typeAutority = 3;
            } else if (this.hasRole("BUREAU")) {
                typeAutority = 4;
            } else if (this.hasRole("ADMIN")) {
                typeAutority = 5;
            }
            Date dMinDateCreation = null;
            Date dMaxDateCreation = null;
            if (minDateCreation != null && maxDateCreation != null){
                dMinDateCreation = this.toDateSansHeure(minDateCreation);
                dMaxDateCreation = this.toDateSansHeure(maxDateCreation);
                
            }
             Collection<DocumentDto> lstDto = this.documentService.getDocuments(dMinDateCreation, dMaxDateCreation, typeAutority); 
             
             ListeDocuments lstModel = new ListeDocuments();
             for (DocumentDto dto : lstDto) {
                 Document model = this.transformeToModel(dto);
                 lstModel.add(model);
             }
             
             return new ResponseEntity<>(lstModel, HttpStatus.OK);

        }

        return new ResponseEntity<ListeDocuments>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    
        /**
     * Convertir une date iso dans les heures
     * @param iso8601string
     * @return 
     */
    public Date toDateSansHeure(final String iso8601string) {
        if (iso8601string == null) {
            return null;
        }
        
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

        Date result2 = null;
        try {
            result2 = df2.parse(iso8601string);
        } catch (ParseException ex) {
            LOGGER.error("Erreur sur le formatage  de date. Date en entré {}. {}", iso8601string , ex.getMessage());
        }


        return result2;
    }
    
    public ResponseEntity<Document> getDossier(@Parameter(in = ParameterIn.PATH, description = "id du dossier", required = true, schema = @Schema()) @PathVariable("idDossier") Long idDossier) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Document>(objectMapper.readValue("{\n  \"libelleLong\" : \"document sur les étoiles\",\n  \"dateCreation\" : \"2020-01-18T21:00:00\",\n  \"id\" : 1225668,\n  \"idAuthority\" : 1,\n  \"nomFichier\" : \"055555884.pdf\",\n  \"idDossierRattachement\" : 1225668,\n  \"libelleCourt\" : \"Etoiles\"\n}", Document.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                LOGGER.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Document>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Document>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Récupérer détail d'un fichier
     * @param idFichier
     * @return 
     */
    public ResponseEntity<Document> getFichier(@Parameter(in = ParameterIn.PATH, description = "id du fichier", required = true, schema = @Schema()) @PathVariable("idFichier") Long idFichier) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Document>(objectMapper.readValue("{\n  \"libelleLong\" : \"document sur les étoiles\",\n  \"dateCreation\" : \"2020-01-18T21:00:00\",\n  \"id\" : 1225668,\n  \"idAuthority\" : 1,\n  \"nomFichier\" : \"055555884.pdf\",\n  \"idDossierRattachement\" : 1225668,\n  \"libelleCourt\" : \"Etoiles\"\n}", Document.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                LOGGER.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Document>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Document>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * MàJ d'un dossier
     * @param idDossier
     * @param body
     * @return 
     */
    public ResponseEntity<Void> majDossier(@Parameter(in = ParameterIn.PATH, description = "id du dossier", required=true, schema=@Schema()) @PathVariable("idDossier") Long idDossier,@Parameter(in = ParameterIn.DEFAULT, description = "Objet dossier", required=true, schema=@Schema()) @Valid @RequestBody Document body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * MàJ d'un Fichier
     * @param idFichier
     * @param body
     * @return 
     */
    public ResponseEntity<Void> majFichier(@Parameter(in = ParameterIn.PATH, description = "id du fichier", required=true, schema=@Schema()) @PathVariable("idFichier") Long idFichier,@Parameter(in = ParameterIn.DEFAULT, description = "Objet fichier", required=true, schema=@Schema()) @Valid @RequestBody Document body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Transforme le model document en dto
     *
     * @param body
     * @return
     */
    private DocumentDto transformeDto(Document body) {
        DocumentDto dto = new DocumentDto();

        dto.setIdDocument(body.getId());
        if (body.getIdDossierRattachement() != null) {
            dto.setIdDocumentParent(body.getIdDossierRattachement());
        }
        dto.setIdAuthority(2); // TODO type d'authority a revoir pour la création de document
        dto.setLablelCourt(body.getLibelleCourt());
        dto.setLabelLong(body.getLibelleLong());
        dto.setNonFichier(body.getNomFichier());
        dto.setIdAuteur(this.getIdAdherentConnecte());

        return dto;
    }

    /**
     * R2cupérer l'id de l'adhérent connecté
     * @return
     */
    private long getIdAdherentConnecte() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username != null) {
           AdherentDto dto = this.adherentService.recupererAdherent(username);
            return dto.getId();
        }
        LOGGER.error("Aucun username recupérer dans le jeton d'authentification");
        return 0;
    }

    /**
     * Vérifier le type de role de l'utilisateur
     *
     * @param role
     * @return
     */
    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains(role)) {
                return true;
            }

        }
        return false;
    }

    /**
     * Transforme l'arboresence DTO en arborescence model
     * @param arborescence
     * @return
     */
    private void transformeToModel(Collection<DocumentDto> arborescenceDto, List<ArborescenceDocuments> arborescenceDocumentsModel) {

        if (arborescenceDto != null && !arborescenceDto.isEmpty()) {

            for (DocumentDto dto : arborescenceDto) {
                ArborescenceDocuments arboresenceDocument = new ArborescenceDocuments();
                Document documentModel = this.transformeToModel(dto);
                arboresenceDocument.setParent(documentModel);

                if (dto.getDocumentsFils() != null && !dto.getDocumentsFils().isEmpty()) {
                    List<ArborescenceDocuments> lstEnfantsArborescenceDocuments = new ArrayList<>();

                    this.transformeToModel(dto.getDocumentsFils(), lstEnfantsArborescenceDocuments);

                    arboresenceDocument.setEnfants(lstEnfantsArborescenceDocuments);
                }

                arborescenceDocumentsModel.add(arboresenceDocument);

            }
            LOGGER.debug("On remonte dans l'arborescence ");
        }

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

    /**
     * Transforme DTO document en model document
     * @param dto
     * @return 
     */
    private Document transformeToModel(DocumentDto dto) {
        Document documentModel = new Document();
        documentModel.setDateCreation(dateToString(dto.getDateDeCreation()));
        documentModel.setId(dto.getIdDocument());
        documentModel.setIdAuthority(Document.IdAuthorityEnum.NUMBER_2); // TODO TYpe d'authority a revoir
        documentModel.setLibelleCourt(dto.getLablelCourt());
        documentModel.setLibelleLong(dto.getLabelLong());
        documentModel.setNomFichier(dto.getNonFichier());
        documentModel.setIdDossierRattachement(dto.getIdDocumentParent());
        documentModel.setIdAuteur(dto.getIdAuteur());
        documentModel.setPrenomNomAuteur(dto.getPrenomAuteur() + ' ' + dto.getNomAuteur());

        return documentModel;
    }

}
