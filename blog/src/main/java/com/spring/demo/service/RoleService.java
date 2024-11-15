package com.spring.demo.service;

import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.request.RoleRequest;

public interface RoleService {

    RoleDTO addRole(RoleRequest roleRequest);

}
