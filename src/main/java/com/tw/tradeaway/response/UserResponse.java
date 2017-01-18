package com.tw.tradeaway.response;

/**
 * Created by poojadhupar on 1/17/17.
 */
public class UserResponse {

    private String username;
    private String type;

    public UserResponse(String username, String type) {
        this.username = username;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
