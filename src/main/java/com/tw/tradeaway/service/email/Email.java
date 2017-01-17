package com.tw.tradeaway.service.email;

/**
 * Created by vikash on 17/01/17.
 */
public class Email {
    private String name;
    private String to;
    private String subject;
    private String text;

    public Email(String name, String to, String subject, String text) {
        this.name = name;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
