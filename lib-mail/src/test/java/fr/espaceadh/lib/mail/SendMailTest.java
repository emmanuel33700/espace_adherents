/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.lib.mail;

import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;
import fr.espaceadh.lib.mail.dto.TemplateMailEnum;
import java.io.StringWriter;
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
    
    @Test
    public void testEnvoyerMail(){
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
        MailOutDto mailOut = sendMail.sendMail(mailIn);
        
        
        
        Assert.assertEquals("success", mailOut.getStatutEnvoi());
        
    }


    
}
