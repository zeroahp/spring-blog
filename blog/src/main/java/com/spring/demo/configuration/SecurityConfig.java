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
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/post/**").permitAll()
                .anyRequest().authenticated());

        //tat cau hinh bao ve enpoint
        httpSecurity.csrf((AbstractHttpConfigurer::disable));
        return httpSecurity.build();
    }

}
