package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.Buyer;
import com.tw.tradeaway.domain.Seller;
import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.BuyerRepository;
import com.tw.tradeaway.repository.SellerRepository;
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
    private SellerRepository sellerRepository;

    private EmailService emailService;

    private UserVerificationTokenService tokenService;

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, BuyerRepository buyerRepository,SellerRepository sellerRepository ,EmailService emailService,UserVerificationTokenService tokenService) {
        this.userRepository = userRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.emailService = emailService;
        this.tokenService =tokenService;
    }

    @Transactional
    public User create(UserRequest userRequest) throws Exception {
        User user = new User( userRequest.getUsername(), userRequest.getPassword(),userRequest.getAuthority());
        System.out.println(user.getUsername());
        User persistedUser = userRepository.save(user);


        if ("role_buyer".equalsIgnoreCase(userRequest.getAuthority())){
            System.out.println(userRequest.getDob());
            Buyer buyer = new Buyer(userRequest.getDob(), persistedUser ,userRequest.getName() ,userRequest.getEmail() ,userRequest.getAddress() ,userRequest.getMobile());
            buyer.setGender(userRequest.getGender());
            System.out.println(buyer.getUser());
            buyerRepository.save(buyer);

        }
        else if ("role_seller".equalsIgnoreCase(userRequest.getAuthority())){

            Seller seller = new Seller(persistedUser ,  userRequest.getName(), userRequest.getEmail(),userRequest.getAddress(), userRequest.getMobile() ,userRequest.getPan(),userRequest.getExperienceInMonths());
            sellerRepository.save(seller);

        }
        try {
            emailService.sendEmail(userRequest.getName(),userRequest.getEmail(),this.tokenService.generate(persistedUser));
        } catch (EmailServiceException e) {
            LOGGER.error("Sending email failed for username:{} , email:{} ",user.getUsername(),userRequest.getEmail(),e);
        }
        return persistedUser;
    }

    @Transactional
    public User verifyEmail(String username){
        User user = this.findByUsername(username);
        user.setEmail_verified(true);
        return  userRepository.save(user);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email , String authority)  {

        if ("role_buyer".equalsIgnoreCase(authority)){
            Buyer buyer= buyerRepository.findByEmail(email);
            System.out.println(buyer);
            if( buyer != null){
                return buyer.getUser();
            }
        }
        else if ("role_seller".equalsIgnoreCase(authority)){
            Seller seller= sellerRepository.findByEmail(email);
            if( seller != null){
                return seller.getUser();
            }

        }
        return null;
    }
}
