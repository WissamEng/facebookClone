package com.fsd09.programming3.finalproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 */
@ControllerAdvice
public class UrlInjection {
    @Value("${server.url}")
    private String serverUrl;

    @ModelAttribute("serverUrl")
    public String serverUrl(){
        return serverUrl;
    }
}
