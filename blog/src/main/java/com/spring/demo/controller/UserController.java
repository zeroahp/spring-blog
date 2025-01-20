package com.spring.demo.controller;

import com.spring.demo.model.request.UserRequest;
import com.spring.demo.model.response.ResponseData;
import com.spring.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
                    .desc("Get user successfully!" )
                    .build());
    }

    @GetMapping("/")
    ResponseEntity<ResponseData> getAllUsers(){

        //User dang login
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}",authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info("GrantedAuthority: {}", grantedAuthority));

        return ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(userService.findAllUsers())
                        .desc("All users found!" )
                        .build());
    }

    @GetMapping("/my-info")
    ResponseEntity<ResponseData> getMyInfo(){
        return ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(userService.getMyInfo())
                        .desc("My infor")
                        .build()
                );
    }

    @DeleteMapping("/")
    ResponseEntity<ResponseData> deleteAll(){
        userService.deleteAllUsers();
        return ResponseEntity.ok()
                .body(ResponseData.builder()
                    .desc("User registered successfully!" )
                    .build());
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseData> updateUser(@PathVariable String id, @RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(userService.updateUser(id,userRequest))
                        .desc("User updated successfully!" )
                        .build());
    }

}
