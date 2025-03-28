package com.spring.demo.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.spring.demo.enums.ErrorCode;
import com.spring.demo.exception.AppException;
import com.spring.demo.model.dto.IntrospectResponse;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.IntrospectRequest;
import com.spring.demo.repository.InvalidatedTokenRepository;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Component
public class JWTNimbusd
{
    @NonFinal
    @Value("${spring.jwt.signerkey}")
    protected String SIGNER_KEY;

    @Autowired
    InvalidatedTokenRepository invalidatedTokenRepository;


    public String generateToken(UserEntity userEntity){

        //header
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        //claim
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userEntity.getUsername())
                .issuer("ahpzero")
                .issueTime(new Date())
                .jwtID(UUID.randomUUID().toString())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", builScope(userEntity))
                .build();

        //payload
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        //jwsObject
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            //sign
            jwsObject.sign(new MACSigner(SIGNER_KEY));
            // chuyển JWT đã ký thành chuỗi để trả về, dạng mã hóa của token này là mã base64
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    //SCOPE
    private String builScope(UserEntity userEntity){
        Set<String> scopeSet = new HashSet<>();
        if(!CollectionUtils.isEmpty(userEntity.getUserRoles())){
            userEntity.getUserRoles().forEach(role -> {
                scopeSet.add("ROLE_" + role.getRoleName());
                if(!CollectionUtils.isEmpty(role.getRolePermission())){
                    role.getRolePermission()
                            .forEach(permission -> scopeSet.add(permission.getPermissionName()));
                }
            });
        }
        return String.join(" ", scopeSet);
    }


    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(token);
        } catch (ParseException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        //Check expirationTime
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        //xác thực chữ ký của một JSON Web Token (JWT) đã được ký
        var verified = signedJWT.verify(verifier);
        if(!(verified && expirationTime.after(new Date()))){
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        //check-logout
        if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        return signedJWT;

    }

}
