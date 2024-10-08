package com.spring.demo.service;

import com.spring.demo.entity.PostEntity;

import java.util.List;

public interface PostService {

    PostEntity createPost(PostEntity post);
    PostEntity getPost(Long id);
    List<PostEntity> getAllPost();


}
