package com.spring.demo.service;

import com.spring.demo.enums.Role;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.request.RoleRequest;

public interface CategoryService {

    Role addRole(RoleRequest roleRequest);

}
