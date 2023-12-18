package com.fsd09.programming3.finalproject.controller;

import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.result.PostResult;
import com.fsd09.programming3.finalproject.result.UserResult;
import com.fsd09.programming3.finalproject.service.IPostService;
import com.fsd09.programming3.finalproject.service.IUserService;
import com.fsd09.programming3.finalproject.util.AuthenticationContextGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 *
 */
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")

public class PostController {
    private final IPostService postService;
    private final AuthenticationContextGetter authenticationContextGetter;
    private final IUserService userService;

    @PostMapping("/addNewPost")
    public String addNewPost(@RequestParam String postContent, Model model) throws UserPrincipalNotFoundException {
        User user = authenticationContextGetter.getCurrentAuthenticatedUser();
        if(postContent == null || "".equals(postContent)){
            model.addAttribute("error", "post can not be blank");
            return "redirect:/home";
        }
        postService.addNewPost(user, postContent);
        UserResult userResult = userService.getUserbyId(user.getUserId());
        model.addAttribute("user", userResult);
        userResult.postResults().stream().forEach(item -> log.info(item.postContent()));
        return "redirect:/home";
    }

    @GetMapping("/edit")
    public String editPost(@RequestParam String postId, Model model) throws UserPrincipalNotFoundException {
        User user = authenticationContextGetter.getCurrentAuthenticatedUser();
        PostResult postResult = postService.getPostById(postId);
        if (!user.getUserId().equals(postResult.userId())) {
            throw new UserPrincipalNotFoundException("User can not be found");
        }
        model.addAttribute("post", postResult);
        return "postEdit";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam String postId) throws UserPrincipalNotFoundException {
        User user = authenticationContextGetter.getCurrentAuthenticatedUser();
        PostResult postResult = postService.getPostById(postId);
        if (!user.getUserId().equals(postResult.userId())) {
            throw new UserPrincipalNotFoundException("User can not be found");
        }
        postService.deleteById(postId, user.getUserId());
        return "redirect:/home";
    }

    @PostMapping("/updatePost")
    private String updatePost(@RequestParam String postContent, @RequestParam String postId, Model model) throws UserPrincipalNotFoundException {
        User user = authenticationContextGetter.getCurrentAuthenticatedUser();
        if(postContent == null || "".equals(postContent)){
            model.addAttribute("error", "Post content can not be blank");
            return "postEdit";
        }
        String userId = user.getUserId();
        PostResult postById = postService.getPostById(postId);
        if (!userId.equals(postById.userId())){
            throw new UserPrincipalNotFoundException("user can not be found");
        }
        postService.updatePost(postId, postContent);
        return "redirect:/home";
    }
}
