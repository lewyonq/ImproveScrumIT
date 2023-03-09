package com.lewyonq.isit.controller;

import com.lewyonq.isit.model.RegisterRequest;
import com.lewyonq.isit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
