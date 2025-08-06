package com.pm.ecommerce.repository;

import com.pm.ecommerce.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Boolean existsByEmail(String email);
}
