package com.spring.demo.service.impl;

import com.spring.demo.mapper.CategoryMapper;
import com.spring.demo.model.dto.CategoryDTO;
import com.spring.demo.model.entity.CategoryEntity;
import com.spring.demo.model.request.CategoryRequest;
import com.spring.demo.repository.CategoryRepository;
import com.spring.demo.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Override
    public CategoryDTO addCategory(CategoryRequest categoryRequest) {

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryName(categoryRequest.getCategoryName())
                .categoryDesc(categoryRequest.getCategoryDesc())
                .build();


        CategoryEntity newCategory = categoryRepository.save(categoryEntity);
        return categoryMapper.toCategoryDTO(newCategory);
    }

}