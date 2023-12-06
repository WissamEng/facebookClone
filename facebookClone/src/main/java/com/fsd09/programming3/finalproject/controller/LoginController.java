package com.fsd09.programming3.finalproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@CrossOrigin
public class LoginController {
    @GetMapping("/login_page")
    public String loginPage(){
        return "loginPage";
    }
    @RequestMapping("/home")
    public String home(){
        return "index";
    }
}
