package com.spring.demo.service.impl;

import com.spring.demo.entity.UserEntity;
import com.spring.demo.exception.AppException;
import com.spring.demo.exception.ErrorCode;
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
            throw new AppException(ErrorCode.USER_EXISTED);
        }


        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
//        userEntity.setUsername(user.getUsername());
//        userEntity.setPassword(user.getPassword());
//        userEntity.setEmail(user.getEmail());

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(id + "This does not exists"));
    }



    @Override
    public UserEntity updateUser(Long id, UserDTO user) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException(id + "This does not exists"));
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userRepository.save(userEntity);
        return  userRepository.save(userEntity);

    }

    @Override
    public UserEntity deleteUser(Long id) {
        return null;
    }



    @Override
    public List<UserEntity> findAllUsers() {
        return List.of();
    }


    @Override
    public UserEntity loginUser(UserEntity user) {
        return null;
    }
}
