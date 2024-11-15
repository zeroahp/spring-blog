package com.spring.demo.mapper;

import com.spring.demo.model.dto.CategoryDTO;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.entity.CategoryEntity;
import com.spring.demo.model.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toCategoryDTO(CategoryEntity categoryEntity);
    List<CategoryDTO> toListCategoryDTO(List<CategoryEntity> categoryEntity);
}
