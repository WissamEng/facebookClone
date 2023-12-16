package com.fsd09.programming3.finalproject.service.imp;

import com.fsd09.programming3.finalproject.entity.Comment;
import com.fsd09.programming3.finalproject.entity.Post;
import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.mapper.UserResultMapper;
import com.fsd09.programming3.finalproject.repository.UserRepository;
import com.fsd09.programming3.finalproject.result.UserResult;
import com.fsd09.programming3.finalproject.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements IUserService {
    private final UserRepository userRepository;
    private final UserResultMapper userResultMapper;

    @Override
    public UserResult insertNewUser(User user) {
        user.setPostList(new ArrayList<Post>());
        user.setCommentList(new ArrayList<Comment>());
        User add = userRepository.save(user);
        log.debug(add.toString());
        return userResultMapper.apply(add);
    }

    @Override
    public UserResult getUserbyId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("no user can be found"));
        List<Post> postList = user.getPostList();

        UserResult userResult = userResultMapper.apply(user);
        return userResult;
    }
}