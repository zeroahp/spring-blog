package com.spring.demo.mapper;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.entity.PermissionEntity;
import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.entity.RoleEntity;
import com.spring.demo.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "userRoles", target = "roleName", qualifiedByName = "mapRoles")
    UserDTO toUserDTO(UserEntity userEntity);

//    @Mapping(source = "author.username", target = "authorName")
    List<UserDTO> toUserDTOList(List<UserEntity> userEntities);

    @Named("mapRoles")
    default Set<String> mapRoles(Set<RoleEntity> roleEntities){
        if (roleEntities == null) {
            return null;
        }
        return roleEntities.stream().map(RoleEntity::getRoleName).collect(Collectors.toSet());
    }

}
