package com.fsd09.programming3.finalproject.domain;

import java.util.List;

/**
 *
 */

public record PostDomain(
        String postId,
        String postContent,
        String userId,
        List<String> commentIdList
) {

}
