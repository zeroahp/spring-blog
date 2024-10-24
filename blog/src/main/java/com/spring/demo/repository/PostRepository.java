package com.spring.demo.repository;

import com.spring.demo.model.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findByTitle(String title);
    List<PostEntity> findByAuthorId(Long author);

}
