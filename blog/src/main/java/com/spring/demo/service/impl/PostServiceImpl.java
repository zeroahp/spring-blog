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
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
    @Transactional
    public PostDTO createPost(PostRequest postRequest) {

        UserEntity userEntity = userRepository.findById(postRequest.getUserId())
                .orElseThrow(() -> new RuntimeException(postRequest.getUserId() + "User does not exists"));

        //mapping
        PostEntity postEntity = PostEntity.builder()
                .author(userEntity)
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();

        if (postRepository.count() == 0){
            postEntity.setId(UUID.randomUUID().toString());
        }

        //update ID
        PostEntity postEntitySaved = postRepository.save(postEntity);
        return postMapper.toPostDTO(postEntitySaved);

    }

    @Override
    public PostDTO getPostById(String postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(postId + "Post does not exists"));

        PostDTO postDTO = postMapper.toPostDTO(postEntity);
        return postDTO;
    }




    @Override
    public PostDTO updatePost(PostRequest postRequest, String postId) {

        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new RuntimeException(postId + "Post does not exists"));

        postEntity.setTitle(postRequest.getTitle());
        postEntity.setContent(postRequest.getContent());

        PostEntity updatedPostEntity = postRepository.save(postEntity);
        return postMapper.toPostDTO(updatedPostEntity);
    }


    @Override
    public Object detelePostById(String id) {
        if(postRepository.findById(id).isPresent()){
            postRepository.deleteById(id);
        }else {
            throw new RuntimeException(id + "Post does not exists");
        }
        return null;
    }

    @Override
    public void deleteAllPost() {
        if(postRepository.count() != 0){
            postRepository.deleteAll();
        }else {
            throw new RuntimeException(postRepository.count() + "Post does not exists");
        }
    }


}
