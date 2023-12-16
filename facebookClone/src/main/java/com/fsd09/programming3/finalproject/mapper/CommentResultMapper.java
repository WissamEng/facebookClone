package com.fsd09.programming3.finalproject.mapper;

import com.fsd09.programming3.finalproject.entity.Comment;
import com.fsd09.programming3.finalproject.result.CommentResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * mapper to convert Entity to Result
 */
@Component

public class CommentResultMapper implements Function<Comment, CommentResult> {
    @Override
    @Transactional
    public CommentResult apply(Comment comment) {
        LocalDateTime commentTime = comment.getCommentTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
        String format = commentTime.format(dateTimeFormatter);
        return new CommentResult(comment.getCommentId(),
                comment.getCommentContent(),// TODO
                format,
                comment.getUser().getUserId(),
                comment.getUser().getUserName(),
                comment.getPost().getPostId());
    }
}
