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
import java.util.stream.Collectors;

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
    public PostDTO createPost(PostRequest postRequest, Long authorId) {

        UserEntity userEntity = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException(authorId + "User does not exists"));

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
    public PostDTO getPost(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(postId + "Post does not exists"));

        PostDTO postDTO = postMapper.toPostDTO(postEntity);
        return postDTO;
    }

    @Override
    public List<PostDTO> getPostByAuthorId(Long authorId) {
        if(userRepository.findById(authorId).isPresent()){
            List<PostEntity> postEntities =  postRepository.findByAuthorId(authorId);
            List<PostDTO> postDTOS = postMapper.toPostDTOList(postEntities);
            return postDTOS;
        }else {
            throw new RuntimeException(authorId + "Author does not exists");
        }

    }


    @Override
    public PostDTO updatePost(PostRequest postRequest, Long postId, Long authorId) {

        UserEntity userEntity = userRepository.findById(authorId).orElseThrow(() -> new RuntimeException(authorId + "Author does not exists"));
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new RuntimeException(postId + "Post does not exists"));

        postEntity.setTitle(postRequest.getTitle());
        postEntity.setContent(postRequest.getContent());

        PostEntity updatedPostEntity = postRepository.save(postEntity);
        return postMapper.toPostDTO(updatedPostEntity);
    }


    @Override
    public void detelePostById(Long id) {
        if(postRepository.findById(id).isPresent()){
            postRepository.deleteById(id);
        }else {
            throw new RuntimeException(id + "This does not exists");
        }
    }




}
