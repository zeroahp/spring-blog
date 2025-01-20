package com.spring.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    Set<RoleEntity> userRoles;


//    @ManyToMany
//    Set<RoleEntity> roles;
//
//    @OneToMany(mappedBy = "author")
//    List<PostEntity> posts;

//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    RoleEntity role ;

//    @OneToMany(mappedBy = "user")
//    List<CommentEntity> comments;

}
