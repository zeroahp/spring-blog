package com.spring.demo.repository;

import com.spring.demo.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findByTitle(String title);
}
