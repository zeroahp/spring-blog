package com.spring.demo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    int role_id;

    String role_name;
    String role_desc;
    Set<String> user_id;
}
