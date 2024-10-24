package com.spring.demo.model.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

   private Long id;
   private String title;
   private String content;
   private String authorName;
//   private String author_id;
}
