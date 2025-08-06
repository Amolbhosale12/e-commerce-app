package com.pm.ecommerce.service;

import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.dto.CustomerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    ResponseEntity<ResultDTO> create(CustomerRequest customerRequest);

    ResponseEntity<ResultDTO> update(String id, CustomerRequest customerRequest);

    ResponseEntity<ResultDTO> delete(String id);

    ResponseEntity<ResultDTO> getByCustomerId(String id);

    ResponseEntity<ResultDTO> getAll();
}
