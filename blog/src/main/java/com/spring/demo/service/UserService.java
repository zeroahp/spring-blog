package com.spring.demo.service;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserRequest user);
    UserEntity loginUser(UserEntity user);
    UserDTO updateUser(String userId, UserRequest user);
    void deleteUser(String userId);
    void deleteAllUsers();

    UserDTO findUserById(String id);
    Page<PostDTO> getPostByAuthorId(int offset, int pageSize, String authorId);

    @PreAuthorize("hasRole('ADMIN')") //returnObject.username == authentication.name
    List<UserDTO> findAllUsers();

    @PostAuthorize("returnObject.username == authentication.name") // Authentication từ SecurityContextHolder.
    UserDTO getMyInfo();
}
