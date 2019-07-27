/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.espaceadh.lib.mail;

import fr.espaceadh.lib.mail.dto.MailInDto;
import fr.espaceadh.lib.mail.dto.MailOutDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @author emmanuel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations="classpath:config.xml")
@PropertySource("classpath:application-dev.properties")
public class SendMailTest {
    
    @Test
    public void testEnvoyerMail(){
        MailInDto mailIn = new MailInDto();
        sendMail sendMail = new SendMailImpl();
        MailOutDto mailOut = sendMail.sendMail(mailIn);
        
        Assert.assertNotNull(mailOut);
        
    }
    
}
