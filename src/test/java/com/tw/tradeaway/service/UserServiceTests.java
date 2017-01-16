package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rsiva on 1/16/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void getUser() throws Exception {
        User user = userService.get(1L);
        assertNotNull(user);
        assertEquals("superadmin", user.getUsername());
        assertEquals("superadmin123", user.getPassword());
    }
}
