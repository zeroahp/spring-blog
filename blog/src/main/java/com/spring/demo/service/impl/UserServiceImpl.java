package com.spring.demo.service.impl;

import com.spring.demo.mapper.UserMapper;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.exception.AppException;
import com.spring.demo.exception.ErrorCode;
import com.spring.demo.model.request.UserRequest;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO registerUser(UserRequest user) {

        if(userRepository.existsByUsername(user.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }


        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.toUserDTO(savedUserEntity);
    }

    @Override
    public UserDTO findUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "This does not exists"));

        return userMapper.toUserDTO(userEntity);
    }



    @Override
    public UserEntity updateUser(Long id, UserRequest user) {
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
