package com.pm.ecommerce.repository;

import com.pm.ecommerce.controller.Customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
