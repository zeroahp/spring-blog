package com.spring.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private int age;

    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "author")
    private List<PostEntity> posts;

}
