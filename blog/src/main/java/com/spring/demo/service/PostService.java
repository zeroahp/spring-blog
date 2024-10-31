package com.spring.demo.service;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.request.PostRequest;

public interface PostService {

    PostDTO createPost(PostRequest postRequest);
    PostDTO getPostById(String id);
    PostDTO updatePost(PostRequest postRequest, String postId);
    Object detelePostById(String id);
    void deleteAllPost();
}
