package com.fsd09.programming3.finalproject.service;

import com.fsd09.programming3.finalproject.result.CommentResult;
import com.fsd09.programming3.finalproject.result.PostResult;
import jakarta.validation.constraints.NotEmpty;

/**
 *
 */
public interface ICommentService {
    void addNewComment(PostResult post, @NotEmpty String commentContent, String userId);

    CommentResult getCommentById(String commentId);

    void updateComment(String commentId, String commentContent);

    void delete(String commentId);
}
