package com.tw.tradeaway.validator;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.service.UserService;
import org.springframework.stereotype.Component;

/**
 * Created by poojadhupar on 1/17/17.
 */
@Component
public class UserValidator {
    private User user;
    private UserService service;
    private String errorMessage;

    public void setUser(User user) {
        this.user = user;
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public boolean isExistingUser() {
        if (isExistingUsername()) {
            errorMessage = "Username already Exists.!!!";
            return true;
        }

        if (isExistingEmail()){
            errorMessage = "Email already registered.!!!";
            return true;
        }

        return false;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private boolean isExistingUsername() {
        User existingUser = service.findByUsername(user.getUsername());
        if (existingUser != null) {
            return true;
        }
        return false;
    }

    private boolean isExistingEmail() {
        User existingUser = service.findByEmail(user.getEmail());
        if (existingUser != null) {
            return true;
        }
        return false;
    }
}
