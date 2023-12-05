package com.fsd09.programming3.finalproject.mapper;

import com.fsd09.programming3.finalproject.DTO.CommentDTO;
import com.fsd09.programming3.finalproject.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 *
 */
@Component
public class CommentDTOMapper implements Function<Comment, CommentDTO> {
    @Override
    public CommentDTO apply(Comment comment) {
        return new CommentDTO(comment.getCommentId(),
                comment.getCommentContent(),
                comment.getUser().getUserId(),
                comment.getPost().getPostId());
    }
}
