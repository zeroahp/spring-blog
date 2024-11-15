package com.spring.demo.repository;

import com.spring.demo.model.entity.CategoryEntity;
import com.spring.demo.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,String > {

    List<CategoryEntity> findAllByCategoryIdIn(Set<Long> categoryIds);
}
