package com.tw.tradeaway.controller;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by poojadhupar on 1/16/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userController = new UserController(userService);
    }

    @Test
    public void shouldInvokeServiceCreateMethod(){
        User user = new User();
        when(userService.create(user)).thenReturn(user);
        userController.createUser(user);

        verify(userService, times(1)).create(user);
    }
}
