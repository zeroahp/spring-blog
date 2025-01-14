package com.spring.demo.service.impl;

import com.spring.demo.mapper.PermissionMapper;
import com.spring.demo.model.dto.PermissionDTO;
import com.spring.demo.model.entity.PermissionEntity;
import com.spring.demo.model.request.PermissionRequest;
import com.spring.demo.repository.PermisstionRepository;
import com.spring.demo.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {

    PermisstionRepository permisstionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionDTO createPermission(PermissionRequest permissionRequest) {
        PermissionEntity permissionEntity = PermissionEntity.builder()
                .permissionName(permissionRequest.getPermissionName())
                .permissionDesc(permissionRequest.getPermissionDesc())
                .build();

        permisstionRepository.save(permissionEntity);

        return permissionMapper.toPermissionDTO(permissionEntity);
    }

    @Override
    public List<PermissionDTO> getAllPermissions() {
        return permissionMapper.toPermissionDTOList(permisstionRepository.findAll());
    }




}