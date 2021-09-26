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

import fr.espaceadh.lib.mail.dto.*;
import fr.espaceadh.lib.mail.utilitaires.JSON;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import fr.espaceadh.lib.mail.GestionMail;
import fr.espaceadh.lib.mail.model.mailjet.ListeMessages;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
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
        if (mailIn.getTemplateMailEnum() != null && mailIn.getTemplateMailEnum() != TemplateMailEnum.SANS_TEMPLATE){
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


        MailjetRequest request = null;
        try {
            JSONArray jSONArrayAttachement = new JSONArray();
            if (!mailIn.getLstFile().isEmpty()) {
                for (InputStreamCustom inptureStream : mailIn.getLstFile()) {
                    byte[] filecontent = this.readAllBytes(inptureStream.getInputStream());
                    String fileData = com.mailjet.client.Base64.encode(filecontent);
                    jSONArrayAttachement.put(new JSONObject().put("ContentType", inptureStream.getContentType())
                            .put("Filename", inptureStream.getFileName())
                            .put("Base64Content", fileData));
                }
            }
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", env.getProperty("message.from.mail"))
                                        .put("Name", env.getProperty("message.from.name")))
                                .put(Emailv31.Message.TO, emailto)
                                .put(Emailv31.Message.TEMPLATELANGUAGE, false)
                                .put(Emailv31.Message.HTMLPART, mailIn.getHtmlMessage())
                                .put(Emailv31.Message.SUBJECT, mailIn.getSujetMail())
                                .put(Emailv31.Message.ATTACHMENTS,jSONArrayAttachement)
                        ));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;


    }


    /**
     * Transfoer un InputStream en byte
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static byte[] readAllBytes(InputStream inputStream) throws IOException {
        final int bufLen = 4 * 0x400; // 4KB
        byte[] buf = new byte[bufLen];
        int readLen;
        IOException exception = null;

        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
                    outputStream.write(buf, 0, readLen);

                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            exception = e;
            throw e;
        } finally {
            if (exception == null) inputStream.close();
            else try {
                inputStream.close();
            } catch (IOException e) {
                exception.addSuppressed(e);
            }
        }
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
            case INFORMATION_EVENEMENT_AJOUTE:
                return  env.getProperty("message.template.code.evenement.ajoute", Integer.class);    
            default:
                break;
        }

        
        LOGGER.info("Aucun template mail récupéré"  );
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



    /**
     * Recupérer l'historique des message envouyé à une personne (via son mail)
     * @param mail
     * @return 
     */
    @Override
    public ListeMessagesResulteDto recupeHistoriqueMessage(String mail) {
        ListeMessages lstMessages = null;
        try {
           StringBuilder urlString = new StringBuilder("https://api.mailjet.com/v3/REST/message?ContactAlt=");
           urlString.append(mail);
           urlString.append("&ShowSubject=true");
           
           /** encodage basic authentitication **/
           StringBuilder authBasic = new StringBuilder( env.getProperty("mailjet.login"));
           authBasic.append(":");
           authBasic.append(env.getProperty("mailjet.password"));
           
           StringBuilder authBasicB64 = new StringBuilder("Basic ");
           authBasicB64.append(Base64.getEncoder().encodeToString(authBasic.toString().getBytes()));
            /** fin encodage basic authentitication **/
           
           URL url = new URL(urlString.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Authorization", authBasicB64.toString());
            int status = con.getResponseCode();
            
            LOGGER.info("code Statut api {}",status);
            
            
            
            BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            
            LOGGER.info("Reponse {}", content.toString());

            con.disconnect();
            
            JSON json = new JSON();
            lstMessages = json.deserialize(content.toString(), ListeMessages.class);
            
            LOGGER.debug(lstMessages.toString());
            
        } catch (MalformedURLException ex) {
            LOGGER.error("MalformedURLException" + ex.getMessage());
        } catch (IOException ex) {
            LOGGER.error("IOException" + ex.getMessage());
        }

       return this.convertDto(lstMessages);
    }

    /**
     * Transforme LstMessages Model en ListeMessagesResulteDto
     * @param lstMessages
     * @return 
     */
    private ListeMessagesResulteDto convertDto(ListeMessages lstMessages) {
        ListeMessagesResulteDto listeDto = new ListeMessagesResulteDto();
        Collection<MessageResultDto> lstMessageResultDto = new ArrayList<>();
        
        for (fr.espaceadh.lib.mail.model.mailjet.Message msg : lstMessages.getData()){
            MessageResultDto msgDto = new MessageResultDto();
            msgDto.setId(msg.getID());
            msgDto.setMailDestinataire(msg.getContactAlt());
            msgDto.setRegleSpam(msg.getSpamassRules());
            msgDto.setScoreSpam(msg.getSpamassassinScore());
            msgDto.setStatut(msg.getStatus().getValue());
            msgDto.setSujetMail(msg.getSubject());
            if (msg.getStateID() != null) msgDto.setTypeErreur(msg.getStateID());
            msgDto.setUUID(msg.getUUID());
            
            if (msg.getArrivedAt() != null) {
                long epochMilli = msg.getArrivedAt().toInstant().toEpochMilli();
                Date date = new Date(epochMilli); 
                msgDto.setDateArrive(date);
            }

            lstMessageResultDto.add(msgDto);
        }
        listeDto.setLstMessageResulteDto(lstMessageResultDto);
        return listeDto;
    }

}
