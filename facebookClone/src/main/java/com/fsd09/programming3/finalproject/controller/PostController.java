package com.fsd09.programming3.finalproject.controller;

import com.fsd09.programming3.finalproject.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/post")
public class PostController {
    private final IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }
}
