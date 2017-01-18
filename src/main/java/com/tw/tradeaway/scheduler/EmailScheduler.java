package com.tw.tradeaway.scheduler;

import com.tw.tradeaway.service.email.AsyncEmailWorker;
import com.tw.tradeaway.service.email.EmailResponse;
import com.tw.tradeaway.service.email.EmailService;
import com.tw.tradeaway.service.email.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * Created by vikash on 18/01/17.
 */
@Component
public class EmailScheduler {


    private static final int CAPACITY = 500;
    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    @Autowired
    private EmailService emailService;
    private ExecutorService executor;


    public EmailScheduler() {
        executor = Executors.newFixedThreadPool(POOL_SIZE);
    }

    public Future<EmailResponse> scheduleJob(String userName, String emailId, String token) throws EmailServiceException {
        AsyncEmailWorker emailWorker = getEmailWorker();
        emailWorker.setUserName(userName);
        emailWorker.setEmail(emailId);
        emailWorker.setToken(token);
        Future<EmailResponse> response = executor.submit(emailWorker);
        return response;
    }

    @Autowired
    public AsyncEmailWorker getEmailWorker() {
        return new AsyncEmailWorker(emailService);
    }

}
