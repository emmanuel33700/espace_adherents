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
package fr.espaceadh.lib.mail.impl.mailjet;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import fr.espaceadh.lib.mail.GestionMail;
import fr.espaceadh.lib.mail.dto.TemplateMailEnum;
import java.util.HashMap;

/**
 *
 * @author emmanuel
 */
@Service
public class GestionMailImpl implements GestionMail {

    @Autowired
    private Environment env;
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GestionMailImpl.class);

    @Override
    public MailOutDto sendMail(MailInDto mailIn) {


        MailjetRequest request = null;
        MailjetResponse response;
        MailOutDto mailOutDto = new  MailOutDto();
        

        // transformtaiton des email en JSONArray pour préparer l'envoie mailjet
        JSONArray emailJsonTo  = this.getEmailJsonTo(mailIn);

       
        
        LOGGER.debug("Mail from {}", env.getProperty("message.from.mail"));
        LOGGER.debug("Mail to {}", emailJsonTo.toString());
        

        
        // Si demande d'envoie un e-mail avec template
        if (mailIn.getTemplateMailEnum() != null || mailIn.getTemplateMailEnum() != TemplateMailEnum.SANS_TEMPLATE){
            request= this.envoyerMailAvecTemplate(mailIn, emailJsonTo);
        }
        // demande d'envoie d'email sans template
        else{
            request= this.envoyerMailSansTemplate(mailIn, emailJsonTo);
        }
        
        try {
            //Envoie du mail via mailjet
            MailjetClient client = new MailjetClient(env.getProperty("mailjet.login"), env.getProperty("mailjet.password"), new ClientOptions("v3.1"));
            response = client.post(request);
            LOGGER.info("Retour de mailjet {} ", response.getData().toString());
            LOGGER.info( "statut maijet {}", response.getStatus());

            
            if (response.getStatus() == 200) {
                mailOutDto.setStatutEnvoi(response.getData().getJSONObject(0).getString("Status"));
            }
            else {
                mailOutDto.setStatutEnvoi("error");
            }
        } catch (MailjetException | MailjetSocketTimeoutException ex) {
            mailOutDto.setStatutEnvoi("error");
            LOGGER.error(ex.getMessage());
        }

        return  mailOutDto;
    }
    
    /**
     * demande d'envoie d'un email géré par mailjet
     * @param mailIn
     * @param emailto
     * @return 
     */
    private MailjetRequest envoyerMailAvecTemplate(MailInDto mailIn, JSONArray emailto) {
        int codeTemplateMailjet = this.recupererCodeTemplateMailJet(mailIn.getTemplateMailEnum());
        JSONObject variables = this.recupererVariablePourMailjet(mailIn.getTemplateVariables());
        
        MailjetRequest request;
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", env.getProperty("message.from.mail"))
                                        .put("Name", env.getProperty("message.from.name")))
                                .put(Emailv31.Message.TO, emailto)
                                .put(Emailv31.Message.TEMPLATEID, codeTemplateMailjet)
                                .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                                .put(Emailv31.Message.SUBJECT, mailIn.getSujetMail())
                                .put(Emailv31.Message.VARIABLES,variables)
                        ));
        return request;
    }
    
    
    /**
     * Envoyer un e-mail sans template géré par mail-jet
     * @param mailIn
     * @param emailto
     * @return 
     */
    private MailjetRequest envoyerMailSansTemplate(MailInDto mailIn, JSONArray emailto) {

        JSONObject message = new JSONObject();
        message.put(Emailv31.Message.FROM, new JSONObject()
                .put(Emailv31.Message.EMAIL, env.getProperty("message.from"))
                .put(Emailv31.Message.NAME, env.getProperty("message.from.name"))
        )
                .put(Emailv31.Message.SUBJECT, "Your email flight plan!")
                .put(Emailv31.Message.TEXTPART, "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
                .put(Emailv31.Message.HTMLPART, "<h3>Dear passenger, welcome to Mailjet</h3><br/>May the delivery force be with you!")
                .put(Emailv31.Message.TO, emailto)
                .put(Emailv31.Message.TEMPLATEID, "1")
                .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                .put(Emailv31.Message.SUBJECT, "Your email flight plan!");

        return new MailjetRequest(Emailv31.resource).property(Emailv31.MESSAGES, (new JSONArray()).put(message));
    }

    
    /**
     * Convertion d'une liste d'email en JSONArray pour préparer l'envoie à mail jet
     * @param mailIn
     * @return 
     */
    private JSONArray getEmailJsonTo(MailInDto mailIn) {
        JSONArray emailJsonTo = new JSONArray();
        /** si nous somme en dev, envoi de l'e-mail à une seule adresse configuré dans le fichier de properties **/
        if (env.getProperty("message.dev") != null && env.getProperty("message.dev", Boolean.class)) {
            LOGGER.info("Envoye de mail en mode dev");
            emailJsonTo.put(new JSONObject().put(Emailv31.Message.EMAIL, env.getProperty("message.to.mail")));
        } 
        /** sinon, envoie au mail indique dans le dto **/
        else {
            if (!mailIn.getMessageTo().isEmpty()) {
                for (String emailit : mailIn.getMessageTo()) {
                    emailJsonTo.put(new JSONObject().put(Emailv31.Message.EMAIL, emailit));
                }
            } else {
                LOGGER.error("Pas d'email destinataire");
            }
        }
        return emailJsonTo;
    }

    /**
     * Recupération des codes de template hébergé chez mailjet
     * @param templateMailEnum
     * @return 
     */
    private int recupererCodeTemplateMailJet(TemplateMailEnum templateMailEnum) {
        if (null != templateMailEnum) switch (templateMailEnum) {
            case CONFIRMATION_ADHESION:
                return  env.getProperty("message.template.code.confirmationadhesion", Integer.class);
            case DEMANDE_VALIDATION_MAIL:
                return  env.getProperty("message.template.code.validationmail", Integer.class);
            case INFORMATION_PRE_INSCRIPTION:
                return  env.getProperty("message.template.code.preinscription", Integer.class);
            default:
                break;
        }

        
        LOGGER.error("Aucun template mail correspondant à  {}" ,templateMailEnum.toString() );
        return 0;
    }

    /**
     * transformer les variables en object json pour appeler mailjet
     * @param templateVariables
     * @return 
     */
    private JSONObject recupererVariablePourMailjet(HashMap<String, String> templateVariables) {
        JSONObject variableJSONObject = new JSONObject();
        
        for (String i : templateVariables.keySet()) {
            variableJSONObject.put(i, templateVariables.get(i));
        }
        
        return variableJSONObject;
    }

}
