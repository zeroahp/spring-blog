package com.spring.demo.controller;


import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.request.AuthenticationRequest;
import com.spring.demo.service.AuthenticationServiceImpl;
import com.spring.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationServiceImpl authenticationServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/log-in")
    ResponseEntity login(@RequestBody AuthenticationRequest authenticationRequest) {

        long userId = authenticationServiceImpl.authenticate(authenticationRequest);
        UserDTO user = userServiceImpl.findUserById(userId);

        return ResponseEntity.ok("Login success.");
    }




}
