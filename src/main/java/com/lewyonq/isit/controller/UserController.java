package com.lewyonq.isit.controller;

import com.lewyonq.isit.enums.UserRole;
import com.lewyonq.isit.model.RegisterRequest;
import com.lewyonq.isit.model.User;
import com.lewyonq.isit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/register-owner")
    public String registerOwnerPage(Model model) {
        model.addAttribute("addUser", new RegisterRequest());
        return "register-owner-form";
    }

    @PostMapping("/register-owner")
    public String registerOwner(@ModelAttribute RegisterRequest request) {
        userService.registerOwner(request);
        return "register-owner-form";
    }

    @GetMapping("/add")
    public String addUserPage(Model model) {
        model.addAttribute("addUser", new RegisterRequest());
        return "add-user-form";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute RegisterRequest request, @AuthenticationPrincipal User owner) throws Exception {
        userService.addUser(request, UserRole.USER, owner);
        return "redirect:/user/panel";
    }

    @GetMapping("/panel")
    public String showOwnerPanel() {
        return "owner-panel";
    }

}
