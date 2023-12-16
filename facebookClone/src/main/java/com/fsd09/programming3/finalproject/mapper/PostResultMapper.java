package com.fsd09.programming3.finalproject.mapper;

import com.fsd09.programming3.finalproject.entity.Comment;
import com.fsd09.programming3.finalproject.entity.Post;
import com.fsd09.programming3.finalproject.result.CommentResult;
import com.fsd09.programming3.finalproject.result.PostResult;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * mapper to convert Entity to Result
 */
@Component
@RequiredArgsConstructor

public class PostResultMapper implements Function<Post, PostResult> {
    final CommentResultMapper commentResultMapper;


    @Override
    @Transactional
    public PostResult apply(Post post) {
        List<CommentResult> commentResults = post.getCommentList().stream().sorted(Comparator.comparing(Comment::getCommentTime).reversed()).map(commentResultMapper).collect(Collectors.toList());
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
