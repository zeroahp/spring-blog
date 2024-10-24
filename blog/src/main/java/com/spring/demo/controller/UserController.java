package com.spring.demo.controller;

import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.ApiResponse;
import com.spring.demo.model.request.UserRequest;
import com.spring.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    ApiResponse<UserEntity> register(@RequestBody @Valid UserRequest userRequest) {
        ApiResponse<UserEntity> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.registerUser(userRequest));
        return apiResponse;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") Long id){
        UserEntity userEntity = userService.findUserById(id);
        return ResponseEntity.ok(userEntity);
    }



}
