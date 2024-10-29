package com.spring.demo.service;


import com.nimbusds.jose.JOSEException;
import com.spring.demo.exception.AppException;
import com.spring.demo.exception.ErrorCode;
import com.spring.demo.model.dto.AuthenticationDTO;
import com.spring.demo.model.dto.IntrospectResponse;
import com.spring.demo.model.request.AuthenticationRequest;
import com.spring.demo.model.request.IntrospectRequest;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.util.JWTNimbusd;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTNimbusd jwtNimbusd;

    public IntrospectResponse introspectToken(IntrospectRequest token){
        try {
            return jwtNimbusd.introspectToken(token);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthenticationDTO authenticate(AuthenticationRequest userRequest) {
        var user =  userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean result = passwordEncoder.matches(userRequest.getPassword(), user.getPassword());

        if(!result)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = jwtNimbusd.generateToken(userRequest.getUsername());

        return  AuthenticationDTO.builder()
                                .authenticated(true)
                                .token(token)
                                .build();
    }




}







