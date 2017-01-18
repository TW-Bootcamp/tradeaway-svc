package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.UserRepository;
import com.tw.tradeaway.service.email.EmailService;
import com.tw.tradeaway.service.email.EmailServiceException;
import com.tw.tradeaway.service.token.UserVerificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by prateeks on 1/13/17.
 */
@Service
public class UserService {

    private UserRepository repository;

    private EmailService emailService;

    private UserVerificationTokenService tokenGenerator;

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    public UserService(UserRepository repository, EmailService emailService, UserVerificationTokenService tokenGenerator) {
        this.repository = repository;
        this.emailService = emailService;
        this.tokenGenerator =tokenGenerator;
    }

    @Transactional
    public User create(User user)
    {
        User persistedUser =  repository.save(user);
        try {
            emailService.sendEmail(persistedUser.getName(),persistedUser.getEmail(),this.tokenGenerator.generate(user));
        } catch (EmailServiceException e) {
            LOGGER.error("Sending email failed for username:{} , email:{} ",user.getUsername(),user.getEmail(),e);
        }
        return  persistedUser;
    }

    public User findByUsername(String username){
        return repository.findByUsername(username);
    }

    public User findByEmail(String email){
        return repository.findByEmail(email);
    }
}
