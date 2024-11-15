package com.spring.demo.repository;

import com.spring.demo.model.entity.CommentEntity;
import com.spring.demo.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,String > {

}
