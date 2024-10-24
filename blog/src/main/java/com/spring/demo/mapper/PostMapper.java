package com.spring.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

//    @Mapping(source = "author", target = "author.username")
//    @Mapping(source = "postDTO", target = "author.posts")
//    PostEntity toPost(PostDTO postDTO, UserEntity userEntity);

//    @Named("mapAuthor")
//    default UserEntity mapAuthor(String author)
//    {
//        if (author != null)
//        {
//            UserEntity userEntity = new UserEntity();
//            userEntity.setUsername(author);
//
//            return userEntity;
//        }
//        return null;
//    }
}


