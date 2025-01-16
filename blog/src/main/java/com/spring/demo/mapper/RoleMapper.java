package com.spring.demo.mapper;

import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.entity.PermissionEntity;
import com.spring.demo.model.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "rolePermission", target = "permissionName", qualifiedByName ="mapPermissions" )
    RoleDTO toRoleDTO(RoleEntity roleEntity);

    @Named("mapPermissions")
    default Set<String> mapPermissions(Set<PermissionEntity> permissionEntities){
        if (permissionEntities == null) {
            return null;
        }
        return permissionEntities.stream().map(PermissionEntity::getPermissionName).collect(Collectors.toSet());
    }

}
