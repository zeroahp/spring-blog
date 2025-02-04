package com.spring.demo.repository;

import com.spring.demo.model.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,String > {
    PostEntity findByTitle(String title);
    List<PostEntity> findByAuthorId(String author);
    void deleteById(String Id);
    Page<PostEntity> findByAuthor_Id(String author, Pageable pageable);


}
