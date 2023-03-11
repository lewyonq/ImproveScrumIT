package com.lewyonq.isit.controller;

import com.lewyonq.isit.model.User;
import com.lewyonq.isit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final UserRepository userRepository;

    @GetMapping("/show-all-users-from-company/{id}")
    public List<User> showAllUsers(@PathVariable Long id) {
        return userRepository.findByCompanyId(id);
    }
}
