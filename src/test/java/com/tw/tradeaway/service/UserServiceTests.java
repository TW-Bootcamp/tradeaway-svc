package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.UserRepository;
import com.tw.tradeaway.service.email.EmailService;
import com.tw.tradeaway.service.token.UserVerificationTokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by rsiva on 1/16/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository repository;

    @Mock
    private EmailService emailService;

    @Mock
    private UserVerificationTokenService tokenService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(repository,emailService,tokenService);
    }

    @Test
    public void shouldInvokeRepositoryMethod() throws Exception {
        User user = new User();
        when(emailService.sendEmail(user.getName(),user.getEmail(),"token")).thenReturn(null);
        when(repository.save(user)).thenReturn(new User());
        userService.create(user);

        verify(repository, times(1)).save(user);
    }
}
