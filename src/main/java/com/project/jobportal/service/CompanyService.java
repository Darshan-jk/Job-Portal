package com.project.jobportal.service;

import com.project.jobportal.entity.Company;
import com.project.jobportal.entity.User;
import com.project.jobportal.repository.CompanyRepository;
import com.project.jobportal.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(
            CompanyRepository companyRepository,
            UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + id));
    }

    public List<Company> searchByName(String name) {
        return companyRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Company> searchByLocation(String location) {
        return companyRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Company> getByIndustry(String industry) {
        return companyRepository.findByIndustryIgnoreCase(industry);
    }

    public List<Company> getByRecruiter(Long recruiterId) {
        return companyRepository.findByRecruiterId(recruiterId);
    }

    public Company createCompany(Company company, Long recruiterId) {

        User recruiter = userRepository.findById(recruiterId)
                .orElseThrow(() -> new RuntimeException("Recruiter not found with ID: " + recruiterId));

        company.setId(null);
        company.setRecruiter(recruiter);

        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company updatedCompany) {

        Company existingCompany = getCompanyById(id);

        existingCompany.setName(updatedCompany.getName());
        existingCompany.setDescription(updatedCompany.getDescription());
        existingCompany.setIndustry(updatedCompany.getIndustry());
        existingCompany.setLocation(updatedCompany.getLocation());
        existingCompany.setWebsite(updatedCompany.getWebsite());
        existingCompany.setLogoUrl(updatedCompany.getLogoUrl());

        return companyRepository.save(existingCompany);
    }

    public void deleteCompany(Long id) {
        Company company = getCompanyById(id);
        companyRepository.delete(company);
    }
}