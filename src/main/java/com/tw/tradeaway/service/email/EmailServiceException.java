package com.tw.tradeaway.service.email;

import java.io.IOException;

/**
 * Created by vikash on 17/01/17.
 */
public class EmailServiceException extends Exception {
    public EmailServiceException(IOException ex) {
        super(ex);
    }
}
