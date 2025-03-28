package com.spring.demo.service.impl;

import com.spring.demo.enums.Role;
import com.spring.demo.mapper.PostMapper;
import com.spring.demo.mapper.UserMapper;
import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.entity.RoleEntity;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.exception.AppException;
import com.spring.demo.enums.ErrorCode;
import com.spring.demo.model.request.UserRequest;
import com.spring.demo.repository.PostRepository;
import com.spring.demo.repository.RoleRepository;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PostRepository postRepository;
    UserMapper userMapper;
    PostMapper postMapper;
    RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;


    @Override
    public UserDTO registerUser(UserRequest user) {

        if(userRepository.existsByUsername(user.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.AUTHOR.name());
        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();

        var roleEntity = roleRepository.findByRoleNameIn(roles);
        userEntity.setUserRoles(new HashSet<>(roleEntity));

        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.toUserDTO(savedUserEntity);
    }

    @Override
    public UserDTO updateUser(String userId,  UserRequest user) {

        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(userId + " not found"));

        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        var role = roleRepository.findByRoleNameIn(user.getRoleName());
        userEntity.setUserRoles(new HashSet<>(role));

        userRepository.save(userEntity);
        return  userMapper.toUserDTO(userRepository.save(userEntity));
    }


    @Override
    public UserDTO findUserById(String id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "This does not exists"));

        return userMapper.toUserDTO(userEntity);
    }

    @Override
    public Page<PostDTO> getPostByAuthorId(int offset, int pageSize, String authorId) {
        Page<PostEntity> posts = postRepository.findByAuthor_Id(authorId, PageRequest.of(offset, pageSize));
        Page<PostDTO> postDTOS= posts.map(postMapper::toPostDTO);
        return postDTOS;
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

    @Override
    public List<UserDTO> findAllUsers() {
        log.info("In method get Users");
        return userMapper.toUserDTOList(userRepository.findAll());
    }

    @Override
    public UserDTO getMyInfo() { //dang login
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        log.info("User: {}",username);
        log.info("Role: {}", context.getAuthentication().getAuthorities());
        UserEntity user  = userRepository.findByUsername(username).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        return userMapper.toUserDTO(user);
    }


    @Override
    public UserEntity loginUser(UserEntity user) {
        return null;
    }
}
