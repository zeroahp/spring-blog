package com.spring.demo.service.impl;


import com.nimbusds.jose.JOSEException;
import com.spring.demo.exception.AppException;
import com.spring.demo.enums.ErrorCode;
import com.spring.demo.model.dto.AuthenticationDTO;
import com.spring.demo.model.dto.IntrospectResponse;
import com.spring.demo.model.entity.InvalidatedToken;
import com.spring.demo.model.request.AuthenticationRequest;
import com.spring.demo.model.request.IntrospectRequest;
import com.spring.demo.repository.InvalidatedTokenRepository;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.AuthenticationService;
import com.spring.demo.util.JWTNimbusd;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTNimbusd jwtNimbusd;

    @Autowired
    InvalidatedTokenRepository invalidatedTokenRepository;

    //login
    @Override
    public AuthenticationDTO authenticate(AuthenticationRequest userRequest) {
        var user =  userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean result = passwordEncoder.matches(userRequest.getPassword(), user.getPassword());

        if(!result)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        //utils jwtNimbusd.generateToken(user);
        var token = jwtNimbusd.generateToken(user);

        return  AuthenticationDTO.builder()
                                .authenticated(true)
                                .token(token)
                                .build();
    }

    @Override
    public IntrospectResponse introspectToken(IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        var token = introspectRequest.getToken();

        boolean isValid = true;
        try {
            jwtNimbusd.verifyToken(token);

        }catch (AppException e){
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    @Override
    public void logout(IntrospectRequest introspectRequest ) throws ParseException, JOSEException {
        var signToken = jwtNimbusd.verifyToken(introspectRequest.getToken());

        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryDate(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);
    }
}







