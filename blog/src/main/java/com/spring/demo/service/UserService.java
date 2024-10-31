package com.spring.demo.service;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.UserRequest;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserRequest user);
    UserEntity loginUser(UserEntity user);
    UserDTO updateUser(String userId, UserRequest user);
    void deleteUser(String userId);
    void deleteAllUsers();
    UserDTO findUserById(String id);
//    List<UserEntity> findAllUsers();
    List<PostDTO> getPostByAuthorId(String authorId);

}
