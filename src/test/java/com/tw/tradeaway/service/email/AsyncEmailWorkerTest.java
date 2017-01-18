package com.tw.tradeaway.service.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncEmailWorkerTest {

    public static final String EMAIL = "ksvikash@thougtworks.com";
    public static final String NAME = "Vicky";
    public static final String TOKEN = "123456";
    @Autowired
    private EmailService emailService;

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
