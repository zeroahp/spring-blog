package com.spring.demo.model.dto;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
     String id;
     String username;
     int age;
     String address;
     String phoneNumber;
    @Email
     String email;
     Set<String> roleName;
}
