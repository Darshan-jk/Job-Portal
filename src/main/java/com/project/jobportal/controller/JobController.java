package com.project.jobportal.controller;

import com.project.jobportal.entity.Job;
import com.project.jobportal.service.JobService;

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
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchByTitle(
            @RequestParam String title) {
        return ResponseEntity.ok(jobService.searchByTitle(title));
    }

    @GetMapping("/location")
    public ResponseEntity<List<Job>> searchByLocation(
            @RequestParam String location) {
        return ResponseEntity.ok(jobService.searchByLocation(location));
    }

    @GetMapping("/type")
    public ResponseEntity<List<Job>> getByJobType(
            @RequestParam String jobType) {
        return ResponseEntity.ok(jobService.getByJobType(jobType));
    }

    @GetMapping("/experience")
    public ResponseEntity<List<Job>> getByExperienceLevel(
            @RequestParam String experienceLevel) {
        return ResponseEntity.ok(jobService.getByExperienceLevel(experienceLevel));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Job>> getByStatus(
            @RequestParam String status) {
        return ResponseEntity.ok(jobService.getByStatus(status));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Job>> getByCompany(
            @PathVariable Long companyId) {
        return ResponseEntity.ok(jobService.getByCompany(companyId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Job>> getByCategory(
            @PathVariable Long categoryId) {
        return ResponseEntity.ok(jobService.getByCategory(categoryId));
    }

    @GetMapping("/company/{companyId}/status")
    public ResponseEntity<List<Job>> getByCompanyAndStatus(
            @PathVariable Long companyId,
            @RequestParam String status) {
        return ResponseEntity.ok(
                jobService.getByCompanyAndStatus(companyId, status));
    }

    @PostMapping
    public ResponseEntity<Job> createJob(
            @RequestParam Long companyId,
            @RequestParam Long categoryId,
            @RequestBody Job job) {
        return ResponseEntity.ok(
                jobService.createJob(job, companyId, categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(
            @PathVariable Long id,
            @RequestParam Long companyId,
            @RequestParam Long categoryId,
            @RequestBody Job job) {
        return ResponseEntity.ok(
                jobService.updateJob(id, job, companyId, categoryId));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Job> updateJobStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                jobService.updateJobStatus(id, status)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}