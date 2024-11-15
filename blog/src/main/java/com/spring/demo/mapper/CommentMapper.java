package com.spring.demo.mapper;

import com.spring.demo.model.dto.CommentDTO;
import com.spring.demo.model.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "user.username", target = "userName")
    @Mapping(source = "post.title", target = "postTitle")
    CommentDTO toCommentDTO (CommentEntity commentEntity);
}
