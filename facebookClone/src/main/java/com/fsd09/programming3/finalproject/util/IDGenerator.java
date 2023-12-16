package com.fsd09.programming3.finalproject.util;

/**
 *
 */
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class IDGenerator {

    private static final String USER_PREFIX = "U";
    private static final String POST_PREFIX = "P";
    private static final String COMMENT_PREFIX = "C";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String generateUserId() {
        return generateId(USER_PREFIX);
    }

    public static String generatePostId() {
        return generateId(POST_PREFIX);
    }

    public static String generateCommentId() {
        return generateId(COMMENT_PREFIX);
    }

    private static String generateId(String prefix) {
        String date = LocalDateTime.now().format(DATE_FORMAT);
        String uniqueId = UUID.randomUUID().toString().replaceAll("-", "");
        return prefix + "-" + date + "-" + uniqueId;
    }
}

