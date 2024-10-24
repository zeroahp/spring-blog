package com.spring.demo.model.request;

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
public class UserRequest {
    private long id;

    @Size(min = 6, message = "USERNAME_INVALID")
    private String username;
    private int age;
    private String address;

    @Size(min = 10, max = 10, message = "PHONE_NUMBER_INVALID" )
    private String phoneNumber;

    @Email
    private String email;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;

}
