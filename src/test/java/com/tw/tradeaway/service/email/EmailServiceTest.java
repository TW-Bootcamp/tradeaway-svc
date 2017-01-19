package com.tw.tradeaway.service.email;

import org.apache.commons.io.IOUtils;
import org.hibernate.property.access.spi.Setter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by vikash on 17/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    public static final String LINK_URL = "http://localhost:8080/tradeaway/api/users/verification?token=12345";
    public static final String SUBJECT = "Email Verification - Tradeaway";
    //    @Inject
    private EmailService emailService;

     @Mock
     private SendGridEmailServiceProvider sendGridEmailServiceProvider;
    private FileSystemResource fileSystemResource;


    @Before
    public void setUp() throws Exception {
        emailService = new EmailService(sendGridEmailServiceProvider);
        ReflectionTestUtils.setField(emailService, "verficationUrl", LINK_URL);
        ReflectionTestUtils.setField(emailService, "subject", SUBJECT);
        System.out.println(new File("src/test/resources/email.template").exists());
        fileSystemResource = new FileSystemResource("src/test/resources/email.template");
        ReflectionTestUtils.setField(emailService, "emailTemplate", fileSystemResource);
    }

    @Test
    public void shouldSendEmail() throws EmailServiceException, IOException {
        String template = IOUtils.toString(fileSystemResource.getInputStream(), "UTF-8");
        System.out.println(template);
        template = String.format(template, "Vikash", LINK_URL);
        Mockito.when(
                sendGridEmailServiceProvider.sendMessage(new Email("Vikash", "ksvikash@thoughtworks.com", SUBJECT, template)))
                .thenReturn(new EmailResponse(200, "message success"));
        EmailResponse emailResponse = emailService.sendEmail("Vikash", "ksvikash@thoughtworks.com", "12345");
        System.out.println("email response is: " + emailResponse);
        assertThat(emailResponse).isNotNull();
        assertThat(emailResponse.getResponseCode()).isEqualTo(200);
    }
}
