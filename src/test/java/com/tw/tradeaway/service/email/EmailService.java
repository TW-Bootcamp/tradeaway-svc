package com.tw.tradeaway.service.email;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by vikash on 17/01/17.
 */
@Service
@Qualifier("EmailService")
public class EmailService {


    private String messageText;

    @Value("${email.subject}")
    private String subject;
    @Value("${email.verification.url}")
    private String verficationUrl;
    @Value("classpath:email.template")
    private Resource emailTemplate;

    private EmailServiceProvider emailServiceProvider;

    @Autowired
    public EmailService(EmailServiceProvider emailServiceProvider) {
        this.emailServiceProvider = emailServiceProvider;
        messageText = null;
    }

    public EmailResponse sendEmail(String name, String toEmail, String verificationToken) throws EmailServiceException {
        EmailResponse emailResponse = new EmailResponse(200, String.format("name %s, email %s, token %s", name, toEmail, verificationToken));
        System.out.println(emailResponse);
        return emailResponse;
    }

    private String getText(String name, String verificationToken, String textTemplate) {
        return String.format(textTemplate, name, String.format(verficationUrl, verificationToken));
    }

    @Bean
    private String getMessageText() {
        try {
            InputStream inputStream = emailTemplate.getInputStream();
            messageText = IOUtils.toString(inputStream, "UTF-8");
            return messageText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
