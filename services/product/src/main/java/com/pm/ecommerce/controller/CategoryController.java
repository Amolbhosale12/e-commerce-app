package com.pm.ecommerce.controller;

import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.dto.CategoryRequest;
import com.pm.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ResultDTO> create(@Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.create(categoryRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> get(@PathVariable String id) {
        return categoryService.getById(id);
    }
    @GetMapping
    public ResponseEntity<ResultDTO> getAll(){
        return categoryService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO> update(@PathVariable String id, @Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.update(id, categoryRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultDTO> delete(@PathVariable String id) {
        return categoryService.delete(id);
    }


}
