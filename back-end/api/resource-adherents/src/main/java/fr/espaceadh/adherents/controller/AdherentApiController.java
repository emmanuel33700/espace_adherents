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
import fr.espaceadh.adherents.dto.AdhesionDto;
import fr.espaceadh.adherents.dto.CiviliteEnum;
import fr.espaceadh.adherents.dto.TypeAdhesionEnum;
import fr.espaceadh.adherents.model.Communication;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-11-14T09:31:23.328Z[GMT]")
@Controller
public class AdherentApiController implements AdherentApi {

    private static final Logger log = LoggerFactory.getLogger(AdherentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    protected AdherentService adherentService;
    
    @Autowired
    private GestionMail getionMail;

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
        
        //TODO ajouter l'id de l'utilisateur qui a créé l'adhérent
        
        int result = adherentService.creerAdherent(dto);
        
        if(result == 0) return  new ResponseEntity<Void>(HttpStatus.CREATED);
        else if (result == 10) new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); //TODO revoir pour les invilides imputes

        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Void> ajoutAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "Objet adhesion", required=true, schema=@Schema()) @Valid @RequestBody Adhesion body) {
        String accept = request.getHeader("Accept");

        boolean ok = this.adherentService.creerAdhesion(this.convertDto(body));
        if (ok) return  new ResponseEntity<Void>(HttpStatus.CREATED);
        //Revoir les codes retours en erreur
        
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> ajoutManifestationAdherent(@Parameter(in = ParameterIn.PATH, description = "id l'adherent à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "Besoin de l'objet manifestation le lier à un adherents", required=true, schema=@Schema()) @Valid @RequestBody ParticipationManifestation body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de l'adhesion à supprimer", required=true, schema=@Schema()) @PathVariable("idAdhesion") Long idAdhesion) {
        String accept = request.getHeader("Accept");
        //TODO A implémenter
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteManifestationAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de la manifestation à supprimer", required=true, schema=@Schema()) @PathVariable("idManifestation") Long idManifestation) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
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
                
               communicationModel.setDateArrive(this.dateToString(msgDto.getDateArrive()));
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


    public ResponseEntity<ListeManifestations> getListeManifestationsAdhrent(@Parameter(in = ParameterIn.PATH, description = "id d'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListeManifestations>(objectMapper.readValue("[ {\n  \"descriptionLongue\" : \"Manifestation à la conférence sur le soleil qui sera présenté par Mr dupond\",\n  \"dateDebut\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"idAdherent\" : 1,\n  \"descriptionCourte\" : \"Manifestation à la conférence\",\n  \"lieux\" : \"Martignas\",\n  \"id\" : 1,\n  \"dateFin\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"statutParticipation\" : 1\n}, {\n  \"descriptionLongue\" : \"Manifestation à la conférence sur le soleil qui sera présenté par Mr dupond\",\n  \"dateDebut\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"idAdherent\" : 1,\n  \"descriptionCourte\" : \"Manifestation à la conférence\",\n  \"lieux\" : \"Martignas\",\n  \"id\" : 1,\n  \"dateFin\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"statutParticipation\" : 1\n} ]", ListeManifestations.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListeManifestations>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }



        return new ResponseEntity<ListeManifestations>(HttpStatus.NOT_IMPLEMENTED);

    }

    public ResponseEntity<Void> updateAdhesionAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de l'adhesion de modifier", required=true, schema=@Schema()) @PathVariable("idAdhesion") Long idAdhesion,@Parameter(in = ParameterIn.DEFAULT, description = "mise à jour d'une adhesion", required=true, schema=@Schema()) @Valid @RequestBody Adhesion body) {
        //TODO A implémzenter
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateManifestationAdherent(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent à recuperer", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.PATH, description = "id de la manifestation à modifier", required=true, schema=@Schema()) @PathVariable("idManifestation") Long idManifestation,@Parameter(in = ParameterIn.DEFAULT, description = "mise à jour d'une adhesion", required=true, schema=@Schema()) @Valid @RequestBody ParticipationManifestation body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Mise à jour de l'adherent
     * @param idadh
     * @param body
     * @return 
     */
    public ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "Objet adherent", required=true, schema=@Schema()) @Valid @RequestBody Adherent body) {
        //récupérationd e l'adh avant modification
        AdherentDto adhold = this.adherentService.recupererAdherent(idadh);
        
        AdherentDto adhnew = this.translateModel(body);
        adhnew.setId(adhold.getId());
        adhnew.setCommentaire(adhold.getCommentaire());
        
        //TODO ajouter l'id de l'utilisateur qui a créé l'adhérent
        adhnew.setIdAdherentUpdate(adhold.getIdAdherentUpdate());
        
        boolean updateok = this.adherentService.updateAdherents(adhnew);
        if (updateok)return new ResponseEntity<>(HttpStatus.OK);
        
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
        adh.setVille(model.getVille());
        adh.setTelMaison(model.getTelMaison());
        adh.setTelPortable(model.getTelPortable());
        adh.setTelTravail(model.getTelTravail());
        adh.setEmail(model.getEmail());
        adh.setProfession(model.getProfession());
        if (model.getDateNaissance() != null) {
            adh.setDateNaissance(this.stringToDate(model.getDateNaissance()));
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
        model.setVille(adherent.getVille());
        model.setTelMaison(adherent.getTelMaison());
        model.setTelPortable(adherent.getTelPortable());
        model.setTelTravail(adherent.getTelTravail());
        model.setEmail(adherent.getEmail());
        model.setProfession(adherent.getProfession());
        model.setDateNaissance(this.dateToString(adherent.getDateNaissance()));
        model.setAccordMail(adherent.isAccordMail());
        model.setPublicContact(adherent.isPublicContact());
        model.setCommentaire(adherent.getCommentaire());
        model.setDateEnregistrement(this.dateToString(adherent.getDateEnregistrement()));
        model.setDateMiseAJour(this.dateToString(adherent.getDateMiseAJour()));
        
        return model;
    }

     /**
      * 
      * @param statut
      * @return 
      */
    private Communication.StatutEnum convertStatutEnum(String statut) {
        if(String.valueOf(Communication.StatutEnum.BLOCKED).equals(statut)){
            return Communication.StatutEnum.BLOCKED;
        } else if(String.valueOf(Communication.StatutEnum.BOUNCE).equals(statut)){
            return Communication.StatutEnum.BOUNCE;
        } else if(String.valueOf(Communication.StatutEnum.CLICKED).equals(statut)){
            return Communication.StatutEnum.CLICKED;
        } else if(String.valueOf(Communication.StatutEnum.DEFERRED).equals(statut)){
            return Communication.StatutEnum.DEFERRED;
        } else if(String.valueOf(Communication.StatutEnum.HARDBOUNCED).equals(statut)){
            return Communication.StatutEnum.HARDBOUNCED;
        } else if(String.valueOf(Communication.StatutEnum.OPENED).equals(statut)){
            return Communication.StatutEnum.OPENED;
        } else if(String.valueOf(Communication.StatutEnum.QUEUED).equals(statut)){
            return Communication.StatutEnum.QUEUED;
        } else if(String.valueOf(Communication.StatutEnum.SOFTBOUNCED).equals(statut)){
            return Communication.StatutEnum.SOFTBOUNCED;
        } else if(String.valueOf(Communication.StatutEnum.SPAM).equals(statut)){
            return Communication.StatutEnum.SPAM;
        } else if(String.valueOf(Communication.StatutEnum.UNSUB).equals(statut)){
            return Communication.StatutEnum.UNSUB;
        } 
        
        return Communication.StatutEnum.UNKNOWN;
    }

    /**
     * convertir date en string format ISO
     * @param dateArrive
     * @return 
     */
    private String dateToString(Date date) {
        if (date == null){
            log.info("date est null");
            return null;
        }
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        return sdf.format(date);
    }

    private Date stringToDate(String date) {
        if (date == null){
            log.info("date est null");
            return null;
        }
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            return df1.parse(date);
        } catch (ParseException ex) {
            log.error("error dans formatage de date " + ex.getLocalizedMessage());
        }
        return null;
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
        //TODO A intégrer la gestion des impression des cartes
        
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


    

}
