package com.spring.demo.configuration;

import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.repository.RoleRepository;
import com.spring.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    RoleRepository roleRepository;

//    @Bean
//    ApplicationRunner applicationRunner() {
//         return args -> {
//             if(userRepository.findByUsername("admin").isEmpty()){
//                 PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//
//                 var role = roleRepository.findByRoleName("Author")
//                         .orElseThrow(() -> new RuntimeException("This role does not exist"));
//
//                 UserEntity userEntity = UserEntity.builder()
//                         .username("admin")
//                         .password(passwordEncoder.encode("admin")
//
//             }
//         }
//    }
}
