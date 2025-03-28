package com.spring.demo.controller;


import com.nimbusds.jose.JOSEException;
import com.spring.demo.model.request.AuthenticationRequest;
import com.spring.demo.model.request.IntrospectRequest;
import com.spring.demo.model.response.ResponseData;
import com.spring.demo.service.AuthenticationService;
import com.spring.demo.service.impl.AuthenticationServiceImpl;
import com.spring.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ResponseEntity<ResponseData> login(@RequestBody AuthenticationRequest authenticationRequest) {

        //SetData
        return ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(authenticationService.authenticate(authenticationRequest))
                        .desc("Login successful")
                        .build());

    }


    //verified (expire, key,..)
    @PostMapping("/introspect")
    ResponseEntity<ResponseData> introspectToken(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {

        var result = authenticationService.introspectToken(request);

        return  ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(result)
                        .desc("Introspection successful")
                        .build());
    }

    @PostMapping("/logout")
    ResponseEntity<ResponseData> logout(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
       authenticationService.logout(request);
        return  ResponseEntity.ok()
                .body(ResponseData.builder()
                        .desc("Logout successful")
                        .build());
    }




}
