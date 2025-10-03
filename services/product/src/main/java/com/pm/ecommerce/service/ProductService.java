package com.pm.ecommerce.service;

import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.dto.ProductPurchaseRequest;
import com.pm.ecommerce.dto.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ResultDTO create(ProductRequest productRequest);
    ResultDTO purchaseProducts(List<ProductPurchaseRequest> ProductPurchaseRequest);
    ResultDTO findById(String productId);
}
