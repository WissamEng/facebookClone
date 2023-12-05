package com.fsd09.programming3.finalproject.service.imp;

import com.fsd09.programming3.finalproject.repository.UserRepository;
import com.fsd09.programming3.finalproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImp implements IUserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
