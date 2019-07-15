/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.lib.mail;

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
import org.springframework.stereotype.Service;

/**
 *
 * @author emmanuel
 */
@Service
public class SendMailImpl implements sendMail {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SendMailImpl.class);

    @Override
    public MailOutDto sendMail(MailInDto mailIn) {


        MailjetRequest email;
        MailjetResponse response;

        // Note how we set the version to v3.1 using ClientOptions
        MailjetClient client = new MailjetClient("9ef2460a73b008943645563d43a428f8", "336e76d521102883d69465769d8cb94a", new ClientOptions("v3.1"));

        JSONObject message = new JSONObject();
        message.put(Emailv31.Message.FROM, new JSONObject()
                .put(Emailv31.Message.EMAIL, "manu.chenais@gmail.com")
                .put(Emailv31.Message.NAME, "Mailjet Pilot")
        )
                .put(Emailv31.Message.SUBJECT, "Your email flight plan!")
                .put(Emailv31.Message.TEXTPART, "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
                .put(Emailv31.Message.HTMLPART, "<h3>Dear passenger, welcome to Mailjet</h3><br/>May the delivery force be with you!")
                .put(Emailv31.Message.TO, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.EMAIL, "manu.chenais@gmail.com")));

        email = new MailjetRequest(Emailv31.resource).property(Emailv31.MESSAGES, (new JSONArray()).put(message));

        try {
            response = client.post(email);
            LOGGER.info(response.getData().toString());
            System.out.println(response.getData().toString());
            
        } catch (MailjetException | MailjetSocketTimeoutException ex) {
            LOGGER.error(ex.getMessage());
        }

        return new MailOutDto();
    }

}
