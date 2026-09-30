package com.project.jobportal.service;

import com.project.jobportal.entity.SavedJob;
import com.project.jobportal.entity.User;
import com.project.jobportal.entity.Job;
import com.project.jobportal.repository.SavedJobRepository;
import com.project.jobportal.repository.UserRepository;
import com.project.jobportal.repository.JobRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobService {

    private final SavedJobRepository savedJobRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public SavedJobService(
            SavedJobRepository savedJobRepository,
            UserRepository userRepository,
            JobRepository jobRepository) {
        this.savedJobRepository = savedJobRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    public List<SavedJob> getSavedJobsByCandidate(Long candidateId) {
        return savedJobRepository.findByCandidateId(candidateId);
    }

    public SavedJob saveJob(Long candidateId, Long jobId) {

        if (savedJobRepository.existsByCandidateIdAndJobId(candidateId, jobId)) {
            throw new RuntimeException("Job is already saved");
        }

        User candidate = userRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + candidateId));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + jobId));

        SavedJob savedJob = new SavedJob();
        savedJob.setCandidate(candidate);
        savedJob.setJob(job);

        return savedJobRepository.save(savedJob);
    }

    public void removeSavedJob(Long candidateId, Long jobId) {

        SavedJob savedJob = savedJobRepository
                .findByCandidateIdAndJobId(candidateId, jobId)
                .orElseThrow(() -> new RuntimeException("Saved job not found"));

        savedJobRepository.delete(savedJob);
    }

    public boolean isJobSaved(Long candidateId, Long jobId) {
        return savedJobRepository.existsByCandidateIdAndJobId(candidateId, jobId);
    }
}