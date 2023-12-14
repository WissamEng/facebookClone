package com.fsd09.programming3.finalproject.result;

import java.util.List;

/**
 *As the data type to return to frontend
 */

public record UserResult(
        String userId,
        String userName,
        String email,
        List<PostResult> postResults,
        List<CommentResult> commentResults
) {
}
