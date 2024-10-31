package com.spring.demo.service.impl;

import com.spring.demo.mapper.PostMapper;
import com.spring.demo.mapper.UserMapper;
import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.exception.AppException;
import com.spring.demo.exception.ErrorCode;
import com.spring.demo.model.request.UserRequest;
import com.spring.demo.repository.PostRepository;
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
    private PostRepository postRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

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
    public UserDTO findUserById(String id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "This does not exists"));

        return userMapper.toUserDTO(userEntity);
    }

    @Override
    public List<PostDTO> getPostByAuthorId(String authorId) {
        if(userRepository.findById(authorId).isPresent()){
            List<PostEntity> postEntities =  postRepository.findByAuthorId(authorId);
            List<PostDTO> postDTOS = postMapper.toPostDTOList(postEntities);
            return postDTOS;
        }else {
            throw new RuntimeException(authorId + "Author does not exists");
        }
    }



    @Override
    public UserDTO updateUser(String id, UserRequest user) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException(id + "This does not exists"));
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userRepository.save(userEntity);
        return  userMapper.toUserDTO(userRepository.save(userEntity));

    }

    @Override
    public void deleteUser(String userId) {
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


//    @Override
//    public List<UserDTO> findAllUsers() {
//        return userRepository.findAll();
//    }


    @Override
    public UserEntity loginUser(UserEntity user) {
        return null;
    }
}
