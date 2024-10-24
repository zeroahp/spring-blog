package com.spring.demo.service;

import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.request.PostRequest;

import java.util.List;

public interface PostService {

    PostEntity createPost(PostRequest postRequest, Long userId);
    PostEntity getPost(Long id);
    List<PostEntity> getPostByAuthorId(Long authorId);

    PostEntity updatePost(Long id, PostRequest postRequest);
    void deletePost(Long id);
    void deleteAllPost();
}
