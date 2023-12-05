package com.fsd09.programming3.finalproject.DTO;

import java.util.List;

/**
 *
 */

public record UserDTO(
        String userId,
        String userName,
        String email,
        List<String> postIdList,
        List<String> commentIdList
) {
}
