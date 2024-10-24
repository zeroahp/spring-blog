package com.spring.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="post")
@Getter
@Setter
@Builder
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
    @JoinColumn(name = "author_id")
    private UserEntity author ;


}
