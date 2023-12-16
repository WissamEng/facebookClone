package com.fsd09.programming3.finalproject.controller;

import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.result.CommentResult;
import com.fsd09.programming3.finalproject.result.PostResult;
import com.fsd09.programming3.finalproject.service.ICommentService;
import com.fsd09.programming3.finalproject.service.IPostService;
import com.fsd09.programming3.finalproject.util.AuthenticationContextGetter;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 *
 */
@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final ICommentService commentService;
    private final AuthenticationContextGetter authenticationContextGetter;
    private final IPostService postService;

    @PostMapping("/addNewComment")
    public String addNewComment(@RequestParam @NotEmpty String commentContent, @RequestParam String postId) throws UserPrincipalNotFoundException {
        User user = authenticationContextGetter.getCurrentAuthenticatedUser();
        String userId = user.getUserId();
        PostResult post = postService.getPostById(postId);
            commentService.addNewComment(post, commentContent, userId);


        return "redirect:/home";
    }

    @GetMapping("/edit")
    public String editPost(@RequestParam String commentId, Model model) throws UserPrincipalNotFoundException {
        User user = authenticationContextGetter.getCurrentAuthenticatedUser();
        CommentResult comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "commentEdit";
    }

    @PostMapping("/updateComment")
    private String updatePost(@RequestParam String commentContent, @RequestParam String commentId) {
        commentService.updateComment(commentId, commentContent);
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String commentId) {
        commentService.delete(commentId);
        return "redirect:/home";
    }
}
