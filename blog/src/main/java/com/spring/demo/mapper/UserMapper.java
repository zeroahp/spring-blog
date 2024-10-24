package com.spring.demo.mapper;

import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(UserEntity userEntity);
}
