package com.tw.tradeaway.repository;

import com.tw.tradeaway.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by prateeks on 1/13/17.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    public User findByUsername(String username);
    public User findByEmail(String email);
}
