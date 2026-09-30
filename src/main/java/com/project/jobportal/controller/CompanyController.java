package com.project.jobportal.controller;

import com.project.jobportal.entity.Company;
import com.project.jobportal.service.CompanyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Company>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(companyService.searchByName(name));
    }

    @GetMapping("/location")
    public ResponseEntity<List<Company>> searchByLocation(
            @RequestParam String location) {
        return ResponseEntity.ok(companyService.searchByLocation(location));
    }

    @GetMapping("/industry")
    public ResponseEntity<List<Company>> getByIndustry(@RequestParam String industry) {
        return ResponseEntity.ok(companyService.getByIndustry(industry));
    }

    @GetMapping("/recruiter/{recruiterId}")
    public ResponseEntity<List<Company>> getByRecruiter(@PathVariable Long recruiterId) {
        return ResponseEntity.ok(companyService.getByRecruiter(recruiterId));
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(
            @RequestParam Long recruiterId,
            @RequestBody Company company) {
        return ResponseEntity.ok(companyService.createCompany(company, recruiterId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(
            @PathVariable Long id,
            @RequestBody Company company) {
        return ResponseEntity.ok(companyService.updateCompany(id, company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}