package com.spring.demo.service;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.request.PostRequest;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostRequest postRequest, String authorId);
    PostDTO getPost(String id);
    List<PostDTO> getPostByAuthorId(String authorId);
    PostDTO updatePost(PostRequest postRequest, String postId, String authorId);
    void detelePostById(String id);
    void deleteAllPost();
}
