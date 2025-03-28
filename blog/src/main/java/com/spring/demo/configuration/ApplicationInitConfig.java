package com.spring.demo.configuration;

import com.spring.demo.enums.Role;
import com.spring.demo.model.entity.RoleEntity;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.PermissionRequest;
import com.spring.demo.model.request.RoleRequest;
import com.spring.demo.repository.RoleRepository;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.PermissionService;
import com.spring.demo.service.RoleService;
import com.spring.demo.service.impl.RoleServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    RoleRepository roleRepository;
    RoleService roleService;
    PermissionService permissionService;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Set<String> roleNames = Collections.singleton(Role.ADMIN.name());
                Set<RoleEntity> roleEntities = roleRepository.findByRoleNameIn(roleNames);
                if (roleEntities.isEmpty()) {
                    PermissionRequest permissionRequest = new PermissionRequest();
                    permissionRequest.setPermissionName("VERIFY_POST");
                    permissionRequest.setPermissionDesc("Verify permission");
                    permissionService.createPermission(permissionRequest);

                    RoleRequest roleRequest = new RoleRequest();
                    roleRequest.setRoleName(Role.ADMIN.name());
                    roleRequest.setRoleDesc("Highest privilege role");

                    Set<String> permissions = new HashSet<>();
                    permissions.add(permissionRequest.getPermissionName());

                    roleRequest.setPermissionNames(permissions);
                    roleRequest.setPermissionNames(permissions);
                    roleService.addRole(roleRequest);
                    roleEntities = roleRepository.findByRoleNameIn(roleNames);
                }

                // Create admin user
                UserEntity userEntity = UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .userRoles(new HashSet<>(roleEntities))
                        .build();

                userRepository.save(userEntity);

                userRepository.save(userEntity);
                log.warn("admin user has been created");
            }
        };
    }
}
