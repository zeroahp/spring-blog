package com.spring.demo.model.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "comment")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "postId")
    PostEntity post;


}
