/*
 * Copyright (C) 2019 emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.espaceadh.authorization.controller;

import fr.espaceadh.authorization.model.Authentification;
import fr.espaceadh.authorization.model.Login;
import fr.espaceadh.authorization.model.ReinitAuthentification;
import fr.espaceadh.authorization.model.Roles;
import fr.espaceadh.authorization.model.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.espaceadh.authorization.dto.AuthoritiesDto;
import fr.espaceadh.authorization.dto.RolesEnum;
import fr.espaceadh.authorization.dto.UserDto;
import fr.espaceadh.authorization.model.ActivationAuthentification;
import fr.espaceadh.authorization.model.InfoAuthentification;
import fr.espaceadh.authorization.model.ModelApiResponse;
import fr.espaceadh.authorization.service.AuthentificationService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T09:41:12.158Z[GMT]")
@Controller
public class AuthentificationApiController implements AuthentificationApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthentificationApiController.class);

    @Autowired
    protected AuthentificationService authentificationService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    

    @org.springframework.beans.factory.annotation.Autowired
    public AuthentificationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    /**
     * Activer ou désactiver un authentification utilisateur
     * @param idadh
     * @param body
     * @return 
     */
    @Override
    public ResponseEntity<Void> activationAuthentification(@Parameter(in = ParameterIn.PATH, description = "id de la personne à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "information d'activation ou desactivation d'un authentification", required=true, schema=@Schema()) @Valid @RequestBody ActivationAuthentification body) {
        String accept = request.getHeader("Accept");
        boolean statut = false;
        if (body.isStatutActivation()) {
            statut = this.authentificationService.activerAuthentification(idadh.intValue());
        } else {
            statut = this.authentificationService.desactiverAuthentification(idadh.intValue());
        }
        
        if (statut) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        
        
    }

    /**
     * Ajout d'un compte
     * @param body
     * @return 
     */
    public ResponseEntity<Void> addAuthentification(@Parameter(in = ParameterIn.DEFAULT, description = "ajout de l'objet authentification", required=true, schema=@Schema()) @Valid @RequestBody Authentification body) {
        String accept = request.getHeader("Accept");
        
        
        UserDto dto = this.convertDto(body);

        authentificationService.creerUser(dto);
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    /**
     * Désactiver l'ensemble des Authentifications
     * @return 
     */
    public ResponseEntity<Void> desactiverAllAuthentification() {
        String accept = request.getHeader("Accept");
        this.authentificationService.desactiverEnsembleAuthentification();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteAuthentification(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Récupérer les informations d'authentification
     * @param idadh
     * @return 
     */
    public ResponseEntity<InfoAuthentification> getAuthentification(@Parameter(in = ParameterIn.PATH, description = "id de la personne à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            UserDto dto = this.authentificationService.recupererUser(idadh.intValue());
            
            if (dto != null) {
               InfoAuthentification model = new InfoAuthentification();
               model.setLogin(dto.getUsername());
               model.setIdAdh(idadh);
               model.setActif(dto.isEnabled());
               return new ResponseEntity<>(model, HttpStatus.OK);
            }
            return new ResponseEntity<InfoAuthentification>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<InfoAuthentification>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * R2cupération du role de la personne
     * @param idadh
     * @return 
     */
    public ResponseEntity<Roles> getRoles(@Parameter(in = ParameterIn.PATH, description = "id de la personne à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            AuthoritiesDto authoritiesDto = this.authentificationService.recupererAuthorities(idadh.intValue());
        
            return new ResponseEntity<>(this.concertModel(authoritiesDto),HttpStatus.OK);
        }

        return new ResponseEntity<Roles>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Demander la mise à jour du mot de passe
     * @param body
     * @return
     */
    public ResponseEntity<Void> resetPassword(@Parameter(in = ParameterIn.DEFAULT, description = "demander la réinitialisation du mot de passe", schema=@Schema()) @Valid @RequestBody Login body) {
        String accept = request.getHeader("Accept");

        boolean result =  this.authentificationService.demanderReinitialisationMotDePasse(body.getLogin());
        if (!result)  {
            this.LOGGER.warn("Demande en erreur  de modification du mot de passe du compte {} ", body.getLogin());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    /**
     * Mise à jour des informations d'authentification
     * @param idadh
     * @param body
     * @return 
     */
    public ResponseEntity<Void> updateAuthentification(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "mise à jour du login de la personne", required=true, schema=@Schema()) @Valid @RequestBody Authentification body) {
        String accept = request.getHeader("Accept");
        
        if (body.getPassword() == null && body.getLogin() != null && body.getIdAdh().intValue() == idadh.intValue()) {
            LOGGER.info("Modification du login d'un utilisateur id {} avec le username {} ", idadh.intValue(), body.getLogin());
            this.authentificationService.modifierInformationUtilisateur(idadh.intValue(), body.getLogin());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Mise à jour du role
     * @param idadh
     * @param body
     * @return 
     */
    public ResponseEntity<Void> updateRoles(@Parameter(in = ParameterIn.PATH, description = "id de la personne à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "mise à jour de l'objet role", required=true, schema=@Schema()) @Valid @RequestBody Roles body) {
         String accept = request.getHeader("Accept");
        
         this.authentificationService.modifierRolesUtilisateur(idadh.intValue(), this.convertDto(body.getRoles()));
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> validationAuthentification(@Parameter(in = ParameterIn.PATH, description = "id de l'adherent", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "Clée de validation reçu par mail au moment de la création du compte", required=true, schema=@Schema()) @Valid @RequestBody Validation body) {
        boolean result = authentificationService.validationCreationUser(idadh.intValue(), body.getCleeValidation());
        
        if (result) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else {
            ModelApiResponse apiResponse =  new ModelApiResponse();
            apiResponse.setMessage("Validation non effectué, id de l'utilisateur  ou clee de validation incorrecte");
            return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED );
        }
    }

    /**
     * Demander la validation de la réinitialisation du mot de passe
     * @param idadh
     * @param body
     * @return
     */
    public ResponseEntity<Void> valideResetPassword(@Parameter(in = ParameterIn.PATH, description = "id de la personne à modifier", required=true, schema=@Schema()) @PathVariable("idadh") Long idadh,@Parameter(in = ParameterIn.DEFAULT, description = "demander la validation de la réinitialisation du mot de passe", schema=@Schema()) @Valid @RequestBody ReinitAuthentification body) {
        String accept = request.getHeader("Accept");
        boolean result =  this.authentificationService.validerReinitialisationMotDePasse(idadh.intValue(), body.getCleeValidation(), passwordEncoder().encode(body.getPassword()));
        if (!result)  {
            this.LOGGER.warn("Demande en erreur  de validation  du mot de passe du compte {} ",idadh);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }



    
    
        /**
     * Transforme Authentification model eb UsersDTO
     * @param Authentification
     * @return UserDTO
     */
    private UserDto convertDto(Authentification body) {
        UserDto dto = new UserDto();
        dto.setIdAdherent(body.getIdAdh().intValue()); 
        dto.setUsername(body.getLogin());
        dto.setPasswordEncode(passwordEncoder().encode(body.getPassword()));
        
        return dto;
    }
    
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * concert DTO AuthoritiesDto to Roles model
     * @param authoritiesDto
     * @return 
     */
    private Roles concertModel(AuthoritiesDto authoritiesDto){
        Roles role = new Roles();
        

        for (RolesEnum roleEnumDto : authoritiesDto.getRoles()){
            
            switch (roleEnumDto) {
                case ADMIN:
                    role.addRolesItem(Roles.RolesEnum.ADMIN);
                    break;
                case CONSEIL:
                    role.addRolesItem(Roles.RolesEnum.CONSEIL);
                    break;
                case RES_ATELIER:
                    role.addRolesItem(Roles.RolesEnum.RES_ATELIER);
                    break;
                default:
                    role.addRolesItem(Roles.RolesEnum.ADHERENT);
                    break;
            }

        }
        
        return role;
        
    }

    /**
     * Concerte RoleEnum Model to DTO
     * @param rolesEnumModel
     * @return 
     */
    private  List<RolesEnum> convertDto(List<Roles.RolesEnum> rolesEnumModel){
        List<RolesEnum> roleEnumDto = new ArrayList<>();
        for (Roles.RolesEnum roleEnumModel : rolesEnumModel){
            switch (roleEnumModel) {
                case ADMIN:
                    roleEnumDto.add(RolesEnum.ADMIN);
                    break;
                case CONSEIL:
                    roleEnumDto.add(RolesEnum.CONSEIL);
                    break;
                case RES_ATELIER:
                    roleEnumDto.add(RolesEnum.RES_ATELIER);
                    break;
                default:
                    roleEnumDto.add(RolesEnum.ADHERENT);
                    break;
            }            
        }
        return roleEnumDto;
    }
    
}
