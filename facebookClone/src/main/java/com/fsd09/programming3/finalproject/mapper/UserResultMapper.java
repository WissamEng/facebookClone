package com.fsd09.programming3.finalproject.mapper;

import com.fsd09.programming3.finalproject.result.UserResult;
import com.fsd09.programming3.finalproject.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *mapper to convert Entity to Result
 */
@Component
public class UserResultMapper implements Function<User, UserResult> {
    @Override
    public UserResult apply(User user) {
        List<String> postIdList = user.getPostList().stream().map(item -> item.getPostId()).collect(Collectors.toList());
        List<String> commentIdList = user.getCommentList().stream().map(item -> item.getCommentId()).collect(Collectors.toList());
        return new UserResult(user.getUserId(), user.getUserName(), user.getEmail(), postIdList, commentIdList);
    }
}
