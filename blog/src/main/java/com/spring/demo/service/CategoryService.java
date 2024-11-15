package com.spring.demo.service;

import com.spring.demo.model.dto.CategoryDTO;
import com.spring.demo.model.request.CategoryRequest;

public interface CategoryService {

    CategoryDTO addCategory(CategoryRequest categoryRequest);

}
