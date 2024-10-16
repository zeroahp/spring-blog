package com.spring.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Entity
@Table(name="post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title ;

    @Column
    private String content ;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author ;


}
