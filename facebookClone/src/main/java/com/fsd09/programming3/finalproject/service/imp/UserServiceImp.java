package com.fsd09.programming3.finalproject.service.imp;

import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.mapper.UserResultMapper;
import com.fsd09.programming3.finalproject.repository.UserRepository;
import com.fsd09.programming3.finalproject.result.UserResult;
import com.fsd09.programming3.finalproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 *
 */
@Service
public class UserServiceImp implements IUserService {
    private final UserRepository userRepository;
    private final UserResultMapper userResultMapper;
    @Autowired
    public UserServiceImp(UserRepository userRepository, UserResultMapper userResultMapper) {
        this.userRepository = userRepository;
        this.userResultMapper = userResultMapper;
    }

    @Override
    public UserResult insertNewUser(User user) {
        user.setPostList(new ArrayList<>());
        user.setCommentList(new ArrayList<>());
        User add = userRepository.save(user);
        return userResultMapper.apply(add);
    }
}
