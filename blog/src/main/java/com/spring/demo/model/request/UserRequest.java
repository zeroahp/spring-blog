package com.spring.demo.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
     String id;

    @Size(min = 6, message = "USERNAME_INVALID")
     String username;
     int age;
     String address;

    @Size(min = 10, max = 10, message = "PHONE_NUMBER_INVALID" )
     String phoneNumber;

    @Email
     String email;

    @Size(min = 8, message = "PASSWORD_INVALID")
     String password;

    Set<String> roleName;



}
