package com.spring.demo.mapper;

import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.RoleEntity;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.RoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "rolePermission", target = "rolePermission")
    RoleDTO toRoleDTO(RoleEntity roleEntity);

}
