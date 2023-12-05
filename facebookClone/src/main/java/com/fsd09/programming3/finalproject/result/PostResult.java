package com.fsd09.programming3.finalproject.result;

import java.util.List;

/**
 *As the data type to return to frontend
 */

public record PostResult(
        String postId,
        String postContent,
        String userId,
        List<String> commentIdList
) {

}
