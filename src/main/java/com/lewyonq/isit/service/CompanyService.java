package com.lewyonq.isit.service;

import com.lewyonq.isit.model.Company;
import com.lewyonq.isit.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company addCompany(String companyName) {
        Company company = Company.builder()
                .name(companyName)
                .build();
        companyRepository.save(company);

        return company;
    }

    public Company findByID(Long id) throws Exception {
        return companyRepository.findById(id)
                .orElseThrow(() -> new Exception("Company not found"));
    }
}
