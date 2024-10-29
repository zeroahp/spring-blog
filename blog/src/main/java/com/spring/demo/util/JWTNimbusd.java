package com.spring.demo.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.spring.demo.model.dto.IntrospectResponse;
import com.spring.demo.model.request.IntrospectRequest;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JWTNimbusd
{
    @NonFinal
    @Value("${spring.jwt.signerkey}")
    protected String SIGNER_KEY;


    public String generateToken(String username){

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

    public IntrospectResponse introspectToken(IntrospectRequest request)
            throws JOSEException, ParseException {
        var token = request.getToken();

        //verifier sign_key
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY);

        //Phan tich token nhan vao
        SignedJWT signedJWT = SignedJWT.parse(token);

        //Check expirationTime
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        //xác thực chữ ký của một JSON Web Token (JWT) đã được ký
        var verified = signedJWT.verify(verifier);

        return  IntrospectResponse.builder()
                .valid(verified && expirationTime.after(new Date()))
                .build();


    }
}
