package com.lewyonq.isit.service;

import com.lewyonq.isit.model.Company;
import com.lewyonq.isit.model.CompanyRequest;
import com.lewyonq.isit.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public void addCompany(CompanyRequest request) {
        Company company = Company.builder()
                .name(request.getName())
                .build();
        companyRepository.save(company);
    }
}
