package com.spring.demo.mapper;

import com.spring.demo.model.dto.PermissionDTO;
import com.spring.demo.model.entity.PermissionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDTO toPermissionDTO (PermissionEntity permissionEntity);
    List<PermissionDTO> toPermissionDTOList (List<PermissionEntity> permissionEntities);
}
