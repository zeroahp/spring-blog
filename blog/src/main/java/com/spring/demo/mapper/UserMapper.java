package com.spring.demo.mapper;

import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role.roleName", target = "roleName")
    UserDTO toUserDTO(UserEntity userEntity);
}
