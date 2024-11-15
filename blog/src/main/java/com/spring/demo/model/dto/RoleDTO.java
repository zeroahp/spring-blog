package com.spring.demo.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDTO {
    Long roleId;
    String roleName;
    String roleDesc;
    Set<String> userId;
}
