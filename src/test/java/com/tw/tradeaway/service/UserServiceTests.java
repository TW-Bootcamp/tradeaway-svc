package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.BuyerRepository;
import com.tw.tradeaway.repository.UserRepository;
import com.tw.tradeaway.request.UserRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by rsiva on 1/16/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BuyerRepository buyerRepository;

    private UserRequest userRequest;

    private User user;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository, buyerRepository);

        userRequest = new UserRequest();
        user = new User();

        userRequest.setUsername("test");
        userRequest.setEmail("test@email.com");
        userRequest.setName("test");
        userRequest.setAddress("test Pune");
        userRequest.setMobile("1234567890");
        userRequest.setPassword("test123");
        userRequest.setAuthority("role_buyer");

        //user.setId();
        user.setEmail_verified(false);
        user.setUsername(userRequest.getUsername());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setMobile(userRequest.getMobile());
        user.setPassword(userRequest.getPassword());
        user.setAuthority(userRequest.getAuthority());

    }

    @Test
    public void shouldInvokeRepositoryMethod() throws Exception {
        userService.create(userRequest);

        verify(userRepository, times(1)).save(user);
    }
}
