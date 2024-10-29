package com.spring.demo.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
@Slf4j
public class JWTNimbusd
{
    @NonFinal
    protected static final String SIGNER_KEY = "XVZqaA3Ugs36a0VUs6u89hIjX3aDiycd7fpWS47HTJlzDwtS4BqfKE6izMgshUL5";


    public static String generateToken(String username){

        //header
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        //claim
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("ahpzero")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("customClaim", "Custom Claim")
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
}
