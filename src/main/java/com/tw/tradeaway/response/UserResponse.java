package com.tw.tradeaway.response;

/**
 * Created by poojadhupar on 1/17/17.
 */
public class UserResponse {

    private String fullname;
    private String email;
    private String username;
    private String type;

    public UserResponse(String username, String fullname, String email, String type) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
