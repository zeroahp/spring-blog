package com.spring.demo.configuration;

import com.spring.demo.enums.Role;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.repository.RoleRepository;
import com.spring.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

                HashSet<String> role = new HashSet<>();
                role.add(Role.ADMIN.name());

                UserEntity userEntity = UserEntity.builder()
                        .username("admin")
//                         .roles(role)
                        .password(passwordEncoder.encode("admin"))
                        .build();


                userRepository.save(userEntity);
                log.warn("admin user has been created");
            }
        };
    }
}
