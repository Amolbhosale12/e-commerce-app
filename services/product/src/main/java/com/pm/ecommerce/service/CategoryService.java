package com.pm.ecommerce.service;

import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.dto.CategoryRequest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResultDTO create(CategoryRequest categoryRequest);
    ResultDTO update(String id,CategoryRequest categoryRequest);
    ResultDTO delete(String id);
    ResultDTO getAll();
    ResultDTO getById(String id);
}
