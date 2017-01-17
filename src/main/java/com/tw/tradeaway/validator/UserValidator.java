package com.tw.tradeaway.validator;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.service.UserService;

/**
 * Created by poojadhupar on 1/17/17.
 */
public class UserValidator {
    private User user;
    private UserService service;

    public UserValidator(User user, UserService service) {
        this.user = user;
        this.service = service;
    }

    public String isUserAlreadyExists() {
        User existingUser = service.findByUsername(user.getUsername());
        if (existingUser != null) {
            return "Username already exists!!!";
        }
        existingUser = service.findByEmail(user.getEmail());
        if (existingUser != null) {
            return "Email already registered!!!";
        }
        return "";
    }
}
