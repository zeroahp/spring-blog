package com.spring.demo.service;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.request.PostRequest;
import org.springframework.data.domain.Page;

public interface PostService {

    PostDTO createPost(PostRequest postRequest);
    PostDTO getPostById(String id);
    Page<PostDTO> getAllPost(int offset, int pageSize);
    PostDTO updatePost(PostRequest postRequest, String postId);
    Object detelePostById(String id);
    void deleteAllPost();
}
