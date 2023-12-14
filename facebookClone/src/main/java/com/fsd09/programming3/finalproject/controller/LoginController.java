package com.fsd09.programming3.finalproject.controller;

import cn.hutool.core.util.BooleanUtil;
import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.mapper.UserResultMapper;
import com.fsd09.programming3.finalproject.result.PostResult;
import com.fsd09.programming3.finalproject.result.UserResult;
import com.fsd09.programming3.finalproject.service.IPostService;
import com.fsd09.programming3.finalproject.service.IUserService;
import com.fsd09.programming3.finalproject.util.AuthenticationContextGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

/**
 *
 */
@Controller
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginController {
    private final AuthenticationContextGetter authenticationContextGetter;
    private final UserResultMapper userResultMapper;
    private final IUserService userService;
    private final IPostService postService;
    @GetMapping("/login_page")
    public String loginPage(@RequestParam(required = false) Boolean error, Model model) {
        if (BooleanUtil.isTrue(error)) {
            model.addAttribute("error", "wrong username or password");
        }
        return "loginPage";
    }



    @RequestMapping("/home")
    public String home(Model model) throws UserPrincipalNotFoundException {
        User user = authenticationContextGetter.getCurrentAuthenticatedUser();
        //todo 手动刷新list
        String userId = user.getUserId();
        UserResult userResult = userService.getUserbyId(userId);
        List<PostResult> allPost = postService.getAllPost();
        model.addAttribute("postList", allPost);
        return "index";
    }
}
