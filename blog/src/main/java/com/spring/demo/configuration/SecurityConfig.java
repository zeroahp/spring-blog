package com.spring.demo.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@EnableWebSecurity
@Configuration
public class SecurityConfig
{
    private final String[] GET_PUBLIC_ENDPOINT = {"/api/post/**"};
    private final String[] POST_PUBLIC_ENDPOINT = {"/api/register", "/auth/log-in", "/auth/introspect"};
    private final String[] DELETE_PUBLIC_ENDPOINT = {"/post/delete-all"};


    @Value("${spring.jwt.signerkey}")
    protected String SIGNER_KEY;

    @Bean //gọi phương thức khi ứng dụng khởi động và quản lý đối tượng trả về.
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, POST_PUBLIC_ENDPOINT).permitAll()
//                .requestMatchers(HttpMethod.GET, GET_PUBLIC_ENDPOINT).permitAll()
//                .requestMatchers(HttpMethod.DELETE, DELETE_PUBLIC_ENDPOINT).permitAll()
//                .anyRequest().authenticated());

        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().permitAll());

        //Allow access when a valid token is provided.

        //when get "/api/user/2"
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt((jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))));
        //tat cau hinh bao ve enpoint
        httpSecurity.csrf((AbstractHttpConfigurer::disable));
        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}

