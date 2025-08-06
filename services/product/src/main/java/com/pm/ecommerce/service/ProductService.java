package com.pm.ecommerce.service;

import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.dto.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ResponseEntity<ResultDTO> create(ProductRequest productRequest);
}
