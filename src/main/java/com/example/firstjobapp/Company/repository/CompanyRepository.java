package com.example.firstjobapp.Company.repository;

import com.example.firstjobapp.Company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
