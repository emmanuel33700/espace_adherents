/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.lib.mail;

import fr.espaceadh.lib.mail.dto.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @author emmanuel
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
//@ComponentScan({ "fr.espaceadh.lib.mail" })
//@ContextConfiguration(locations="classpath:config.xml")
@ContextConfiguration
  @TestPropertySource("classpath:application-dev.properties")
public class SendMailTest {
    
    @Autowired
    private GestionMail sendMail;
    
    @Autowired
    private Environment env;
    

    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SendMailTest.class);

    
    @Configuration
    @ComponentScan({"fr.espaceadh.lib.mail.impl.mailjet", })
    public static class SpringConfig {

    }


    public void testEnvoyerMailAvecTemplate(){
        MailInDto mailIn = new MailInDto();

        LOGGER.info("Login {}", env.getProperty("mailjet.login"));
        LOGGER.info("Password {}", env.getProperty("mailjet.password"));
        
        /* adresse email de destination */
        Collection<String> messageTo = new ArrayList<>();
        messageTo.add("toto.toto@gmail.com");
        mailIn.setMessageTo(messageTo);
        
        /* type de template */
        mailIn.setTemplateMailEnum(TemplateMailEnum.DEMANDE_VALIDATION_MAIL);
        
        /* variables associées au tempalte **/
         HashMap<String, String> templateVariables = new HashMap<>();
        templateVariables.put("adh_prenom", "Emmanuel");
        templateVariables.put("confirmation_link", "http://jalle-astro.fr/");
        mailIn.setTemplateVariables(templateVariables);
        
         
        /* demande d'envoie du mail */
        Collection<MailOutDto> mailsOut = sendMail.sendMail(mailIn);

        for (MailOutDto mo : mailsOut) {
            Assert.assertEquals("success", mo);
        }

    }

    @Test
    public void testEnvoyerMailSansTemplate(){


        try {
            MailInDto mailIn = new MailInDto();

            LOGGER.info("Login {}", env.getProperty("mailjet.login"));
            LOGGER.info("Password {}", env.getProperty("mailjet.password"));

            /* adresse email de destination */
            Collection<String> messageTo = new ArrayList<>();
            messageTo.add("manu.chenais@gmail.com");


            mailIn.setMessageTo(messageTo);

            /* type de template */
            mailIn.setTemplateMailEnum(null);

            /** id du message **/
            mailIn.setIdMAil("12345");


             /** sujet du message **/
            mailIn.setSujetMail("Test de manu");

            /** ajouter un message **/
            String msgHtml = "<h1> Bonjour test de main </h1>";
            mailIn.setHtmlMessage(msgHtml);

            /* ajouter des fichiers **/
            InputStreamCustom iptc1 = new InputStreamCustom();
            InputStream is1 = new ClassPathResource(
                    "templatesmails/NSwitch.pdf").getInputStream();
            iptc1.setInputStream(is1);
            iptc1.setContentType( "application/pdf");
            iptc1.setFileName( "toto1.pdf");

            InputStreamCustom iptc2 = new InputStreamCustom();
            InputStream is2 = new ClassPathResource(
                    "templatesmails/NSwitch.pdf").getInputStream();
            iptc2.setInputStream(is2);
            iptc2.setContentType( "application/pdf");
            iptc2.setFileName( "toto2.pdf");


            Collection<InputStreamCustom> lstFile = new ArrayList<>();
            lstFile.add(iptc1);
            lstFile.add(iptc2);
            mailIn.setLstFile(lstFile);

            /* demande d'envoie du mail */
            Collection<MailOutDto> mailsOut = sendMail.sendMail(mailIn);

            boolean result = true;

            for (MailOutDto mo : mailsOut) {
                if (!mo.getStatutEnvoi().equalsIgnoreCase("success")) {
                    LOGGER.error("Erreor lors de l'envoi de mail");
                    result = false;
                }
            }
            Assert.assertTrue(result);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void testStatisticMail(){
        ListeMessagesResulteDto mailOut = sendMail.recupeHistoriqueMessage("manu.chenais@gmail.com");
        System.out.println(mailOut);
    }
    
}
