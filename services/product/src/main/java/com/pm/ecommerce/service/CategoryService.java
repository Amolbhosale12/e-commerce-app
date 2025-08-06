package com.pm.ecommerce.service;

import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.dto.CategoryRequest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<ResultDTO> create(CategoryRequest categoryRequest);
    ResponseEntity<ResultDTO> update(String id,CategoryRequest categoryRequest);
    ResponseEntity<ResultDTO> delete(String id);
    ResponseEntity<ResultDTO> getAll();
    ResponseEntity<ResultDTO> getById(String id);
}
