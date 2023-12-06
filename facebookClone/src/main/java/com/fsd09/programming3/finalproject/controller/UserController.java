package com.fsd09.programming3.finalproject.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fsd09.programming3.finalproject.DTO.UserDTOForRegister;
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
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(IUserService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerNewUser(@RequestBody UserDTOForRegister userDTO) {
        User newUser = new User()
                .builder()
                .userId(IDGenerator.generateUserId())
                .userName(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .build();
        UserResult userResult = userService.insertNewUser(newUser);
        if (BeanUtil.isNotEmpty(userResult)) {
            return ResponseEntity.ok().body("user has been added");
        } else {
            return ResponseEntity.internalServerError().body("Username is existed");
        }
    }
}
