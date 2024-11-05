package com.spring.demo.model.entity;

import com.spring.demo.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE) //gia tri private
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column
    String username;

    @Column
    int age;

    @Column
    String address;

    @Column
    String phoneNumber;

    @Column
    String email;

    @Column
    String password;

    @Column
    List<Role> roles;


    @OneToMany(mappedBy = "author")
    List<PostEntity> posts;

}
