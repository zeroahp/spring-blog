package com.spring.demo.service.impl;

import com.spring.demo.entity.UserEntity;
import com.spring.demo.model.UserDTO;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity registerUser(UserDTO user) {

        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("User existed.");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserById(int id) {
        return null;
    }

    @Override
    public UserEntity loginUser(UserEntity user) {
        return null;
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return null;
    }

    @Override
    public UserEntity deleteUser(int id) {
        return null;
    }



    @Override
    public List<UserEntity> findAllUsers() {
        return List.of();
    }
}
