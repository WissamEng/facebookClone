package com.fsd09.programming3.finalproject.controller;

import com.fsd09.programming3.finalproject.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    private final ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }
}
