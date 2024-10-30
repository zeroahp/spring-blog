package com.spring.demo.service;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.request.PostRequest;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostRequest postRequest, Long authorId);
    PostDTO getPost(Long id);
    List<PostDTO> getPostByAuthorId(Long authorId);
    PostDTO updatePost(PostRequest postRequest, Long postId, Long authorId);
    void detelePostById(Long id);
    void deleteAllPost();
}
