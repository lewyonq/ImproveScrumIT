package com.lewyonq.isit.controller;

import com.lewyonq.isit.service.UserService;
import com.lewyonq.isit.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;

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

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("register", new RegisterRequest());
        return "user-register-form";
    }

    @PostMapping("/register")
    public String fullfillForm(@ModelAttribute RegisterRequest request) {
        userService.addUser(request);
        return "redirect:/login";
    }
}
