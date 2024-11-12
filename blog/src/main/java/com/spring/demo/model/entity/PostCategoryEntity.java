package com.spring.demo.model.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "postcategory")
@FieldDefaults(level = AccessLevel.PRIVATE) //gia tri private
public class PostCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String categoryName;

    @ManyToMany
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    Set<PostEntity> posts = new HashSet<>();


}
