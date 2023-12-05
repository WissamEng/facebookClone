package com.fsd09.programming3.finalproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 */

public record CommentDTO(
        String commentId,
        String commentContent,
        String userId,
        String postId

) {
}
