package com.lewyonq.isit.service;

import com.lewyonq.isit.model.Company;
import com.lewyonq.isit.model.NewCompanyRequest;
import com.lewyonq.isit.model.User;
import com.lewyonq.isit.repository.CompanyRepository;
import com.lewyonq.isit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public void createNewCompany(NewCompanyRequest request,
                                 @AuthenticationPrincipal User owner) {

        Company company = Company.builder()
                .name(request.getCompanyName())
                .maturityLevel(0)
                .projects(new ArrayList<>())
                .users(new ArrayList<>())
                .build();

        owner.setCompany(company);
        companyRepository.save(company);
    }

//    public void addCompanyToOwnerAccount(Company company, User user) {
//        companyRepository.addCompanyToOwnerAccount(company, user.getEmail());
//    }

    public Company findByID(Long id) throws Exception {
        return companyRepository.findById(id)
                .orElseThrow(() -> new Exception("Company not found"));
    }

    public Company findByName(String name) throws Exception {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new Exception("Company not found"));
    }

    //todo: add project to projects in company
}
