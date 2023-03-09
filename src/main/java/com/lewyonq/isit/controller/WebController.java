package com.lewyonq.isit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String viewHomePage() {
        return "home";
    }

    @GetMapping("/home")
    public String viewHomePage2() {
        return "home";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/hello")
    public String viewHelloPage() {
        return "hello";
    }

}
