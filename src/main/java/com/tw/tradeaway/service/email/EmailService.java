package com.tw.tradeaway.service.email;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        Email email = new Email(name, toEmail, subject, getText(name, toEmail, verificationToken, getMessageText()));
        EmailResponse emailResponse = emailServiceProvider.sendMessage(email);
        return emailResponse;
    }

    private String getText(String name, String email, String verificationToken, String textTemplate) {
        String formLinkUrl = formLinkUrl(email, verificationToken);
        String formatedMessageText = String.format(textTemplate, name, formLinkUrl);
        System.out.println(formatedMessageText);
        return formatedMessageText;
    }

    private String formLinkUrl(String email, String verificationToken) {
        return String.format(verficationUrl, verificationToken, email);
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
