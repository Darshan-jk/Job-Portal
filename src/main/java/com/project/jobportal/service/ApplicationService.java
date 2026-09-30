package com.project.jobportal.service;

import com.project.jobportal.entity.Application;
import com.project.jobportal.entity.User;
import com.project.jobportal.entity.Job;
import com.project.jobportal.repository.ApplicationRepository;
import com.project.jobportal.repository.UserRepository;
import com.project.jobportal.repository.JobRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            UserRepository userRepository,
            JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with ID: " + id));
    }

    public List<Application> getApplicationsByCandidate(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId);
    }

    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    public List<Application> getApplicationsByStatus(String status) {
        return applicationRepository.findByStatusIgnoreCase(status);
    }

    public List<Application> getApplicationsByJobAndStatus(Long jobId, String status) {
        return applicationRepository.findByJobIdAndStatusIgnoreCase(jobId, status);
    }

    public Application applyForJob(
            Long candidateId,
            Long jobId,
            String coverLetter,
            String resumeUrl) {

        if (applicationRepository.existsByCandidateIdAndJobId(candidateId, jobId)) {
            throw new RuntimeException("You have already applied for this job");
        }

        User candidate = userRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + candidateId));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + jobId));

        Application application = new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setCoverLetter(coverLetter);
        application.setResumeUrl(resumeUrl);
        application.setStatus("PENDING");

        return applicationRepository.save(application);
    }

    public Application updateApplicationStatus(Long id, String status) {
        Application application = getApplicationById(id);
        application.setStatus(status);
        return applicationRepository.save(application);
    }

    public void withdrawApplication(Long id) {
        Application application = getApplicationById(id);
        applicationRepository.delete(application);
    }
}