package com.spring.demo.controller;


import com.spring.demo.model.dto.CategoryDTO;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.request.CategoryRequest;
import com.spring.demo.model.request.RoleRequest;
import com.spring.demo.model.response.ResponseData;
import com.spring.demo.service.CategoryService;
import com.spring.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    ResponseEntity<ResponseData> addCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryDTO categoryDTO = categoryService.addCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseData.builder()
                        .data(categoryDTO)
                        .desc("Category added successfully" )
                        .build());
    }


}
