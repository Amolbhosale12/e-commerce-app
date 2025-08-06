package com.pm.ecommerce.repository;

import com.pm.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByName(String name);
}
