package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by prateeks on 1/13/17.
 */
@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User create(User user){
        return repository.save(user);
    }

    public User findByUsername(String username){
        return repository.findByUsername(username);
    }

    public User findByEmail(String email){
        return repository.findByEmail(email);
    }
}
