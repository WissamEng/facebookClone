package com.fsd09.programming3.finalproject.controller;

import cn.hutool.core.util.BooleanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Controller
@CrossOrigin
@Slf4j
public class LoginController {
    @GetMapping("/login_page")
    public String loginPage(@RequestParam(required = false) Boolean error, Model model) {
        log.info("===========================checking username and password, error{}");
        if (BooleanUtil.isTrue(error)) {
            model.addAttribute("error", "wrong username or password");
        }
        return "loginPage";
    }



    @RequestMapping("/home")
    public String home() {
        return "index";
    }
}
