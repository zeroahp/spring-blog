package com.spring.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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

    @Size(min = 6, message = "User name must be at least 6 character.")
    private String username;
    private int age;
    private String address;

    @Size(min = 10, max = 10, message = "Phone number must be at 10 character." )
    private String phoneNumber;

    @Email
    private String email;

    @Size(min = 8, message = "Password must be at least 8 character.")
    private String password;

}
