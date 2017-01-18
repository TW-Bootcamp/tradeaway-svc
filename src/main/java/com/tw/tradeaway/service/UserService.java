package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.Buyer;
import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.BuyerRepository;
import com.tw.tradeaway.repository.UserRepository;
import com.tw.tradeaway.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by prateeks on 1/13/17.
 */
@Service
public class UserService {

    private UserRepository userRepository;
    private BuyerRepository buyerRepository;

    @Autowired
    public UserService(UserRepository userRepository, BuyerRepository buyerRepository) {
        this.userRepository = userRepository;
        this.buyerRepository = buyerRepository;
    }

    @Transactional
    public User create(UserRequest userRequest){
        User user = new User(userRequest.getName(), userRequest.getEmail(), userRequest.getUsername(), userRequest.getPassword(),
                userRequest.getAddress(), userRequest.getMobile(), userRequest.getAuthority());

        User persistedUser = userRepository.save(user);

        if ("role_buyer".equalsIgnoreCase(userRequest.getAuthority())){
            Buyer buyer = new Buyer(userRequest.getGender(), userRequest.getDob(), persistedUser);
            buyerRepository.save(buyer);
        }

        return persistedUser;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
