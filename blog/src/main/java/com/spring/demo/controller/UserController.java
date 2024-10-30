package com.spring.demo.controller;

import com.spring.demo.model.dto.UserDTO;
import com.spring.demo.model.dto.ApiResponse;
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
    ApiResponse<UserDTO> register(@RequestBody @Valid UserRequest userRequest) {
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.registerUser(userRequest));
        return apiResponse;
    }

    @GetMapping("/user/{id}")
    public ApiResponse<UserDTO> getUser(@PathVariable("id") String id){

        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.findUserById(id));

        return apiResponse;
    }

    @DeleteMapping("/user/delete-all")
    ResponseEntity<String> deleteAll(){
        userService.deleteAllUsers();
        return ResponseEntity.ok("Deleted all users");
    }

}
