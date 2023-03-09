package com.lewyonq.isit.controller;

import com.lewyonq.isit.model.Company;
import com.lewyonq.isit.model.CompanyRequest;
import com.lewyonq.isit.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/add")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        return "company-register-form";
    }

    @PostMapping("/add")
    public String fullfillCompanyForm(@ModelAttribute CompanyRequest request) {
        companyService.addCompany(request);
        return "redirect:/company-added";
    }
}
