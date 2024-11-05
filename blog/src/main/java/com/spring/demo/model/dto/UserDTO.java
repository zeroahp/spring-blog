package com.spring.demo.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String id;

    private String username;
    private int age;
    private String address;
    private String phoneNumber;
    private Set<String> roles;
    @Email
    private String email;


//    @Size(min = 8, message = "PASSWORD_INVALID")
//    private String password;

}
