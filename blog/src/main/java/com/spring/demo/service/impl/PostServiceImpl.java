package com.spring.demo.service.impl;

import com.spring.demo.entity.PostEntity;
import com.spring.demo.model.PostDTO;
import com.spring.demo.repository.PostRepository;
import com.spring.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public PostEntity createPost(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setContent(postDTO.getContent());
//        postEntity.setAuthor(postDTO.getAuthor());
        if (postRepository.count() == 0){
            postEntity.setId(1L);
        }
        return postRepository.save(postEntity);

    }

    @Override
    public PostEntity getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException(id + "This does not exists"));

    }

    @Override
    public List<PostEntity> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public PostEntity updatePost(Long id, PostDTO postDTO) {
        if(postRepository.findById(id).isPresent()){
            PostEntity newPost = new PostEntity();
            newPost.setId(id);
            newPost.setTitle(postDTO.getTitle());
            newPost.setContent(postDTO.getContent());
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
