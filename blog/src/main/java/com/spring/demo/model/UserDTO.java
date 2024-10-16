package com.spring.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    private String username;
    private int age;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;

}
