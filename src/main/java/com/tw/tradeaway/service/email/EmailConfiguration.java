package com.tw.tradeaway.service.email;

/**
 * Created by vikash on 17/01/17.
 */
public class EmailConfiguration {
    private String from;
    private String text;
    private String textTemplate;

    public void setTextTemplate(String textTemplate) {
        this.textTemplate = textTemplate;
    }

    public String getTextTemplate() {
        return textTemplate;
    }
}
