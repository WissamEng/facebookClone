package com.fsd09.programming3.finalproject.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.result.UserResult;
import com.fsd09.programming3.finalproject.service.IUserService;
import com.fsd09.programming3.finalproject.util.IDGenerator;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserController(IUserService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<Object> registerNewUser(@RequestParam String userName,@RequestParam String password,@RequestParam String email){
        User newUser = new User()
                .builder()
                .userId(IDGenerator.generateUserId())
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        UserResult userResult = userService.insertNewUser(newUser);
        if (BeanUtil.isNotEmpty(userResult)){
            return ResponseEntity.ok().body("user has been added");
        }else {
            return ResponseEntity.internalServerError().body("something went wrong");
        }
    }
}
