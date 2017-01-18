package com.tw.tradeaway.repository;

import com.tw.tradeaway.domain.Buyer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by poojadhupar on 1/18/17.
 */
@Repository
public interface BuyerRepository extends CrudRepository<Buyer,Long> {
}
