package com.spring.demo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig
{
    private final String[] GET_PUBLIC_ENDPOINT = {"/api/post/**"};
    private final String[] POST_PUBLIC_ENDPOINT = {"/api/register"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, POST_PUBLIC_ENDPOINT).permitAll()
                .requestMatchers(HttpMethod.GET, GET_PUBLIC_ENDPOINT).permitAll()
                .anyRequest().authenticated());

        //tat cau hinh bao ve enpoint
        httpSecurity.csrf((AbstractHttpConfigurer::disable));
        return httpSecurity.build();
    }

}
