package com.spring.demo.service.impl;

import com.spring.demo.mapper.CategoryMapper;
import com.spring.demo.mapper.PostMapper;
import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.entity.CategoryEntity;
import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.entity.UserEntity;
import com.spring.demo.model.request.PostRequest;
import com.spring.demo.repository.CategoryRepository;
import com.spring.demo.repository.PostRepository;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    PostMapper postMapper;
    CategoryMapper categoryMapper;

    @Override
    @Transactional
    public PostDTO createPost(PostRequest postRequest) {

        UserEntity userEntity = userRepository.findById(postRequest.getUserId())
                .orElseThrow(() -> new RuntimeException(postRequest.getUserId() + "User does not exists"));


        List<CategoryEntity> categories = new ArrayList<>();
        if(postRequest.getCategoryIds() != null && !postRequest.getCategoryIds().isEmpty()){
            categories = categoryRepository.findAllByCategoryIdIn(postRequest.getCategoryIds());
        }

        //builder postEntity
        PostEntity postEntity = PostEntity.builder()
                .author(userEntity)
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postCategory(categories)
                .build();

        if (postRepository.count() == 0){
            postEntity.setId(UUID.randomUUID().toString());
        }

        //update ID
        PostEntity postEntitySaved = postRepository.save(postEntity);

        //builder postDTO
        PostDTO postDTO = PostDTO.builder()
                .id(postEntitySaved.getId())
                .title(postEntitySaved.getTitle())
                .content(postEntitySaved.getContent())
                .authorName(postEntitySaved.getAuthor().getUsername())
                .categories(categoryMapper.toListCategoryDTO(postEntitySaved.getPostCategory()))
                .build();

        return postDTO;
    }

    @Override
    public PostDTO getPostById(String postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(postId + "Post does not exists"));

        PostDTO postDTO = postMapper.toPostDTO(postEntity);
        return postDTO;
    }

    @Override
    public Page<PostDTO> getAllPost(int offset, int pageSize) {
        Page<PostEntity> Posts = postRepository.findAll(PageRequest.of(offset,pageSize));

        Page<PostDTO> postDTOS = Posts.map(postMapper::toPostDTO);
        return postDTOS;
    }
//    @Override
//    public Page<PostDTO> getAllPostWithPaginationAndSorting(int offset, int pageSize, String field) {
//        Page<PostEntity> Posts = postRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
//
//        return postMapper.toPostDTOPageList(Posts);
//
//    }


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
