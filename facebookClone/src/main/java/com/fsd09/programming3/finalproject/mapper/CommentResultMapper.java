package com.fsd09.programming3.finalproject.mapper;

import com.fsd09.programming3.finalproject.entity.Comment;
import com.fsd09.programming3.finalproject.result.CommentResult;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * mapper to convert Entity to Result
 */
@Component
public class CommentResultMapper implements Function<Comment, CommentResult> {
    @Override
    public CommentResult apply(Comment comment) {
        return new CommentResult(comment.getCommentId(),
                comment.getCommentContent(),
                comment.getUser().getUserId(),
                comment.getPost().getPostId());
    }
}
