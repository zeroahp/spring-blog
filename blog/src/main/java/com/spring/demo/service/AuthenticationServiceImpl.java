package com.spring.demo.service;


import com.spring.demo.exception.AppException;
import com.spring.demo.exception.ErrorCode;
import com.spring.demo.model.request.AuthenticationRequest;
import com.spring.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl {

    @Autowired
    UserRepository userRepository;

    public Long authenticate(AuthenticationRequest userRequest) {
        var user =  userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean result = passwordEncoder.matches(userRequest.getPassword(), user.getPassword());

        if(result){
            return user.getId();
        }else {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

    }
}
