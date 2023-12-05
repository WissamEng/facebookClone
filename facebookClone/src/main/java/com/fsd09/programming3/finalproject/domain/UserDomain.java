package com.fsd09.programming3.finalproject.domain;

import java.util.List;

/**
 *
 */

public record UserDomain(
        String userId,
        String userName,
        String email,
        List<String> postIdList,
        List<String> commentIdList
) {
}
