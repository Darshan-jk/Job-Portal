package com.project.jobportal.repository;

import com.project.jobportal.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByNameContainingIgnoreCase(String name);

    List<Company> findByLocationContainingIgnoreCase(String location);

    List<Company> findByIndustryIgnoreCase(String industry);

    List<Company> findByRecruiterId(Long recruiterId);
}