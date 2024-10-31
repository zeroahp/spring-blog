package com.spring.demo.service;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.UserRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDTO registerUser(UserRequest user);
    UserEntity loginUser(UserEntity user);
    UserDTO updateUser(String userId, UserRequest user);
    void deleteUser(String userId);
    void deleteAllUsers();
    UserDTO findUserById(String id);
    Page<PostDTO> getPostByAuthorId(int offset, int pageSize, String authorId);

}
