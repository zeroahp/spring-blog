package com.spring.demo.service;

import com.spring.demo.entity.PostEntity;
import com.spring.demo.model.PostDTO;

import java.util.List;

public interface PostService {

    PostEntity createPost(PostDTO postDTO);
    PostEntity getPost(Long id);
    List<PostEntity> getAllPost();
    PostEntity updatePost(Long id, PostDTO postDTO);
    void deletePost(Long id);
    void deleteAllPost();
}
