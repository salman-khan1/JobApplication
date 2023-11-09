package com.example.firstjobapp.Company.controller;

import com.example.firstjobapp.Company.entity.Company;
import com.example.firstjobapp.Company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<List<Company>>getAllCompanies(){
        List<Company> companyList = companyService.getAllCompanies ();

        if (companyList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList()); // Return an empty list with a 404 status code
        } else {
            return ResponseEntity.ok(companyList);
        }
    }

    @PostMapping("/companies")
    public ResponseEntity<String> addCompany(@RequestBody Company company){
         companyService.createCompany(company);
         return new  ResponseEntity<>("Company Added Successfully",HttpStatus.CREATED);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
        companyService.updateCompany (id,company);
        return new  ResponseEntity<>("Company Updated Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean deleted= companyService.deleteCompanyById(id);
        if(deleted){
            return new ResponseEntity<> ("Job Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<> ("Company with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        Company company=companyService.getCompanyById (id);
        if(company!=null){
            return new ResponseEntity<> (company,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);

        }
    }

}
