package com.spring.demo.service;

import com.spring.demo.model.dto.PermissionDTO;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.request.PermissionRequest;
import com.spring.demo.model.request.RoleRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

public interface PermissionService {

    PermissionDTO createPermission (PermissionRequest permissionRequest);
    List<PermissionDTO> getAllPermissions ();
}
