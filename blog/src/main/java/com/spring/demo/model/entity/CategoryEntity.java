package com.spring.demo.model.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE) //gia tri private
@Table(name = "Category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long categoryId;
    String categoryName;
    String categoryDesc;

    @ManyToMany(mappedBy = "postCategory")
    Set<PostEntity> posts = new HashSet<>();


}
