package com.spring.demo.model.entity;

import com.spring.demo.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
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


    @OneToMany(mappedBy = "author")
    List<PostEntity> posts;

    @ManyToMany
    @JoinTable(
            name = "User_Role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    List<CommentEntity> comments;

}
