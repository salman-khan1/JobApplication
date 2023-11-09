package com.example.firstjobapp.Company.service;

import com.example.firstjobapp.Company.entity.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {

     List<Company> getAllCompanies();

     void createCompany (Company company);
     boolean updateCompany (Long id, Company updatedCompany);
     boolean deleteCompanyById (Long id);
     Company getCompanyById(Long id);
}
