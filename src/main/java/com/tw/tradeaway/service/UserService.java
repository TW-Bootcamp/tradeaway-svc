package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.Buyer;
import com.tw.tradeaway.domain.Seller;
import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.BuyerRepository;
import com.tw.tradeaway.repository.SellerRepository;
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
    private SellerRepository sellerRepository;

    @Autowired
    public UserService(UserRepository userRepository, BuyerRepository buyerRepository ,SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Transactional
    public User create(UserRequest userRequest){
        User user = new User( userRequest.getUsername(), userRequest.getPassword(),
                 userRequest.getAuthority());

        User persistedUser = userRepository.save(user);

        if ("role_buyer".equalsIgnoreCase(userRequest.getAuthority())){
            Buyer buyer = new Buyer(userRequest.getDob(), persistedUser ,userRequest.getName(), userRequest.getEmail(),userRequest.getAddress(), userRequest.getMobile());
            buyerRepository.save(buyer);
        }
        if ("role_seller".equalsIgnoreCase(userRequest.getAuthority())){

            Seller seller = new Seller(persistedUser ,  userRequest.getName(), userRequest.getEmail(),userRequest.getAddress(), userRequest.getMobile() ,userRequest.getPan(),userRequest.getExperienceInMonths());

            sellerRepository.save(seller);
        }

        return persistedUser;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean findByEmail(String email ,String authority)  {
        if ("role_buyer".equalsIgnoreCase(authority)){
            if(buyerRepository.findByEmail(email) != null){
                return true;
            }
        }
        if ("role_seller".equalsIgnoreCase(authority)){
            if(sellerRepository.findByEmail(email) != null){
                return true;
            }
        }
        return false;
    }
}
