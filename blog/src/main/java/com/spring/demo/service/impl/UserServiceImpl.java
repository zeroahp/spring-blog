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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
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
    public UserDTO updateUser(Long id, UserRequest user) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException(id + "This does not exists"));
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userRepository.save(userEntity);
        return  userMapper.toUserDTO(userRepository.save(userEntity));

    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("This does not exists"));
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUsers() {
        if(userRepository.count() != 0){
            userRepository.deleteAll();
        }else {
            throw new RuntimeException("There is no any users in the database");
        }
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
