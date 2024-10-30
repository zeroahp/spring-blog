package com.spring.demo.model.request;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

   private String id;

   @Size(min = 10, max = 60, message = "TITLE_INVALID")
   private String title;

   @Size(min = 10, message = "CONTENT_INVALID")
   private String content;

   private String author;
}
