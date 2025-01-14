package com.spring.demo.mapper;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(source = "roles.roleName", target = "roleName")
    UserDTO toUserDTO(UserEntity userEntity);

//    @Mapping(source = "author.username", target = "authorName")
    List<UserDTO> toUserDTOList(List<UserEntity> userEntities);
}
