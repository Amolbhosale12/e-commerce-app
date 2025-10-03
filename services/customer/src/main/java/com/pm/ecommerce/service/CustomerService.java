package com.pm.ecommerce.service;

import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.dto.CustomerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    ResultDTO create(CustomerRequest customerRequest);

    ResultDTO update(String id, CustomerRequest customerRequest);

    ResultDTO delete(String id);

    ResultDTO getByCustomerId(String id);

    ResultDTO getAll();
}
