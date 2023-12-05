package com.fsd09.programming3.finalproject.mapper;

import com.fsd09.programming3.finalproject.DTO.PostDTO;
import com.fsd09.programming3.finalproject.entity.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
@Component
public class PostDTOMapper implements Function<Post, PostDTO> {

    @Override
    public PostDTO apply(Post post) {
        List<String> commentIdList = post.getCommentList().stream().map(item -> item.getCommentId()).collect(Collectors.toList());
        return new PostDTO(
                post.getPostId(),
                post.getPostContent(),
                post.getUser().getUserId(),
                commentIdList
                );
    }
}
