package com.example.firstjobapp.Company.service.impl;

import com.example.firstjobapp.Company.entity.Company;
import com.example.firstjobapp.Company.repository.CompanyRepository;
import com.example.firstjobapp.Company.service.CompanyService;
import com.example.firstjobapp.Job.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public List<Company> getAllCompanies () {
        return companyRepository.findAll ();
    }

    @Override
    public void createCompany (Company company) {
         companyRepository.save (company);
    }

    @Override
    public boolean updateCompany (Long id, Company updatedCompany) {
        Optional<Company> companyOptional=companyRepository.findById (id);
        if(companyOptional.isPresent ()){
            Company company= companyOptional.get ();
            company.setName (updatedCompany.getName ());
            company.setDescription (updatedCompany.getDescription ());
            company.setJobs (updatedCompany.getJobs ());
            companyRepository.save (company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById (Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty ()) {
            return false;
        }
        companyRepository.deleteById(id);
        return true;
    }

    @Override
    public Company getCompanyById (Long id) {
        return companyRepository.findById (id).orElse (null);
    }
}
