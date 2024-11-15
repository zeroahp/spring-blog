package com.spring.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name="post")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     String id;

    @Column
     String title ;

    @Column
     String content ;

    @ManyToOne
    @JoinColumn(name = "author_id")
     UserEntity author ;

    @OneToMany(mappedBy = "post")
     List<CommentEntity> comments ;

    @ManyToMany(mappedBy = "posts")
    List<CategoryEntity> postCategories ;

}
