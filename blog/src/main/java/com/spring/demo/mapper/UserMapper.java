package com.spring.demo.mapper;

import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "userRoles", target = "roles")
    UserDTO toUserDTO(UserEntity userEntity);

//    @Mapping(source = "author.username", target = "authorName")
    List<UserDTO> toUserDTOList(List<UserEntity> userEntities);

//    @Named("mapRoles")
//    default Set<String> mapRoles(Set<RoleEntity> roleEntities){
//        if (roleEntities == null) {
//            return null;
//        }
//        return roleEntities.stream().map(RoleEntity::getRoleName).collect(Collectors.toSet());
//    }

}
