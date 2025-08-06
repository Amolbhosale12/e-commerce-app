package com.pm.ecommerce.controller;

import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.dto.ProductRequest;
import com.pm.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ResultDTO> create(@Valid @RequestBody ProductRequest productRequest) {
        log.info("Received product creating request {}", productRequest);
        return productService.create(productRequest);
    }

}
