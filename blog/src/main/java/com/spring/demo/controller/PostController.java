package com.spring.demo.controller;

import com.spring.demo.entity.PostEntity;
import com.spring.demo.model.PostDTO;
import com.spring.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PropertySource("classpath:application.properties") //lay du lieu tu application.properties
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<String> createPost(@RequestBody PostDTO post){
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postService.createPost(postEntity);
        return new ResponseEntity<>("Post ok: " + postEntity.getId(), HttpStatus.CREATED) ;
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<PostEntity> getPostById(@PathVariable("id") Long id){
        PostEntity result = postService.getPost(id);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/all-post")
    public List<PostEntity> getAllPost(){
        return postService.getAllPost();
    }
}
