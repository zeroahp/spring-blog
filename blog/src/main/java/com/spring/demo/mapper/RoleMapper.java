package com.spring.demo.mapper;

import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.RoleEntity;
import com.spring.demo.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toRoleDTO(RoleEntity roleEntity);
}
