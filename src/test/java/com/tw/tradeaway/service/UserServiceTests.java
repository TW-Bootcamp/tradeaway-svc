package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.Buyer;
import com.tw.tradeaway.domain.Seller;
import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.BuyerRepository;
import com.tw.tradeaway.repository.SellerRepository;
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

    @Mock
    private SellerRepository sellerRepository;

    private UserRequest userRequest;

    private User user;
    private Buyer buyer;
    private Seller seller;
    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository, buyerRepository ,sellerRepository);

        userRequest = new UserRequest();
        user = new User();
        buyer = new Buyer();
        seller = new Seller();
        userRequest.setUsername("test");
        userRequest.setEmail("test@email.com");
        userRequest.setName("test");
        userRequest.setAddress("test Pune");
        userRequest.setMobile("1234567890");
        userRequest.setPassword("test123");
        userRequest.setGender('M');
        userRequest.setAuthority("role_buyer");

        user.setEmail_verified(false);
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setAuthority(userRequest.getAuthority());

        buyer.setEmail(userRequest.getEmail());
        buyer.setName(userRequest.getName());
        buyer.setAddress(userRequest.getAddress());
        buyer.setMobile(userRequest.getMobile());
        buyer.setGender(userRequest.getGender());
    }

    @Test
    public void shouldInvokeRepositoryMethod() throws Exception {
        userService.create(userRequest);

        verify(userRepository, times(1)).save(user);
    }
/*
    @Test
    public void shouldInvokeBuyerRepositoryMethod() throws Exception {
        userService.create(userRequest);

        verify(userRepository, times(1)).save(user);
       // verify(buyerRepository, times(1)).save(buyer);
    }
   */
}
