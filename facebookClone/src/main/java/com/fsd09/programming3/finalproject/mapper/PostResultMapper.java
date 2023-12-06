package com.fsd09.programming3.finalproject.mapper;

import com.fsd09.programming3.finalproject.result.PostResult;
import com.fsd09.programming3.finalproject.entity.Post;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * mapper to convert Entity to Result
 */
@Component
public class PostResultMapper implements Function<Post, PostResult> {

    @Override
    public PostResult apply(Post post) {
        List<String> commentIdList = post.getCommentList().stream().map(item -> item.getCommentId()).collect(Collectors.toList());
        return new PostResult(
                post.getPostId(),
                post.getPostContent(),
                post.getUser().getUserId(),
                commentIdList
        );
    }
}
