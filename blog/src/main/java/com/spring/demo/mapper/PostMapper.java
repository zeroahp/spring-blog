package com.spring.demo.mapper;

import com.spring.demo.model.dto.PostDTO;
import com.spring.demo.model.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(source = "author.username", target = "authorName")
    PostDTO toPostDTO(PostEntity postEntity);

//    @Mapping(source = "author.username", target = "author_id")
//    PostDTO toPostDTOAuthorId(PostEntity postEntity);

    @Mapping(source = "author.username", target = "authorName")
    List<PostDTO> toPostDTOList(List<PostEntity> postEntities);


}


