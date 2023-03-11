package com.lewyonq.isit.controller;

import com.lewyonq.isit.enums.UserRole;
import com.lewyonq.isit.model.OwnerRequest;
import com.lewyonq.isit.model.RegisterRequest;
import com.lewyonq.isit.model.User;
import com.lewyonq.isit.service.CompanyService;
import com.lewyonq.isit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/add-new")
    public String registerUser(Model model) {
        model.addAttribute("register", new RegisterRequest());
        return "user-register-form";
    }

    @PostMapping("/add-new")
    public String addUser(@ModelAttribute RegisterRequest request, @AuthenticationPrincipal User owner) throws Exception {
        userService.addUser(request, UserRole.USER, owner);
        return "redirect:/user/panel";
    }

    @GetMapping("/add-new-owner")
    public String registerOwner(Model model) {
        model.addAttribute("registerOwner", new OwnerRequest());
        return "owner-register-form";
    }

    @PostMapping("/add-new-owner")
    public String addOwner(@ModelAttribute OwnerRequest request) {
        userService.addOwner(request);
        return "redirect:/login";
    }

    @GetMapping("/panel")
    public String showOwnerPanel() {
        return "owner-panel";
    }

}
