package com.lewyonq.isit.controller;

import com.lewyonq.isit.model.Company;
import com.lewyonq.isit.model.NewCompanyRequest;
import com.lewyonq.isit.model.User;
import com.lewyonq.isit.repository.UserRepository;
import com.lewyonq.isit.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final UserRepository userRepository;

    @GetMapping("/add")
    public String addCompanyPage(Model model) {
        model.addAttribute("newCompany", new NewCompanyRequest());
        return "new-company-form";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute NewCompanyRequest request,
                             @AuthenticationPrincipal User owner) throws Exception {
        Company company = Company.builder()
                .name(request.getCompanyName())
                .maturityLevel(0)
                .projects(new ArrayList<>())
                .users(new ArrayList<>())
                .build();

        companyService.createNewCompany(company);
        //todo: add company to user;
        owner.setCompany(company);
        userRepository.save(owner);

        return "redirect:/user/panel";
    }

//    @GetMapping("/users")
//    public List<User> showAllUsers(@AuthenticationPrincipal User user) {
//
//    }
}
