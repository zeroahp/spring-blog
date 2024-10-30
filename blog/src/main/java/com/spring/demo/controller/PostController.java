package com.spring.demo.controller;

import com.spring.demo.model.dto.ApiResponse;
import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.request.PostRequest;
import com.spring.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PropertySource("classpath:application.properties") //lay du lieu tu application.properties
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{userId}/post-create")
    ApiResponse<PostDTO> createPost(@RequestBody @Valid PostRequest postRequest,
                                    @PathVariable("userId") String userId){

        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setData(postService.createPost(postRequest, userId));
        return apiResponse ;
    }


    @GetMapping("/post/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") String id){
        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping("/{userId}/all-post")
    public List<PostDTO> getPostByUserId(@PathVariable String userId){
        return postService.getPostByAuthorId(userId);
    }

    @PutMapping("/{userId}/post-update/{postId}")
    public PostDTO updatePost(@PathVariable("postId") String postId,
                                           @PathVariable("userId") String userId
                                            ,@RequestBody PostRequest postRequest){

        return postService.updatePost(postRequest, postId, userId) ;
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") String id){
        postService.detelePostById(id);
        return  ResponseEntity.ok("Delete success");
    }

    @DeleteMapping("/post/delete-all")
    public ResponseEntity<String> deleteAllPost(){
        postService.deleteAllPost();
        return  ResponseEntity.ok("Delete succsess");
    }
}
