package com.spring.demo.service;

import com.spring.demo.entity.UserEntity;
import com.spring.demo.model.UserDTO;

import java.util.List;

public interface UserService {
    UserEntity registerUser(UserDTO user);
    UserEntity loginUser(UserEntity user);
    UserEntity updateUser(Long id, UserDTO user);
    UserEntity deleteUser(Long id);
    UserEntity findUserById(Long id);
    List<UserEntity> findAllUsers();

}
