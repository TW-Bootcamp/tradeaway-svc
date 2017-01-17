package com.tw.tradeaway.controller;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.response.UserResponse;
import com.tw.tradeaway.service.UserService;
import com.tw.tradeaway.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by poojadhupar on 1/16/17.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/home")
    public String home(){
        Logger logger = LoggerFactory.getLogger(HomeController.class);
        logger.error("we will explain");
        return "bootcamp";
    }

    @RequestMapping(method = RequestMethod.POST,  value="/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody @Valid User user){
        UserValidator validator = new UserValidator(user, service);
        String validatorRes = validator.isUserAlreadyExists();
        if (!validatorRes.isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<String, Object>();
            errorResponse.put("errorMessage", validatorRes);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> successResponse = new HashMap<String, Object>();
        User createdUser = service.create(user);
        UserResponse response = new UserResponse(createdUser.getUsername(), createdUser.getName(),
                createdUser.getEmail(), createdUser.getType());
        successResponse.put("user", response);
        return new ResponseEntity(successResponse, HttpStatus.OK);
    }
}

