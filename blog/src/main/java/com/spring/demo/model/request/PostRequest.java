package com.spring.demo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {

   @NotBlank(message = "User ID is mandatory")
    String userId;

   @Size(min = 10, max = 60, message = "TITLE_INVALID")
    String title;

   @Size(min = 10, message = "CONTENT_INVALID")
    String content;

   Set<Long> categoryIds;


}
