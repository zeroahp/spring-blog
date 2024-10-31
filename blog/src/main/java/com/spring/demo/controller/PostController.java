package com.spring.demo.controller;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.request.PostRequest;
import com.spring.demo.model.response.ResponseData;
import com.spring.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@PropertySource("classpath:application.properties") //lay du lieu tu application.properties
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    ResponseEntity<ResponseData> createPost(@RequestBody @Valid PostRequest postRequest){
        PostDTO postCreated = postService.createPost(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResponseData.builder()
                .data(postCreated)
                .desc("Post created with id: " + postCreated.getId())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getPostById(@PathVariable("id") String id){
        return ResponseEntity.ok()
            .body(ResponseData.builder()
                .data(postService.getPostById(id))
                .desc("Get post with id: " + id)
                .build());

    }

    @GetMapping("/{offset}/{pageSize}")
    public ResponseEntity<ResponseData> getAllPostWithPagination(@PathVariable int offset,
                                                                 @PathVariable int pageSize){
        return ResponseEntity.ok()
                .body(ResponseData.builder()
                        .desc("Posts with offset: " + offset + " and page size: " + pageSize)
                        .data(postService.getAllPostWithPagination(offset, pageSize))
                        .build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData> updatePost(@PathVariable("id") String postId,
                              @RequestBody PostRequest postRequest){
        return ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(postService.updatePost(postRequest, postId))
                        .desc("Update post with id: " + postId)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deletePost(@PathVariable("id") String id){
        return  ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(postService.detelePostById(id))
                        .desc("Delete post with id: "+ id)
                        .build());
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseData> deleteAllPost(){
        postService.deleteAllPost();
        return ResponseEntity.ok()
                .body(ResponseData.builder()
                        .desc("Delete all post")
                        .build());
    }
}
