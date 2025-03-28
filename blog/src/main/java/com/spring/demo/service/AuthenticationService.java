package com.spring.demo.service;

import com.nimbusds.jose.JOSEException;
import com.spring.demo.model.dto.AuthenticationDTO;
import com.spring.demo.model.dto.IntrospectResponse;
import com.spring.demo.model.request.AuthenticationRequest;
import com.spring.demo.model.request.IntrospectRequest;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationDTO authenticate(AuthenticationRequest userRequest);
    IntrospectResponse introspectToken(IntrospectRequest introspectRequest) throws ParseException, JOSEException;
    void logout(IntrospectRequest introspectRequest) throws ParseException, JOSEException;


}
