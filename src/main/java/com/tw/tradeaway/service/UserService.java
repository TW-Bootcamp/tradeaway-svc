package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.Buyer;
import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.BuyerRepository;
import com.tw.tradeaway.repository.UserRepository;
import com.tw.tradeaway.service.email.EmailService;
import com.tw.tradeaway.service.email.EmailServiceException;
import com.tw.tradeaway.service.token.UserVerificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private EmailService emailService;

    private UserVerificationTokenService tokenService;

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, BuyerRepository buyerRepository,EmailService emailService,UserVerificationTokenService tokenService) {
        this.userRepository = userRepository;
        this.buyerRepository = buyerRepository;
        this.emailService = emailService;
        this.tokenService =tokenService;
    }

    @Transactional
    public User create(UserRequest userRequest){
        User user = new User(userRequest.getName(), userRequest.getEmail(), userRequest.getUsername(), userRequest.getPassword(),
                userRequest.getAddress(), userRequest.getMobile(), userRequest.getAuthority());

        User persistedUser = userRepository.save(user);
        try {
            emailService.sendEmail(persistedUser.getName(),persistedUser.getEmail(),this.tokenService.generate(user));
        } catch (EmailServiceException e) {
            LOGGER.error("Sending email failed for username:{} , email:{} ",user.getUsername(),user.getEmail(),e);
        }
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
