package com.tw.tradeaway.service.email;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

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
        try {
            Mail mail = new Mail(new com.sendgrid.Email(from), email.getSubject(), new com.sendgrid.Email(email.getTo()), new Content("text/html", email.getText()));
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            EmailResponse emailResponse = new EmailResponse();
            emailResponse.setResponseCode(response.statusCode == 202 ? 200 : response.statusCode);
            emailResponse.setMessage(response.body);
            return emailResponse;
        } catch (IOException ex) {
            throw new EmailServiceException(ex);
        }
    }

    @Bean
    private SendGrid sendGrid() {
        sg = new SendGrid(apiSecretKey);
        return sg;
    }

}
