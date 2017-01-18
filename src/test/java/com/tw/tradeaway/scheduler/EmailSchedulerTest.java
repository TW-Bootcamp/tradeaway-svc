package com.tw.tradeaway.scheduler;

import com.tw.tradeaway.service.email.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by vikash on 18/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {
    public static final String EMAIL = "ksvikash@thougtworks.com";
    public static final String NAME = "Vicky";
    public static final String TOKEN = "123456";



    public static final String LINK_URL = "http://localhost:8080/tradeaway/api/users/verification?token=12345&email="+EMAIL;
    public static final String SUBJECT = "Email Verification - Tradeaway";

    private FileSystemResource fileSystemResource;

    private EmailScheduler emailScheduler;
    @Mock
    private EmailService emailService;

    @Before
    public void setUp() throws Exception {
        emailScheduler = new EmailScheduler();
        ReflectionTestUtils.setField(emailScheduler, "emailService", emailService);
        ReflectionTestUtils.setField(emailService, "verficationUrl", LINK_URL);
        ReflectionTestUtils.setField(emailService, "subject", SUBJECT);
        System.out.println(new File("src/test/resources/email.template").exists());
        fileSystemResource = new FileSystemResource("src/test/resources/email.template");
        ReflectionTestUtils.setField(emailService, "emailTemplate", fileSystemResource);
        Mockito.when(emailService.sendEmail(NAME, EMAIL, TOKEN)).thenReturn(new EmailResponse(200, String.format("%s %s %s", NAME, EMAIL, TOKEN)));
    }

    @Test
    public void shouldScheduleEmailJob() throws EmailServiceException, ExecutionException, InterruptedException {
        Future<EmailResponse> emailResponseFuture = emailScheduler.scheduleJob(NAME, EMAIL, TOKEN);
        EmailResponse emailResponse = emailResponseFuture.get();
        assertThat(emailResponse).isNotNull();
        assertThat(emailResponse.getMessage()).contains(TOKEN);
        assertThat(emailResponse.getMessage()).contains(NAME);
        assertThat(emailResponse.getMessage()).contains(EMAIL);
    }
}
