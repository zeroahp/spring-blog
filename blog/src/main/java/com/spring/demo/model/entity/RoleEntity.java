package com.spring.demo.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int role_id;

    @Column
    String role_name;

    @Column
    String role_desc;

    @ManyToMany(mappedBy = "roles")
    Set<UserEntity> users = new HashSet<>();

}
