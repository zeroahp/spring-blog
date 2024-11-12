package com.spring.demo.service.impl;

import com.spring.demo.enums.ErrorCode;
import com.spring.demo.enums.Role;
import com.spring.demo.exception.AppException;
import com.spring.demo.mapper.PostMapper;
import com.spring.demo.mapper.RoleMapper;
import com.spring.demo.mapper.UserMapper;
import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.entity.RoleEntity;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.RoleRequest;
import com.spring.demo.model.request.UserRequest;
import com.spring.demo.repository.PostRepository;
import com.spring.demo.repository.RoleRepository;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.RoleService;
import com.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleDTO addRole(RoleRequest roleRequest) {

        RoleEntity roleEntity = RoleEntity.builder()
                .role_name(roleRequest.getRole_name())
                .role_desc(roleRequest.getRole_desc())
                .build();

        RoleEntity newRole = roleRepository.save(roleEntity);
        return roleMapper.toRoleDTO(newRole);
    }

}