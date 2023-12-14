package com.fsd09.programming3.finalproject.result;

/**
 * As the data type to return to frontend
 */

public record CommentResult(
        String commentId,
        String commentContent,
        String commentTime,
        String userId,
        String username,
        String postId

) {
}
