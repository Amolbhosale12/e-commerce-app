package com.pm.ecommerce.controller;

import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.dto.ProductPurchaseRequest;
import com.pm.ecommerce.dto.ProductRequest;
import com.pm.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
    public ResponseEntity<ResultDTO> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        log.info("Received product creating request {}", productRequest);
        return ResponseEntity.ok(productService.create(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<ResultDTO> purchaseProducts(@Valid @RequestBody List<ProductPurchaseRequest> ProductPurchaseRequest) {
//        log.info("Received product creating request {}", productRequest);
        return ResponseEntity.ok(productService.purchaseProducts(ProductPurchaseRequest));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ResultDTO> getProductById(@PathVariable("product-id") String productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }
}
