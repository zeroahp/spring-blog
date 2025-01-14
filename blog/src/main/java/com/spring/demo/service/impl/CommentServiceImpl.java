package com.spring.demo.service.impl;

import com.spring.demo.mapper.CommentMapper;
import com.spring.demo.model.dto.CommentDTO;
import com.spring.demo.model.entity.CommentEntity;
import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.CommentRequest;
import com.spring.demo.repository.CommentRepository;
import com.spring.demo.repository.PostRepository;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    private CommentMapper commentMapper;

    @Override
    public CommentDTO addComment(CommentRequest commentRequest) {

        UserEntity getUser = new UserEntity();
        PostEntity getPost = new PostEntity();
        if(userRepository.findById(commentRequest.getUserId()) !=  null && postRepository.findById(commentRequest.getPostId()) != null){
            getUser = userRepository.findById(commentRequest.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found!"));
            getPost = postRepository.findById(commentRequest.getPostId())
                    .orElseThrow(() -> new RuntimeException("Post not found!"));
        }
        CommentEntity commentEntity = CommentEntity.builder()
                .content(commentRequest.getContent())
                .user(getUser)
                .post(getPost)
                .build();

        CommentEntity newComment = commentRepository.save(commentEntity);
        return commentMapper.toCommentDTO(newComment);
    }

}