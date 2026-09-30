package com.project.jobportal.controller;

import com.project.jobportal.entity.Application;
import com.project.jobportal.service.ApplicationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<Application>> getApplicationsByCandidate(
            @PathVariable Long candidateId) {
        return ResponseEntity.ok(
                applicationService.getApplicationsByCandidate(candidateId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsByJob(
            @PathVariable Long jobId) {
        return ResponseEntity.ok(
                applicationService.getApplicationsByJob(jobId));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Application>> getApplicationsByStatus(
            @RequestParam String status) {
        return ResponseEntity.ok(
                applicationService.getApplicationsByStatus(status));
    }

    @GetMapping("/job/{jobId}/status")
    public ResponseEntity<List<Application>> getApplicationsByJobAndStatus(
            @PathVariable Long jobId,
            @RequestParam String status) {
        return ResponseEntity.ok(
                applicationService.getApplicationsByJobAndStatus(jobId, status));
    }

    @PostMapping
    public ResponseEntity<Application> applyForJob(
            @RequestParam Long candidateId,
            @RequestParam Long jobId,
            @RequestParam(required = false) String coverLetter,
            @RequestParam(required = false) String resumeUrl) {
        return ResponseEntity.ok(
                applicationService.applyForJob(
                        candidateId, jobId, coverLetter, resumeUrl));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateApplicationStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(
                applicationService.updateApplicationStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> withdrawApplication(@PathVariable Long id) {
        applicationService.withdrawApplication(id);
        return ResponseEntity.noContent().build();
    }
}