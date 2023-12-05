package com.fsd09.programming3.finalproject.mapper;

import com.fsd09.programming3.finalproject.DTO.UserDTO;
import com.fsd09.programming3.finalproject.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
@Component
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        List<String> postIdList = user.getPostList().stream().map(item -> item.getPostId()).collect(Collectors.toList());
        List<String> commentIdList = user.getCommentList().stream().map(item -> item.getCommentId()).collect(Collectors.toList());
        return new UserDTO(user.getUserId(), user.getUserName(), user.getEmail(), postIdList, commentIdList);
    }
}
