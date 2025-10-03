package com.pm.ecommerce.service.impl;

import com.pm.ecommerce.common.Constants;
import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.common.utils.ResultBuilder;
import com.pm.ecommerce.dto.CategoryRequest;
import com.pm.ecommerce.dto.CategoryResponse;
import com.pm.ecommerce.exception.CategoryNotFoundException;
import com.pm.ecommerce.model.Category;
import com.pm.ecommerce.repository.CategoryRepository;
import com.pm.ecommerce.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static CategoryResponse toCategoryResponse(Category category) {

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        return categoryResponse;
    }

    public static Category toCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        return category;
    }


    @Override
    @Transactional
    public ResultDTO create(CategoryRequest categoryRequest) {

        // check duplication
        if (categoryRepository.existsByName(categoryRequest.getName())) {
            return ResultBuilder.error(Constants.CATEGORY_ALREADY_EXISTS);
        }
        categoryRepository.save(toCategory(categoryRequest));
        log.info("Category created successfully against :: {}", categoryRequest.getName());
        return ResultBuilder.success(Constants.CATEGORY_CREATED_SUCCESSFULLY);
    }

    @Override
    @Transactional
    public ResultDTO update(String id, CategoryRequest categoryRequest) {

        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(Constants.CATEGORY_NOT_FOUND));

        if (categoryRepository.existsByName(categoryRequest.getName())) {
            log.info("Category with name {} already exists", categoryRequest.getName());
            return ResultBuilder.error(Constants.CATEGORY_ALREADY_EXISTS);
        }

        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        categoryRepository.save(category);
        log.info("Category updated successfully against :: {}", categoryRequest.getName());
        return ResultBuilder.success(Constants.CATEGORY_UPDATED_SUCCESSFULLY);
    }


    @Override
    @Transactional
    public ResultDTO delete(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(Constants.CATEGORY_NOT_FOUND));
        categoryRepository.delete(category);
        log.info("Category deleted successfully against :: {}", category.getId());
        return ResultBuilder.success(Constants.CATEGORY_DELETED_SUCCESSFULLY);
    }

    @Override
    public ResultDTO getAll() {

        List<Category> list = categoryRepository.findAll();
        if (list.isEmpty()) {
            log.info("Category list is empty");
            return ResultBuilder.error(Constants.CATEGORY_NOT_FOUND);
        }
        List<CategoryResponse> categoryResponseList;
        categoryResponseList = list.stream().map(CategoryServiceImpl::toCategoryResponse).toList();
        log.info("Category list contains {}", categoryResponseList.size());

        return ResultBuilder.success(Constants.CATEGORY_FETCHED_SUCCESSFULLY, categoryResponseList);
    }

    @Override
    public ResultDTO getById(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(Constants.CATEGORY_NOT_FOUND));
        log.info("Category with id {} found", id);
        return ResultBuilder.success(HttpStatus.OK.toString(), toCategoryResponse(category));
    }
}
