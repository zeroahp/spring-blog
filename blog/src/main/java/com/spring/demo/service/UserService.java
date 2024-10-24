package com.spring.demo.service;

import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.UserRequest;

import java.util.List;

public interface UserService {
    UserEntity registerUser(UserRequest user);
    UserEntity loginUser(UserEntity user);
    UserEntity updateUser(Long id, UserRequest user);
    UserEntity deleteUser(Long id);
    UserEntity findUserById(Long id);
    List<UserEntity> findAllUsers();

}
