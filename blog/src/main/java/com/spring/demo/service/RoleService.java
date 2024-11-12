package com.spring.demo.service;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.request.PostRequest;
import com.spring.demo.model.request.RoleRequest;
import org.springframework.data.domain.Page;

public interface RoleService {

    RoleDTO addRole(RoleRequest roleRequest);
//    PostDTO getPostById(String id);
//    Page<PostDTO> getAllPost(int offset, int pageSize);
//    PostDTO updatePost(PostRequest postRequest, String postId);
//    Object detelePostById(String id);
//    void deleteAllPost();
}
