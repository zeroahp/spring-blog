package com.spring.demo.service.impl;

import com.spring.demo.enums.ErrorCode;
import com.spring.demo.enums.Role;
import com.spring.demo.exception.AppException;
import com.spring.demo.mapper.PermissionMapper;
import com.spring.demo.mapper.PostMapper;
import com.spring.demo.mapper.RoleMapper;
import com.spring.demo.mapper.UserMapper;
import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.PermissionEntity;
import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.entity.RoleEntity;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.RoleRequest;
import com.spring.demo.model.request.UserRequest;
import com.spring.demo.repository.PermisstionRepository;
import com.spring.demo.repository.PostRepository;
import com.spring.demo.repository.RoleRepository;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.RoleService;
import com.spring.demo.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {


    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermisstionRepository permisstionRepository;
    PermissionMapper permissionMapper;

    @Override
    public RoleDTO addRole(RoleRequest roleRequest) {

        RoleEntity roleEntity = RoleEntity.builder()
                .roleName(roleRequest.getRoleName())
                .roleDesc(roleRequest.getRoleDesc())
                .build();

//        .stream().collect(Collectors.toSet()
        var permissionEntities = permisstionRepository.findByPermissionNameIn(roleRequest.getPermissionNames());
        roleEntity.setRolePermission(new HashSet<>(permissionEntities));

        roleRepository.save(roleEntity);
        return roleMapper.toRoleDTO(roleEntity);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleDTO).collect(Collectors.toList());
    }
}