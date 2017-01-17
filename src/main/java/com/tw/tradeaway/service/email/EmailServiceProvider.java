package com.tw.tradeaway.service.email;

/**
 * Created by vikash on 17/01/17.
 */
public interface EmailServiceProvider {
    public EmailResponse sendMessage(Email email) throws EmailServiceException;
}
