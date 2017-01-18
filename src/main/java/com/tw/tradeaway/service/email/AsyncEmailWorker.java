package com.tw.tradeaway.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * Created by vikash on 18/01/17.
 */
public class AsyncEmailWorker implements Callable<EmailResponse> {

    private EmailService emailService;

    private String userName;
    private String token;
    private String email;

    public AsyncEmailWorker(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public EmailResponse call() throws Exception {
        EmailResponse emailResponse = emailService.sendEmail(userName, email, token);
        return emailResponse;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
