package com.fsd09.programming3.finalproject.domain;

/**
 *
 */

public record CommentDomain(
        String commentId,
        String commentContent,
        String userId,
        String postId

) {
}
