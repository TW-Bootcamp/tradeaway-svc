package com.tw.tradeaway.service.email;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AsyncEmailWorkerTest {
    public static final String LINK_URL = "http://localhost:8080/tradeaway/api/users/verification?token=12345";
    public static final String SUBJECT = "Email Verification - Tradeaway";
    public static final String EMAIL = "ksvikash@thougtworks.com";
    public static final String NAME = "Vicky";
    public static final String TOKEN = "123456";

    @Mock
    private EmailService emailService;

    @Before
    public void setup() throws EmailServiceException {
        ReflectionTestUtils.setField(emailService, "verficationUrl", LINK_URL);
        ReflectionTestUtils.setField(emailService, "subject", SUBJECT);
        Mockito.when(emailService.sendEmail(NAME, EMAIL, TOKEN)).thenReturn(new EmailResponse(200, String.format("%s %s %s", NAME, EMAIL, TOKEN)));
    }


    @Test
    public void shouldSendEmailAsync() throws ExecutionException, InterruptedException {
        AsyncEmailWorker asyncEmailWorker = new AsyncEmailWorker(emailService);
        asyncEmailWorker.setEmail(EMAIL);
        asyncEmailWorker.setUserName(NAME);
        asyncEmailWorker.setToken(TOKEN);
        Future<EmailResponse> responseFuture = Executors.newSingleThreadExecutor().submit(asyncEmailWorker);
        EmailResponse emailResponse = responseFuture.get();
        assertThat(emailResponse).isNotNull();
        assertThat(emailResponse.getMessage()).contains(EMAIL);
        assertThat(emailResponse.getMessage()).contains(NAME);
        assertThat(emailResponse.getMessage()).contains(TOKEN);
    }
}
