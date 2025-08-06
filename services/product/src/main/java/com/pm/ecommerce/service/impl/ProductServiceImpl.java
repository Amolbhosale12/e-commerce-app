package com.pm.ecommerce.service.impl;

import com.pm.ecommerce.common.Constants;
import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.common.utils.ResultBuilder;
import com.pm.ecommerce.dto.ProductRequest;
import com.pm.ecommerce.exception.CategoryNotFoundException;
import com.pm.ecommerce.model.Category;
import com.pm.ecommerce.model.Product;
import com.pm.ecommerce.repository.CategoryRepository;
import com.pm.ecommerce.repository.ProductRepository;
import com.pm.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product toProduct(ProductRequest productRequest) {

        return Product
                .builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .availableQuantity(productRequest.getAvailableQuantity())
                .price(productRequest.getPrice())
                .category(toCategory(productRequest.getCategoryId())).build();
    }

    public Category toCategory(String categoryId) {
        Category foundCategory = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(Constants.CATEGORY_NOT_FOUND));

        Category categoryCopy = new Category();
        categoryCopy.setId(foundCategory.getId());
        categoryCopy.setName(foundCategory.getName());
        categoryCopy.setDescription(foundCategory.getDescription());
        return categoryCopy;
    }


    @Override
    public ResponseEntity<ResultDTO> create(ProductRequest productRequest) {

        // check duplication
        boolean exists = productRepository.existsByName(productRequest.getName());
        log.info("exists = {}",exists);
        if (exists) {
            return ResultBuilder
                    .error(Constants.PRODUCT_ALREADY_EXISTS);
        }

        productRepository.save(toProduct(productRequest));
        return ResultBuilder.success(Constants.PRODUCT_CREATED_SUCCESSFULLY);
    }
}
