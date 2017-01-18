package com.tw.tradeaway.repository;


import com.tw.tradeaway.domain.Seller;
import com.tw.tradeaway.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by poojadhupar on 1/18/17.
 */
@Repository
public interface SellerRepository extends CrudRepository<Seller,Long> {
    public Seller findByEmail(String email);
}
