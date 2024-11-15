package com.spring.demo.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CategoryDTO {
    Long categoryId;
    String categoryName;
    String categoryDesc;
    Set<PostDTO> listPost;
}
