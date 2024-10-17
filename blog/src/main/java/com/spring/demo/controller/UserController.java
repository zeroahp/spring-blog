package com.spring.demo.controller;

import com.spring.demo.entity.UserEntity;
import com.spring.demo.model.UserDTO;
import com.spring.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createPost( @RequestBody @Valid UserDTO user){
        UserEntity userEntity = userService.registerUser(user);
        return new ResponseEntity<>("Register success: " + userEntity.getId(), HttpStatus.CREATED) ;
    }

}
