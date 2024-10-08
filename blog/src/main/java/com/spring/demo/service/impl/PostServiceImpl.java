package com.spring.demo.service.impl;

import com.spring.demo.entity.PostEntity;
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
    public PostEntity createPost(PostEntity post) {
        return postRepository.save(post);

    }

    @Override
    public PostEntity getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException(id + "This does not exists"));

    }

    @Override
    public List<PostEntity> getAllPost() {
        return postRepository.findAll();
    }


}
