package com.fsd09.programming3.finalproject.controller;

import com.fsd09.programming3.finalproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }
}
