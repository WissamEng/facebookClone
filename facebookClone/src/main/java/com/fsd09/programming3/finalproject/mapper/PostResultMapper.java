package com.fsd09.programming3.finalproject.mapper;

import com.fsd09.programming3.finalproject.result.CommentResult;
import com.fsd09.programming3.finalproject.result.PostResult;
import com.fsd09.programming3.finalproject.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * mapper to convert Entity to Result
 */
@Component
@RequiredArgsConstructor
@Transactional
public class PostResultMapper implements Function<Post, PostResult> {
    final CommentResultMapper commentResultMapper;


    @Override
    public PostResult apply(Post post) {
        List<CommentResult> commentResults = post.getCommentList().stream().map(commentResultMapper).collect(Collectors.toList());
        LocalDateTime postTime = post.getPostTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = postTime.format(dateTimeFormatter);
        return new PostResult(
                post.getPostId(),
                post.getPostContent(),
                format,
                post.getUser().getUserId(),
                post.getUser().getUserName(),
                commentResults
        );
    }
}
