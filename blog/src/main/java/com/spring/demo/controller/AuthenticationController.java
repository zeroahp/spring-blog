package com.spring.demo.controller;


import com.spring.demo.model.request.AuthenticationRequest;
import com.spring.demo.model.request.IntrospectRequest;
import com.spring.demo.model.response.ResponseData;
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
    ResponseEntity<ResponseData> login(@RequestBody AuthenticationRequest authenticationRequest) {

        //SetData
        return ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(authenticationServiceImpl.authenticate(authenticationRequest))
                        .desc("Login successful")
                        .build());

    }


    //verified (expire, key,..)
    @PostMapping("/introspect")
    ResponseEntity<ResponseData> introspectToken(@RequestBody IntrospectRequest request) {

        var result = authenticationServiceImpl.introspectToken(request);

        return  ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(result)
                        .desc("Introspection successful")
                        .build());
    }




}
