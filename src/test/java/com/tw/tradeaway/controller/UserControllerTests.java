package com.tw.tradeaway.controller;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.request.UserRequest;
import com.tw.tradeaway.response.ErrorResponse;
import com.tw.tradeaway.response.UserResponse;
import com.tw.tradeaway.service.UserService;
import com.tw.tradeaway.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by poojadhupar on 1/16/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    private UserController userController;

    @Mock
    private UserRequest userRequest;

    @Mock
    private UserService userService;

    @Mock
    private UserValidator validator;

    @Before
    public void setUp() throws Exception {
        userController = new UserController(userService, validator);
    }

    @Test
    public void verifySuccessResponseIfUserDoesNotExist() throws Exception {
        when(validator.isExistingUser()).thenReturn(false);
        User createdUser = new User();
        when(userService.create(userRequest)).thenReturn(createdUser);

        UserResponse response = new UserResponse(createdUser.getUsername(),  createdUser.getAuthority());
        Map<String, Object> successResponse = new HashMap<String, Object>();
        successResponse.put("user", response);

        ResponseEntity expectedResponseEntity = new ResponseEntity(successResponse, HttpStatus.OK);

        ResponseEntity responseEntity = userController.createUser(userRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(expectedResponseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getClass()).isEqualTo(expectedResponseEntity.getBody().getClass());

        verify(userService, times(1)).create(userRequest);
    }

    @Test
    public void verifyErrorResponseIfUserExists() throws Exception {
        UserRequest userRequest = new UserRequest();
        when(validator.isExistingUser()).thenReturn(true);

        ResponseEntity expectedResponseEntity = new ResponseEntity(new ErrorResponse("User already exists", ""+HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);

        ResponseEntity responseEntity = userController.createUser(userRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(expectedResponseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getClass()).isEqualTo(expectedResponseEntity.getBody().getClass());

        verify(userService, times(0)).create(userRequest);
    }
}
