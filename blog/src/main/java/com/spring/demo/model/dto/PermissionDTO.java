package com.spring.demo.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionDTO {
    Long permissionId;
    String permissionName;
    String permissionDesc;
}
