package com.fsd09.programming3.finalproject.mapper;

import com.fsd09.programming3.finalproject.entity.Comment;
import com.fsd09.programming3.finalproject.entity.Post;
import com.fsd09.programming3.finalproject.result.CommentResult;
import com.fsd09.programming3.finalproject.result.PostResult;
import com.fsd09.programming3.finalproject.result.UserResult;
import com.fsd09.programming3.finalproject.entity.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * mapper to convert Entity to Result
 */
@Component
@RequiredArgsConstructor
@Transactional
public class UserResultMapper implements Function<User, UserResult> {
    private final CommentResultMapper commentResultMapper;
    private final PostResultMapper postResultMapper;
    @Override
    public UserResult apply(User user) {
        user.getPostList().sort(Comparator.comparing(Post::getPostTime).reversed());
        List<PostResult> postResultList = user.getPostList().stream().map(postResultMapper).collect(Collectors.toList());
        user.getCommentList().sort(Comparator.comparing(Comment::getCommentTime).reversed());

        List<CommentResult> commentResultList = user.getCommentList().stream().map(commentResultMapper).collect(Collectors.toList());
        System.out.println(postResultList);
        return new UserResult(user.getUserId(), user.getUserName(), user.getEmail(), postResultList, commentResultList);
    }
}
