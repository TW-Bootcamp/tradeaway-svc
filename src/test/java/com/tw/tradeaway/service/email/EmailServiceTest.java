package com.tw.tradeaway.service.email;

import com.tw.tradeaway.service.email.EmailResponse;
import com.tw.tradeaway.service.email.EmailService;
import com.tw.tradeaway.service.email.EmailServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by vikash on 17/01/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void shouldSendEmail() throws EmailServiceException {
        EmailResponse emailResponse = emailService.sendEmail( "Vijay",  "vijayv@thoughtworks.com", UUID.randomUUID().toString());
        assertThat(emailResponse).isNotNull();
        assertThat(emailResponse.getResponseCode()).isEqualTo(200);
    }
}
