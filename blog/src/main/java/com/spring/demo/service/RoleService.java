package com.spring.demo.service;

import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.request.RoleRequest;

import java.util.List;

public interface RoleService {

    RoleDTO addRole(RoleRequest roleRequest);
    List<RoleDTO> getAllRoles();

}
