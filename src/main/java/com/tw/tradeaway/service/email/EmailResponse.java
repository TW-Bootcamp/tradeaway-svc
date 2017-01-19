package com.tw.tradeaway.service.email;

/**
 * Created by vikash on 17/01/17.
 */
public class EmailResponse {
    private String id;
    private String message;
    private int responseCode;

    public EmailResponse() {
    }

    @Override
    public int hashCode() {
        int result = message.hashCode();
        result = 31 * result + responseCode;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        EmailResponse response = (EmailResponse) obj;
        return response.getMessage().equals(message) && response.getResponseCode() == responseCode;
    }

    @Override
    public String toString() {
        return String.format("[EmailResponse] responseCode %s, message %s", responseCode, message);
    }

    public EmailResponse(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
