package com.fsd09.programming3.finalproject.DTO;

import java.util.List;

/**
 *
 */

public record PostDTO(
        String postId,
        String postContent,
        String userId,
        List<String> commentIdList
) {

}
