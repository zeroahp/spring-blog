package com.spring.demo.model.dto;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

   private String id;
   private String title;
   private String content;
   private String authorName;
//   private String author_id;
}
