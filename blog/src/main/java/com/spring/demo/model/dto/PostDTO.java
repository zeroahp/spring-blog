package com.spring.demo.model.dto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

   private String title;
   private String content;
   private String authorName;
   List<CategoryDTO> categories;
}
