package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.UserRepository;
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
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(repository);
    }

    @Test
    public void shouldInvokeRepositoryMethod() throws Exception {
        User user = new User();

        userService.create(user);

        verify(repository, times(1)).save(user);
    }
}
