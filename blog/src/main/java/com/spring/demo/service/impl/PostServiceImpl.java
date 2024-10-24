package com.spring.demo.service.impl;

import com.spring.demo.mapper.PostMapper;
import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.PostRequest;
import com.spring.demo.repository.PostRepository;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostMapper postMapper;


    @Override
    public PostDTO createPost(PostRequest postRequest, Long userId) {

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(userId + "User does not exists"));

        //mapping
        PostEntity postEntity = PostEntity.builder()
                .author(userEntity)
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();

        if (postRepository.count() == 0){
            postEntity.setId(1L);
        }

        //update ID
        PostEntity postEntitySaved = postRepository.save(postEntity);
        return postMapper.toPostDTO(postEntitySaved);

    }

    @Override
    public PostEntity getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "This does not exists"));

    }

    @Override
    public List<PostEntity> getPostByAuthorId(Long authorId) {
     return postRepository.findByAuthorId(authorId);
    }


    @Override
    public PostEntity updatePost(Long id, PostRequest postRequest) {
        if(postRepository.findById(id).isPresent()){
            PostEntity newPost = new PostEntity();
            newPost.setId(id);
            newPost.setTitle(postRequest.getTitle());
            newPost.setContent(postRequest.getContent());
            return postRepository.save(newPost);
        }else {
            throw new RuntimeException(id + "This does not exists");
        }
    }
    @Override
    public void deletePost(Long id) {
        if(postRepository.findById(id).isPresent()){
            postRepository.deleteById(id);
        }else {
            throw new RuntimeException(id + "This does not exists");
        }
    }

    @Override
    public void deleteAllPost() {
        postRepository.deleteAll();
    }


}
