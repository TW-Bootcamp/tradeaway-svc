package com.tw.tradeaway.service.email;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by vikash on 17/01/17.
 */
@Component
public class SendGridEmailServiceProvider implements EmailServiceProvider {

    private SendGrid sg;
    @Value("classpath:client_secret.json")
    private Resource secretJson;
    @Value("${email.from}")
    private String from;
    @Value("${email.api.secret}")
    private String apiSecretKey;

    public SendGridEmailServiceProvider() {

    }

    @Override
    public EmailResponse sendMessage(Email email) throws EmailServiceException {
        Request request = new Request();
        EmailResponse emailResponse = new EmailResponse();
        emailResponse.setResponseCode(200);
        emailResponse.setMessage(String.format("name %s, to %s, text %s", email.getName(), email.getTo(), email.getText()));
        return emailResponse;
    }

    @Bean
    private SendGrid sendGrid() {
        sg = new SendGrid(apiSecretKey);
        return sg;
    }

}
