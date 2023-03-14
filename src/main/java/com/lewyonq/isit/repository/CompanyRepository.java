package com.lewyonq.isit.repository;

import com.lewyonq.isit.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findById(Long id);
    Optional<Company> findByName(String name);

//    @Query("UPDATE User u SET u.company = :company WHERE u.email = :email")
//    void addCompanyToOwnerAccount(Company company, String email);
}
