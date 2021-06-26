package fr.espaceadh.adherents.controller;

import fr.espaceadh.adherents.model.Adherent;
import fr.espaceadh.adherents.model.Adhesion;
import fr.espaceadh.adherents.model.ListeAdherents;
import fr.espaceadh.adherents.model.ListeAdhesions;
import fr.espaceadh.adherents.model.ListeManifestations;
import fr.espaceadh.adherents.model.ListeCommunications;
import fr.espaceadh.adherents.model.ParticipationManifestation;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.adherents.dto.AdherentDto;
import fr.espaceadh.adherents.dto.AdherentEvenementDto;
import fr.espaceadh.adherents.dto.AdhesionDto;
import fr.espaceadh.adherents.dto.CiviliteEnum;
import fr.espaceadh.adherents.dto.TypeAdhesionEnum;
import fr.espaceadh.adherents.model.Communication;
import fr.espaceadh.adherents.model.LiensAdherent;
import fr.espaceadh.adherents.model.Manifestation;
import fr.espaceadh.adherents.service.AdherentEvenementsService;
import fr.espaceadh.adherents.service.AdherentService;
import fr.espaceadh.lib.mail.GestionMail;
import fr.espaceadh.lib.mail.dto.ListeMessagesResulteDto;
import fr.espaceadh.lib.mail.dto.MessageResultDto;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-11-14T09:31:23.328Z[GMT]")
@Controller
public class AdherentApiController implements AdherentApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdherentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    protected AdherentService adherentService;
    
    @Autowired
    protected AdherentEvenementsService adherentEvenementsService;
    
    @Autowired
    private GestionMail getionMail;
    
    @Autowired
    private Environment env;

    @org.springframework.beans.factory.annotation.Autowired
    public AdherentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    /**
     * Ajouter un adhérents
     * @param body
     * @return 
     */
    public ResponseEntity<Void> ajoutAdherent(@ApiParam(value = "Besoin de l'objet adhérent pour ajouter un adhérent" ,required=true )  @Valid @RequestBody Adherent body) {
        
        AdherentDto dto = this.translateModel(body);
        
        // ajouter l'id de l'utilisateur qui a créé l'adhérent
        dto.setIdAdherentUpdate(this.getIdAdherentConnecte());
        
        int result = adherentService.creerAdherent(dto);
        
        if(result == 0) return  new ResponseEntity<Void>(HttpStatus.CREATED);
        else if (result == 10) new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); //TODO revoir pour les invilides imputes

        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

    }

    /**
     * Ajouter une adhésion à un adhérent
     * @param idadh
     * @param body
     * @return 
     */
    public ResponseEntity<Void> ajoutAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "Objet adhesion", required=true, schema=@Schema()) @Valid @RequestBody Adhesion body) {
        String accept = request.getHeader("Accept");

        boolean ok = this.adherentService.creerAdhesion(this.convertDto(body));
        if (ok) return  new ResponseEntity<Void>(HttpStatus.CREATED);
        //Revoir les codes retours en erreur
        
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
    

    /**
     * Ajouter un lien entre 2 adherents
     * @param idadh
     * @param idAdhLien
     * @return 
     */
    public ResponseEntity<Void> ajoutLienAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent representant", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de l'adherent representé", required=true, schema=@Schema()) @PathVariable("idAdhLien") Long idAdhLien) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Ajouter la participation à une manigestation
     * @param idadh
     * @param idManifestation
     * @param body
     * @return 
     */
    public ResponseEntity<Void> ajoutManifestationAdherent(@Parameter(in = ParameterIn.PATH, description = "id l'adherent à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de la manifestation à modifier", required=true, schema=@Schema()) @PathVariable("idManifestation") Long idManifestation,@Parameter(in = ParameterIn.DEFAULT, description = "Besoin de l'objet manifestation le lier à un adherents", required=true, schema=@Schema()) @Valid @RequestBody ParticipationManifestation body) {
        String accept = request.getHeader("Accept");
        
        
        boolean ok = this.adherentEvenementsService.updateParticipationEvenement(idadh, idManifestation, this.statutParticipationManifestation(body.getStatutParticipation()));
        if (ok) return  new ResponseEntity<Void>(HttpStatus.CREATED);
        
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
    
    public ResponseEntity<Void> deleteAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de l'adhesion à supprimer", required=true, schema=@Schema()) @PathVariable("idAdhesion") Long idAdhesion) {
        String accept = request.getHeader("Accept");
        //TODO A implémenter
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    /**
     * Supression d'un lien entre 2 adherents
     * @param idadh
     * @param idAdhLien
     * @return 
     */
    public ResponseEntity<Void> deleteLienAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent representant", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de l'adherent representé", required=true, schema=@Schema()) @PathVariable("idAdhLien") Long idAdhLien) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Supression d'une participation à un adhérent
     * @param idadh
     * @param idManifestation
     * @return 
     */
    public ResponseEntity<Void> deleteManifestationAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de la manifestation à supprimer", required=true, schema=@Schema()) @PathVariable("idManifestation") Long idManifestation) {
        String accept = request.getHeader("Accept");
        
        boolean ok = this.adherentEvenementsService.updateParticipationEvenement(idadh, idManifestation, 3);
        if (ok) return  new ResponseEntity<Void>(HttpStatus.CREATED);
        
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Supprimer un adhérent
     * @param idadh
     * @return 
     */
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * recuperer un adherent
     * @param idadh
     * @return 
     */
    public ResponseEntity<Adherent> getAdherent(@ApiParam(value = "id de la personne à modifier",required=true) @PathVariable("idadh") Long idadh) {       
        
        AdherentDto dto = this.adherentService.recupererAdherent(idadh);
        
        if (dto == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Adherent adh = this.translateDto(dto);
        
        return new ResponseEntity<>(adh,HttpStatus.OK);
    }

    /**
     * Recherche un adhérent via son mail
     * @param mailadherent
     * @return 
     */
   public ResponseEntity<Adherent> getAdherentByMail(@NotNull @Parameter(in = ParameterIn.QUERY, description = "mail de l'adhérent" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "mailadherent", required = true) String mailadherent) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            AdherentDto dto = this.adherentService.recupererAdherent(mailadherent);
            if (dto == null){
                return new ResponseEntity<>(null,HttpStatus.OK);
            } 
            else {
                Adherent adh = this.translateDto(dto);
                return new ResponseEntity<>(adh,HttpStatus.OK);
            }
        }

        return new ResponseEntity<Adherent>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    /**
     * Retourner les adhésions dans adhérent
     * @param idadh
     * @param idAdhesion
     * @return 
     */
    public ResponseEntity<Adhesion> getAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de l'adhesion à recuperer", required=true, schema=@Schema()) @PathVariable("idAdhesion") Long idAdhesion) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            AdhesionDto dto = this.adherentService.getAdhesionPourUnAdherent(idadh, idAdhesion);
            
            Adhesion model = this.convertModel(dto);
            
            return new ResponseEntity<>(model,HttpStatus.OK);
        }

        return new ResponseEntity<Adhesion>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    /**
     * Rechercher la list des liens de l'adhérents
     * @param idadh
     * @return 
     */
    public ResponseEntity<LiensAdherent> getLienAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            
        }

        return new ResponseEntity<LiensAdherent>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Recuperer l'ensemble des adherents
     * @return 
     */
    public ResponseEntity<ListeAdherents> getListeAdherents() {
        String accept = request.getHeader("Accept");
        ListeAdherents lstAdh =  new ListeAdherents();
        
        
        Collection<AdherentDto> lstAdherentDto =  this.adherentService.recupererListeCompletAdherent();
        
        for (AdherentDto adhDto : lstAdherentDto){
            lstAdh.add(this.translateDto(adhDto));
        }
        
        return new ResponseEntity<>(lstAdh,HttpStatus.OK);

    }

    /**
     * recuperer les adherents de la saison
     * @return 
     */
    public ResponseEntity<ListeAdherents> getListeAdherentsSaison() {
        String accept = request.getHeader("Accept");
        ListeAdherents lstAdh =  new ListeAdherents();
        
        
        Collection<AdherentDto> lstAdherentDto =  this.adherentService.recupererListeAdherentSaison();
        
        for (AdherentDto adhDto : lstAdherentDto){
            lstAdh.add(this.translateDto(adhDto));
        }
        
        return new ResponseEntity<>(lstAdh,HttpStatus.OK);
    }

    public ResponseEntity<ListeAdhesions> getListeAdhesionsAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Collection<AdhesionDto> lstDto = this.adherentService.getAdhesionsPourUnAdherent(idadh);
            
            ListeAdhesions lstModel = this.convertModel(lstDto);
            
            return new ResponseEntity<>(lstModel,HttpStatus.OK);
        }

        return new ResponseEntity<ListeAdhesions>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 
     * @param idadh
     * @return 
     */
    public ResponseEntity<ListeCommunications> getListeCommunicationAdhrent(@Parameter(in = ParameterIn.PATH, description = "id d'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh) {
       
        AdherentDto adh = this.adherentService.recupererAdherent(idadh);
        ListeMessagesResulteDto lstMessage = this.getionMail.recupeHistoriqueMessage(adh.getEmail());
        
        ListeCommunications listeCommunications = new ListeCommunications();
        
        if (lstMessage.getLstMessageResulteDto() != null && !lstMessage.getLstMessageResulteDto().isEmpty()){

           for(MessageResultDto msgDto : lstMessage.getLstMessageResulteDto()){
                Communication communicationModel = new Communication();
                
               communicationModel.setDateArrive(this.dateToStringAvecMS(msgDto.getDateArrive()));
               communicationModel.setDestinataire(msgDto.getMailDestinataire());
               communicationModel.setId(msgDto.getId());
               communicationModel.setRegleSpam(msgDto.getRegleSpam());
               communicationModel.setScoreSpam(msgDto.getScoreSpam());
               communicationModel.setStatut(this.convertStatutEnum(msgDto.getStatut()));
                
               communicationModel.setSujet(msgDto.getSujetMail());
               communicationModel.setUUID(msgDto.getUUID());
               listeCommunications.add(communicationModel);
           }   
        }
        
        return new ResponseEntity<>(listeCommunications,HttpStatus.OK);
    }


    /**
     * Recupérer la liste des manifestations avec précisions si l'adhérent y participe
     * @param idadh
     * @return 
     */
    public ResponseEntity<ListeManifestations> getListeManifestationsAdherent(@Parameter(in = ParameterIn.PATH, description = "id d'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.QUERY, description = "date de début" ,schema=@Schema()) @Valid @RequestParam(value = "datedebut", required = false) String datedebut,@Parameter(in = ParameterIn.QUERY, description = "date de fin" ,schema=@Schema()) @Valid @RequestParam(value = "datefin", required = false) String datefin) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Collection<AdherentEvenementDto>  lstAdhEvenementDto = null;
            
            if (datedebut != null && datefin != null) {
                Date dateDebutIso = toDateSansHeure(datedebut);
                Date dateFinIso = toDateSansHeure(datefin);
                lstAdhEvenementDto = this.adherentEvenementsService.getLstEvenement(idadh, dateDebutIso, dateFinIso);
            } else {
                lstAdhEvenementDto = this.adherentEvenementsService.getLstEvenement(idadh);
            }
            
            ListeManifestations lstManifestation = new ListeManifestations();
            if (lstAdhEvenementDto != null){
                for (AdherentEvenementDto dto : lstAdhEvenementDto){
                    lstManifestation.add(this.transformeModel(dto));
                }
            }
            return new ResponseEntity<>(lstManifestation,HttpStatus.OK);
        }

        return new ResponseEntity<ListeManifestations>(HttpStatus.NOT_IMPLEMENTED);

    }
    
    
        /**
     * Transforme un dto evenement en model envement
     * @param dto
     * @return 
     */
    private Manifestation transformeModel(AdherentEvenementDto dto) {
        Manifestation model = new Manifestation();
        model.setId(dto.getIdEvenement());
        model.setIdAdherent(dto.getIdAdherent());
        model.setDescriptionCourte(dto.getDescriptionCourte());
        model.setDescriptionLongue(dto.getDescriptionLongue());
        model.setLieux(dto.getLieux());
        model.setDateDebut(this.dateToStringAvecMS(dto.getDateDebut()));
        model.setDateFin(this.dateToStringAvecMS(dto.getDateFin()));
        
        /**
         * type de participation
        1 : PARTICIPE
        2 : PARTICIPE PAS
        3 : NE SAIS PAS
        **/
        if (dto.getTypeParticipation()== 1){
            model.setStatutParticipation(Manifestation.StatutParticipationEnum.NUMBER_1); 
        } else if (dto.getTypeParticipation() == 2){
             model.setStatutParticipation(Manifestation.StatutParticipationEnum.NUMBER_2); 
        } else {
            model.setStatutParticipation(Manifestation.StatutParticipationEnum.NUMBER_3); 
        }

 
        return model;
    }
    
    
    
    public ResponseEntity<Manifestation> getManifestationsAdherent(@Parameter(in = ParameterIn.PATH, description = "id d'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de la manifestation à recuperer", required=true, schema=@Schema()) @PathVariable("idManifestation") Long idManifestation) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            AdherentEvenementDto  adhEvenementDto = null;
            
            adhEvenementDto = this.adherentEvenementsService.getEvenement(idadh, idManifestation);
            
            Manifestation manifestation = new Manifestation();
            if (adhEvenementDto != null){
                manifestation = this.transformeModel(adhEvenementDto);
            }
            return new ResponseEntity<>(manifestation,HttpStatus.OK);
        }

        return new ResponseEntity<Manifestation>(HttpStatus.NOT_IMPLEMENTED);
    }
    

    public ResponseEntity<Void> updateAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de l'adhesion de modifier", required=true, schema=@Schema()) @PathVariable("idAdhesion") Long idAdhesion,@Parameter(in = ParameterIn.DEFAULT, description = "mise à jour d'une adhesion", required=true, schema=@Schema()) @Valid @RequestBody Adhesion body) {
        //TODO A implémzenter
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * MàJ de la participation pour un adhérent
     * @param idadh
     * @param idManifestation
     * @param body
     * @return 
     */
    public ResponseEntity<Void> updateManifestationAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de la manifestation à modifier", required=true, schema=@Schema()) @PathVariable("idManifestation") Long idManifestation,@Parameter(in = ParameterIn.DEFAULT, description = "mise à jour d'une adhesion", required=true, schema=@Schema()) @Valid @RequestBody ParticipationManifestation body) {
        String accept = request.getHeader("Accept");
        
        boolean ok = this.adherentEvenementsService.updateParticipationEvenement(idadh, idManifestation, this.statutParticipationManifestation(body.getStatutParticipation())
        );
        if (ok) return  new ResponseEntity<Void>(HttpStatus.CREATED);
        
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Mise à jour de l'adherent
     * @param idadh
     * @param body
     * @return 
     */
    public ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "Objet adherent", required=true, schema=@Schema()) @Valid @RequestBody Adherent body) {
        //récupération de l'adh avant modification
        AdherentDto adhold = this.adherentService.recupererAdherent(idadh);
        
        AdherentDto adhnew = this.translateModel(body);
        adhnew.setId(adhold.getId());
        adhnew.setDateEnregistrement(adhold.getDateEnregistrement());
        adhnew.setLienPhotoProfil(adhold.getLienPhotoProfil());
       // adhnew.setCommentaire(adhold.getCommentaire());
        
        // ajouter l'id de l'utilisateur qui a met à jour l'adhérent
        adhnew.setIdAdherentUpdate(this.getIdAdherentConnecte());
        
        boolean updateok = this.adherentService.updateAdherents(adhnew);
        if (updateok)return new ResponseEntity<>(HttpStatus.OK);
        
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Mise à jour de la photo de profil de l'adhérent
     * @param idadh id de l'adhérent
     * @param fileName nom du fichier
     * @param file fichier binaire
     * @return 
     */
    public ResponseEntity<Void> updateUserPhoto(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="fileName", required=true)  String fileName,@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file) {
        String accept = request.getHeader("Accept");
        
       LOGGER.info("userid {}, fileName {}", idadh, fileName);

        try {
            
            // Supression de l'ancien fichier
            boolean resultDel = this.deleteFilesForPathByPrefix(env.getProperty("folder.path.photo.profil"), idadh.toString());
            if (!resultDel) {
                LOGGER.error("Erreur lors de la supression du fichier avec le préfix {}", idadh.toString());
                return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // création du nouveau fichier
            StringBuilder cheminFichier = new StringBuilder();
            cheminFichier.append(env.getProperty("folder.path.photo.profil"));
            cheminFichier.append("/");
            cheminFichier.append(fileName);

            LOGGER.info("Enregistrement du fichier : {}", cheminFichier.toString());

            Files.copy(file.getInputStream(), Paths.get(cheminFichier.toString()), StandardCopyOption.REPLACE_EXISTING);
            
            Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);

            perms.add(PosixFilePermission.OTHERS_READ);

            perms.add(PosixFilePermission.GROUP_READ);

            Files.setPosixFilePermissions(Paths.get(cheminFichier.toString()), perms);
            
            // Màj de la BD avec le nouveau nom du fichier
            this.adherentService.updateLienPhotoAdherent(idadh, fileName);
            
        } catch (IOException ex) {
            LOGGER.error("Erreur copie fichier {}", ex);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    

    /**
     * Supression d'un fichier avec préfix
     * @param path
     * @param prefix
     * @return 
     */
    private boolean deleteFilesForPathByPrefix(final String path, final String prefix) {
    boolean success = true;
    try (DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(Paths.get(path), prefix + "*")) {
        for (final Path newDirectoryStreamItem : newDirectoryStream) {
            Files.delete(newDirectoryStreamItem);
        }
    } catch (final Exception e) {
        success = false;
        LOGGER.error(e.getMessage());
    }
    return success;
}

    
    /**
     * transformer le model adherent en dto adherent
     */
    private AdherentDto translateModel(Adherent model){
        AdherentDto adh = new AdherentDto();
        
        adh.setId(model.getId());
        if (model.getCivilite() == model.getCivilite().MME){
           adh.setCivilite(CiviliteEnum.MADAME);  
        } else {
            adh.setCivilite(CiviliteEnum.MONSIEUR);  
        }
        adh.setNom(model.getNom());
        adh.setPrenom(model.getPrenom());
        adh.setAdresse1(model.getAdresse1());
        adh.setAdresse2(model.getAdresse2());
        adh.setCodePostal(model.getCodePostal());
        adh.setVille(model.getVille());
        adh.setTelMaison(model.getTelMaison());
        adh.setTelPortable(model.getTelPortable());
        adh.setTelTravail(model.getTelTravail());
        adh.setEmail(model.getEmail());
        adh.setProfession(model.getProfession());
        if (model.getDateNaissance() != null) {
            adh.setDateNaissance(this.toDate(model.getDateNaissance()));
        }
        adh.setAccordMail(model.isAccordMail());
        adh.setPublicContact(model.isPublicContact());
        adh.setCommentaire(model.getCommentaire());
        adh.setDateEnregistrement(new Date());
        adh.setDateMiseAJour(new Date());

        
        return adh;
    }
    
    
    
    /**
     * Transformer un DTO en model
     * @param adherent
     * @return 
     */
     private Adherent translateDto(AdherentDto adherent){
        Adherent model = new Adherent();
        
        model.setId(adherent.getId());
        if (adherent.getCivilite() == adherent.getCivilite().MADAME){
           model.setCivilite(model.getCivilite().MME);  
        } else {
            model.setCivilite(model.getCivilite().MR);  
        }
        model.setNom(adherent.getNom());
        model.setPrenom(adherent.getPrenom());
        model.setAdresse1(adherent.getAdresse1());
        model.setAdresse2(adherent.getAdresse2());
        model.setCodePostal(adherent.getCodePostal());
        model.setVille(adherent.getVille());
        model.setTelMaison(adherent.getTelMaison());
        model.setTelPortable(adherent.getTelPortable());
        model.setTelTravail(adherent.getTelTravail());
        model.setEmail(adherent.getEmail());
        model.setProfession(adherent.getProfession());
        model.setDateNaissance(this.dateToStringAvecMS(adherent.getDateNaissance()));
        model.setLienPhotoProfil(adherent.getLienPhotoProfil());
        model.setAccordMail(adherent.isAccordMail());
        model.setPublicContact(adherent.isPublicContact());
        model.setCommentaire(adherent.getCommentaire());
        model.setDateEnregistrement(this.dateToStringAvecMS(adherent.getDateEnregistrement()));
        model.setDateMiseAJour(this.dateToStringAvecMS(adherent.getDateMiseAJour()));
        model.setAdhesionsSaisonCourante(adherent.isAdhesionSaisonCourante());
        return model;
    }

     /**
      * 
      * @param statut
      * @return 
      */
    private Communication.StatutEnum convertStatutEnum(String statut) {
        if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.BLOCKED).equals(statut)){
            return Communication.StatutEnum.BLOCKED;
        } else if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.BOUNCE).equals(statut)){
            return Communication.StatutEnum.BOUNCE;
        } else if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.CLICKED).equals(statut)){
            return Communication.StatutEnum.CLICKED;
        } else if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.DEFERRED).equals(statut)){
            return Communication.StatutEnum.DEFERRED;
        } else if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.HARDBOUNCED).equals(statut)){
            return Communication.StatutEnum.HARDBOUNCED;
        } else if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.OPENED).equals(statut)){
            return Communication.StatutEnum.OPENED;
        } else if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.QUEUED).equals(statut)){
            return Communication.StatutEnum.QUEUED;
        } else if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.SOFTBOUNCED).equals(statut)){
            return Communication.StatutEnum.SOFTBOUNCED;
        } else if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.SPAM).equals(statut)){
            return Communication.StatutEnum.SPAM;
        } else if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.UNSUB).equals(statut)){
            return Communication.StatutEnum.UNSUB;
        }  else if(String.valueOf(fr.espaceadh.lib.mail.model.mailjet.Message.StatusEnum.SENT).equals(statut)){
            return Communication.StatutEnum.SENT;
        }
        
        return Communication.StatutEnum.UNKNOWN;
    }

    /**
     * convertir date en string format ISO
     * @param dateArrive
     * @return 
     */
    private String dateToStringAvecMS(Date date) {
        if (date == null){
            return null;
        }
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        return sdf.format(date);
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

    
    
    /**
     * Convertir un date iso en date sans les milisecondes
     * @param iso8601string
     * @return 
     */
    public Date toDateSansMS(final String iso8601string) {
        if (iso8601string == null) {
            return null;
        }
        
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        Date result2 = null;
        try {
            result2 = df2.parse(iso8601string);
        } catch (ParseException ex) {
            LOGGER.error("Erreur sur le formatage  de date. Date en entré {}. {}", iso8601string , ex.getMessage());
        }


        return result2;
    }

        /** Transform ISO 8601 string to Calendar.
     * @param iso8601string
     * @return  */
    public  Date toDate(final String iso8601string) {

        if (iso8601string == null) return null;
        return Date.from( Instant.parse( iso8601string));
    }
    /**
     * Convert adhesion model en adhesion DTO
     * @param body
     * @return 
     */
    private AdhesionDto convertDto(Adhesion body) {
        AdhesionDto dto = new AdhesionDto();
        
        dto.setId(body.getId());
        dto.setIdAdherent(body.getIdAdherent());
        dto.setIdAnneeAdhesion(body.getIdAnneeAdhesion());
        
        switch (body.getIdTypeAdhesion()) {
            case NUMBER_1:
                dto.setIdTypeAdhesion(TypeAdhesionEnum.ADULTE);
                break;
            case NUMBER_2:
                dto.setIdTypeAdhesion(TypeAdhesionEnum.FAMILLE);
                break;
            case NUMBER_3:
                dto.setIdTypeAdhesion(TypeAdhesionEnum.RESPONSABLE_DE_FAMILLE);
                break;
            case NUMBER_4:
                dto.setIdTypeAdhesion(TypeAdhesionEnum.ENFANT);
                break;
            case NUMBER_5:        
                dto.setIdTypeAdhesion(TypeAdhesionEnum.BIENFAITEUR);
                break;
            case NUMBER_6:
                dto.setIdTypeAdhesion(TypeAdhesionEnum.HONNEUR);
                break;
            case NUMBER_7:
                dto.setIdTypeAdhesion(TypeAdhesionEnum.ETUDIANT);
                break;
            default:
                dto.setIdTypeAdhesion(TypeAdhesionEnum.ADULTE);
                break;
        }
        
        dto.setComptaBanque(body.getComptaBanque());
        dto.setComptaNumCheque(body.getComptaNumCheque());
        dto.setComptaSomme(body.getComptaSomme());
        dto.setCheque(body.isCheque());
        dto.setEspace(body.isEspace());
        dto.setCarteAdhesion(false); //TODO A intégrer la gestion des impression des cartes
        
        return dto;
    }

    /**
     * Convert adhesion dto en adhesion model
     * @param dto
     * @return 
     */
    private Adhesion convertModel(AdhesionDto dto) {
        if (dto == null) return null;
        
        Adhesion adh = new Adhesion();
        adh.setId(dto.getId());
        adh.setIdAdherent(dto.getIdAdherent());
        adh.setIdAnneeAdhesion(dto.getIdAnneeAdhesion());

        switch (dto.getIdTypeAdhesion()) {
            case ADULTE:
                adh.setIdTypeAdhesion(Adhesion.IdTypeAdhesionEnum.NUMBER_1);
                break;
            case FAMILLE:
                adh.setIdTypeAdhesion(Adhesion.IdTypeAdhesionEnum.NUMBER_2);
                break;
            case RESPONSABLE_DE_FAMILLE:
                adh.setIdTypeAdhesion(Adhesion.IdTypeAdhesionEnum.NUMBER_3);
                break;
            case ENFANT:
                adh.setIdTypeAdhesion(Adhesion.IdTypeAdhesionEnum.NUMBER_4);
                break;
            case BIENFAITEUR:        
                 adh.setIdTypeAdhesion(Adhesion.IdTypeAdhesionEnum.NUMBER_5);
                break;
            case HONNEUR:
                adh.setIdTypeAdhesion(Adhesion.IdTypeAdhesionEnum.NUMBER_6);
                break;
            case ETUDIANT:
                adh.setIdTypeAdhesion(Adhesion.IdTypeAdhesionEnum.NUMBER_7);
                break;
            default:
                adh.setIdTypeAdhesion(Adhesion.IdTypeAdhesionEnum.NUMBER_1);
                break;
        }
                
        
        adh.setComptaBanque(dto.getComptaBanque());
        adh.setComptaNumCheque(dto.getComptaNumCheque());
        adh.setComptaSomme(dto.getComptaSomme());
        adh.setCheque(dto.isCheque());
        adh.setEspace(dto.isEspace());
        adh.setLibelleAnneeAdhesion(dto.getLibelleAnneeAdhesion());
        
        return adh;
    }

    /**
     * Convert aliste dhesion dto en liste adhesion model
     * @param lstDto
     * @return 
     */
    private ListeAdhesions convertModel(Collection<AdhesionDto> lstDto) {
        ListeAdhesions lstAdhesions = new ListeAdhesions();

        if (lstDto != null && !lstDto.isEmpty()) {
            for (AdhesionDto dto : lstDto) {
                lstAdhesions.add(this.convertModel(dto));
            }
            return lstAdhesions;
        }
        else {
            return null;
        }

    }

    private int statutParticipationManifestation(ParticipationManifestation.StatutParticipationEnum statutParticipation) {
        /**
         * type de participation
        1 : PARTICIPE
        2 : PARTICIPE PAS
        3 : NE SAIS PAS
        **/
        if (statutParticipation ==  ParticipationManifestation.StatutParticipationEnum.NUMBER_1){
           return 1;
        } else if (statutParticipation ==  ParticipationManifestation.StatutParticipationEnum.NUMBER_2){
             return 2;
        } else {
            return 3;
        }
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


}
