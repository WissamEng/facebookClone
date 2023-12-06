package com.fsd09.programming3.finalproject.service;

import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.result.UserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 */

public interface IUserService {
    UserResult insertNewUser(User user);
}
