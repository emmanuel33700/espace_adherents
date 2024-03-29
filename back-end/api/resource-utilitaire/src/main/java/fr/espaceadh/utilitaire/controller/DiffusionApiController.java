package fr.espaceadh.utilitaire.controller;

import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.dto.CiviliteEnum;
import fr.espaceadh.adherents.service.AdherentService;
import fr.espaceadh.lib.mail.dto.InputStreamCustom;
import fr.espaceadh.utilitaire.dto.AdherentMailingListeDto;
import fr.espaceadh.utilitaire.dto.EMailDto;
import fr.espaceadh.utilitaire.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.utilitaire.dto.GroupeDiffusionDto;
import fr.espaceadh.utilitaire.service.ListeDiffusionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import java.util.Date;

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

    @Autowired
    protected AdherentService adherentService;

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
     * Supression de l'ensemble des pieces jointes d'un mail en cours d'écriture
     * @param idMail
     * @return
     */
    public ResponseEntity<Void> delBinarysToMail(@Parameter(in = ParameterIn.PATH, description = "id du mail à envoyer", required=true, schema=@Schema()) @PathVariable("idMail") Long idMail) {
        String accept = request.getHeader("Accept");

        this.delFolder(Long.toString(idMail));
        return new ResponseEntity<Void>(HttpStatus.OK);
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
     * Recupérer les adhérents insrit à une mailing liste
     * @param idListe
     * @return
     */
    public ResponseEntity<ListInscritsMailingListe> getAdherentsInscritListe(@Parameter(in = ParameterIn.PATH, description = "id du fichier", required=true, schema=@Schema()) @PathVariable("idListe") Long idListe,@Parameter(in = ParameterIn.QUERY, description = "type de filtre" ,schema=@Schema(allowableValues={ "ALL", "ONLYADH", "ONLYINSCRIT" }
    )) @Valid @RequestParam(value = "filter", required = false) String filter) {
        String accept = request.getHeader("Accept");
        if (this.hasRole("ADMIN") && filter == null ) filter = "ALL";
        if (this.hasRole("CONSEIL") && filter == null ) filter = "ONLYADH";


        if (accept != null && accept.contains("application/json")) {

            LOGGER.info("Type de fitre appliqué {} " , filter);
            Collection<AdherentMailingListeDto> lstADh = this.listeDiffusionService.getListeAdherentsListDiffusion(idListe);
            ListInscritsMailingListe listInscritsMailingListe = new ListInscritsMailingListe();
            listInscritsMailingListe.setId(idListe);
           for (AdherentMailingListeDto dto : lstADh)  {

               InscritsMailingListe model = null;

               if (this.hasRole("ADMIN") ) {
                   if (filter.equalsIgnoreCase("ALL")) {
                       listInscritsMailingListe.addLstAdherentsItem(this.convertDto(dto));
                   }
                   // Restitution uniquementpersonnes inscrit sur la liste
                   else if (filter.equalsIgnoreCase("ONLYINSCRIT") && dto.isInscriptionMailingList()) {
                       listInscritsMailingListe.addLstAdherentsItem(this.convertDto(dto));
                   }
                   // Restitution uniquement des adhérents de la saison ++ personnes inscrit sur la liste
                   else if (filter.equalsIgnoreCase("ONLYADH") && (dto.isAdhesionSaisonCourante() || dto.isInscriptionMailingList())) {
                       listInscritsMailingListe.addLstAdherentsItem(this.convertDto(dto));
                   }

               }


               else if (this.hasRole("CONSEIL")) {
                   // Restitution uniquementpersonnes inscrit sur la liste
                   if (filter.equalsIgnoreCase("ONLYINSCRIT") && dto.isInscriptionMailingList()) {
                       listInscritsMailingListe.addLstAdherentsItem(this.convertDto(dto));
                   }
                   // Restitution uniquement des adhérents de la saison ++ personnes inscrit sur la liste
                   else if (filter.equalsIgnoreCase("ONLYADH") && (dto.isAdhesionSaisonCourante() || dto.isInscriptionMailingList())) {
                       listInscritsMailingListe.addLstAdherentsItem(this.convertDto(dto));
                   }

               }

               // Restitution uniquement des inscrits
               else  if(this.hasRole("RES_ATELIER") && dto.isInscriptionMailingList()) {
                   listInscritsMailingListe.addLstAdherentsItem(this.convertDto(dto));
               }

           }
            return new ResponseEntity<ListInscritsMailingListe>(listInscritsMailingListe, HttpStatus.OK);

        }

        return new ResponseEntity<ListInscritsMailingListe>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Transforme AdherentMailingListeDto to InscritsMailingListe;
     * @param dto
     * @return
     */
    private InscritsMailingListe convertDto(AdherentMailingListeDto dto) {

        InscritsMailingListe inscritsMailingListe = new InscritsMailingListe();
        inscritsMailingListe.setId(dto.getId());
        inscritsMailingListe.setStatutParticipation(dto.isInscriptionMailingList());

        if (dto.getCivilite() == dto.getCivilite().MADAME){
            inscritsMailingListe.setCivilite(InscritsMailingListe.CiviliteEnum.MME);
        } else {
            inscritsMailingListe.setCivilite(InscritsMailingListe.CiviliteEnum.MR);
        }
        inscritsMailingListe.setNom(dto.getNom());
        inscritsMailingListe.setPrenom(dto.getPrenom());
        inscritsMailingListe.setEmail(dto.getEmail());
        inscritsMailingListe.setAccordMail(dto.isAccordMail());
        inscritsMailingListe.setPublicContact(dto.isPublicContact());
        inscritsMailingListe.adhesionsSaisonCourante(dto.isAdhesionSaisonCourante());
        inscritsMailingListe.setLienPhotoProfil(dto.getLienPhotoProfil());

        return inscritsMailingListe;

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

                    if(dto.getIdAuthority() == 4 && (this.hasRole("ADMIN") || this.hasRole("CONSEIL") )) {
                        lst.add(this.transformeToModel(dto));
                    }
                    if (dto.getIdAuthority() == 2) {
                        lst.add(this.transformeToModel(dto));
                    }

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
        dto.setIdMail(idMail);

        // Si envoi poune liste de diffusion
        if (body.getTypeMail() == MailAEnvoyer.TypeMailEnum.NUMBER_10) {
            LOGGER.debug("Envoyer un mail à la mailing liste {}", body.getIdListeDiffusion());

            AdherentDto auteurMaildto = getIdAdherentConnecte();

            dto.setMailReplyTo(auteurMaildto.getEmail());
            dto.setAuteurName(auteurMaildto.getNom() + " " + auteurMaildto.getPrenom());

            boolean restult = listeDiffusionService.envoyerMailListeDiffusion(dto, body.getIdListeDiffusion());

            try {
                this.closeFilIpts(dto.getLstFile());
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
            if (restult) {
                this.delFolder(Long.toString(idMail));
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            }
            else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Si envoie pour une liste d'adherent, option uniquement pour le conseil et l'admin
        else if ((body.getTypeMail() != MailAEnvoyer.TypeMailEnum.NUMBER_10) && (this.hasRole("CONSEIL") || this.hasRole("ADMIN") )){
            LOGGER.debug("Envoyer un mail aux adhérents de type {}", Integer.parseInt(body.getTypeMail().toString()));

            boolean restult = listeDiffusionService.envoyerMailListeAdherent(dto, Integer.parseInt(body.getTypeMail().toString()));

            try {
                this.closeFilIpts(dto.getLstFile());
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
            if (restult) {
                this.delFolder(Long.toString(idMail));
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            }
            else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Fermutre des InputStream
     * @param lstFile
     * @throws IOException
     */
    private void closeFilIpts(Collection<InputStreamCustom> lstFile) throws IOException {
        if (lstFile != null){
            for(InputStreamCustom ipts : lstFile) {
                ipts.getInputStream().close();
            }
        }

    }

    /**
     * Supprimer un répertoire avec document lié
     * @param toString
     */
    private void delFolder(String directoryName) {
        StringBuilder cheminDossier = new StringBuilder();
        cheminDossier.append("/tmp/");
        cheminDossier.append("/");
        cheminDossier.append(directoryName);

        File directory = new File(cheminDossier.toString());
        if (directory.exists() ) {
            for (File f : directory.listFiles()) {
                f.delete();
            }
            directory.delete();
        }
    }

    /**
     * Recherche les fichiers contenu dans un répertoire
     * @param folder
     */
    private Collection<InputStreamCustom> listFilesForFolder(String directoryName) {

        Collection<InputStreamCustom> lstFiles = new ArrayList<>();


        final File folder = new File("/tmp/"+directoryName);
        if(folder.exists()) {
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
        } else {
            LOGGER.info("PAs de répertoire {}" , directoryName);
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
        model.setIdAuthority(dto.getIdAuthority());
        model.setNbInscrit(Long.valueOf(dto.getNbInscrit()));

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
        dto.setIdAuthority(model.getIdAuthority());

        return dto;
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username != null) {
            AdherentDto dto = this.adherentService.recupererAdherent(username);
            return dto;
        }
        LOGGER.error("Aucun username recupérer dans le jeton d'authentification");
        return null;
    }

}
