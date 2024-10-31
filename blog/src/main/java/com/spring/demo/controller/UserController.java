package com.spring.demo.controller;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.request.UserRequest;
import com.spring.demo.model.response.ResponseData;
import com.spring.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    ResponseEntity<ResponseData> register(@RequestBody @Valid UserRequest userRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseData.builder()
                        .data(userService.registerUser(userRequest))
                        .desc("User registered successfully!" )
                        .build());
    }

    @GetMapping("/{userId}/posts/{offset}/{pageSize}")
    public ResponseEntity<ResponseData> getPostsByUserId(@PathVariable int offset,
                                          @PathVariable int pageSize,
                                          @PathVariable String userId){
        return ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(userService.getPostByAuthorId(offset, pageSize, userId))
                        .desc("Posts found with offset: " + offset + " and page size: " + pageSize + " by userId: " + userId)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getUser(@PathVariable("id") String id){

        return ResponseEntity.ok()
                .body(ResponseData.builder()
                    .data(userService.findUserById(id))
                    .desc("User registered successfully!" )
                    .build());
    }

    @DeleteMapping("/")
    ResponseEntity<ResponseData> deleteAll(){
        userService.deleteAllUsers();
        return ResponseEntity.ok()
                .body(ResponseData.builder()
                    .desc("User registered successfully!" )
                    .build());
    }

}
