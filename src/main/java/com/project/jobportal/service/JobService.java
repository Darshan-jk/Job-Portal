package com.project.jobportal.service;

import com.project.jobportal.entity.Job;
import com.project.jobportal.entity.Company;
import com.project.jobportal.entity.JobCategory;
import com.project.jobportal.repository.JobRepository;
import com.project.jobportal.repository.CompanyRepository;
import com.project.jobportal.repository.JobCategoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final JobCategoryRepository jobCategoryRepository;

    public JobService(
    		JobRepository jobRepository,
            CompanyRepository companyRepository,
            JobCategoryRepository jobCategoryRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.jobCategoryRepository = jobCategoryRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + id));
    }

    public List<Job> searchByTitle(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Job> searchByLocation(String location) {
        return jobRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Job> getByJobType(String jobType) {
        return jobRepository.findByJobTypeIgnoreCase(jobType);
    }

    public List<Job> getByExperienceLevel(String experienceLevel) {
        return jobRepository.findByExperienceLevelIgnoreCase(experienceLevel);
    }

    public List<Job> getByStatus(String status) {
        return jobRepository.findByStatusIgnoreCase(status);
    }

    public List<Job> getByCompany(Long companyId) {
        return jobRepository.findByCompanyId(companyId);
    }

    public List<Job> getByCategory(Long categoryId) {
        return jobRepository.findByCategoryId(categoryId);
    }

    public List<Job> getByCompanyAndStatus(Long companyId, String status) {
        return jobRepository.findByCompanyIdAndStatusIgnoreCase(companyId, status);
    }

    public Job createJob(Job job, Long companyId, Long categoryId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + companyId));

        JobCategory category = jobCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

        job.setId(null);
        job.setCompany(company);
        job.setCategory(category);

        if (job.getStatus() == null || job.getStatus().isBlank()) {
            job.setStatus("OPEN");
        }

        return jobRepository.save(job);
    }

    public Job updateJob(Long id, Job updatedJob, Long companyId, Long categoryId) {

        Job existingJob = getJobById(id);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + companyId));

        JobCategory category = jobCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setRequirements(updatedJob.getRequirements());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setSalaryMin(updatedJob.getSalaryMin());
        existingJob.setSalaryMax(updatedJob.getSalaryMax());
        existingJob.setJobType(updatedJob.getJobType());
        existingJob.setExperienceLevel(updatedJob.getExperienceLevel());
        existingJob.setOpenings(updatedJob.getOpenings());
        existingJob.setStatus(updatedJob.getStatus());
        existingJob.setCompany(company);
        existingJob.setCategory(category);

        return jobRepository.save(existingJob);
    }
    
    public Job updateJobStatus(Long id, String status) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Job not found with id: " + id)
                );

        job.setStatus(status);

        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        Job job = getJobById(id);
        jobRepository.delete(job);
    }
}