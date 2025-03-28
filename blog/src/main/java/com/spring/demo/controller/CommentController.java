package com.spring.demo.controller;


import com.spring.demo.model.dto.CommentDTO;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.request.CommentRequest;
import com.spring.demo.model.request.RoleRequest;
import com.spring.demo.model.response.ResponseData;
import com.spring.demo.service.CommentService;
import com.spring.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/")
    ResponseEntity<ResponseData> addComment(@RequestBody CommentRequest commentRequest) {
        CommentDTO commentDTO = commentService.addComment(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseData.builder()
                        .data(commentDTO)
                        .desc("Comment added successfully")
                        .build());
    }

}
