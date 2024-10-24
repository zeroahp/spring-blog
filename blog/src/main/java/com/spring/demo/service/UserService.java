package com.spring.demo.service;

import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.UserRequest;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserRequest user);
    UserEntity loginUser(UserEntity user);
    UserEntity updateUser(Long id, UserRequest user);
    UserEntity deleteUser(Long id);
    UserDTO findUserById(Long id);
    List<UserEntity> findAllUsers();

}
