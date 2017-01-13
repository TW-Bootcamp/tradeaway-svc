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

    @Autowired
    private UserRepository repository;

    @Transactional
    public void create(User user){
        repository.save(user);
    }

    public User get(Long id){
        return repository.findOne(id);
    }
}
