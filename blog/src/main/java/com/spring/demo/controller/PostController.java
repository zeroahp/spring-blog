package com.spring.demo.controller;

import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.request.PostRequest;
import com.spring.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PropertySource("classpath:application.properties") //lay du lieu tu application.properties
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{userId}/create-post")
    public ResponseEntity<String> createPost(@RequestBody PostRequest post, @PathVariable("userId") Long userId){
        PostEntity postEntity = postService.createPost(post, userId);
        return new ResponseEntity<>("Post: " + postEntity.getId(), HttpStatus.CREATED) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostEntity> getPostById(@PathVariable("id") Long id){
        PostEntity result = postService.getPost(id);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}/all-post")
    public List<PostEntity> getPostByUserId(@PathVariable Long userId){
        return postService.getPostByAuthorId(userId);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updatePost(@PathVariable("id") Long id
//                                            ,@RequestBody PostDTO postDTO){
//        PostEntity result = postService.updatePost(id, postDTO);
//        return new ResponseEntity<>("Post: " + result, HttpStatus.CREATED) ;
//    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){
//        postService.deletePost(id);
//        return  ResponseEntity.ok("delete success");
//    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteAllPost(){
//        postService.deleteAllPost();
//        return  ResponseEntity.ok("delete success");
//    }
}
