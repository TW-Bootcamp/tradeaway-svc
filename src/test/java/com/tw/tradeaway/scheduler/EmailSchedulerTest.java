package com.tw.tradeaway.scheduler;

import com.tw.tradeaway.service.email.EmailResponse;
import com.tw.tradeaway.service.email.EmailService;
import com.tw.tradeaway.service.email.EmailServiceException;
import junit.textui.TestRunner;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by vikash on 18/01/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSchedulerTest {
    public static final String EMAIL = "ksvikash@thougtworks.com";
    public static final String NAME = "Vicky";
    public static final String TOKEN = "123456";

    @Autowired
    private EmailScheduler emailScheduler;

    @Test
    public void shouldScheduleEmailJob() throws EmailServiceException, ExecutionException, InterruptedException {
        Future<EmailResponse> emailResponseFuture = emailScheduler.scheduleJob(NAME, EMAIL, TOKEN);
        EmailResponse emailResponse = emailResponseFuture.get();
        assertThat(emailResponse).isNotNull();
        assertThat(emailResponse.getMessage()).contains(TOKEN);
        assertThat(emailResponse.getMessage()).contains(NAME);
        assertThat(emailResponse.getMessage()).contains(EMAIL);
    }

    @Test
    public void shouldScheduleMultipleEmailJob() throws EmailServiceException, ExecutionException, InterruptedException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        emailScheduler.scheduleJob(NAME, EMAIL, TOKEN);
        String token1 = UUID.randomUUID().toString();
        emailScheduler.scheduleJob("Vijay", EMAIL, token1);
        String token2 = UUID.randomUUID().toString();
        emailScheduler.scheduleJob("Verma", EMAIL, token2);
        TimeUnit.SECONDS.sleep(2);
        byte[] obj = byteArrayOutputStream.toByteArray();
        String tempString = new String(obj);
        String[] results = tempString.split("\n");

        Map<String, String> resultMap = Arrays.stream(results).map((elem) -> elem.split(" token "))
                .collect(Collectors.toMap(e -> e[1].trim(), e -> e[0]));
        assertThat(resultMap.get(TOKEN)).isEqualToIgnoringCase("responseCode 200, name Vicky, email ksvikash@thougtworks.com,");
        assertThat(resultMap.get(token1)).isEqualToIgnoringCase("responseCode 200, name Vijay, email ksvikash@thougtworks.com,");
        assertThat(resultMap.get(token2)).isEqualToIgnoringCase("responseCode 200, name Verma, email ksvikash@thougtworks.com,");

//        assertThat(tempString).contains("[EmailResponse] responseCode 200, message name Vicky, email ksvikash@thougtworks.com, token 123456\n"+
//                "[EmailResponse] responseCode 200, message name Vijay, email ksvikash@thougtworks.com, token "+token1+"\n"+
//                "[EmailResponse] responseCode 200, message name Verma, email ksvikash@thougtworks.com, token "+token2+"\n");
    }
}
