package com.project.jobportal.controller;

import com.project.jobportal.entity.SavedJob;
import com.project.jobportal.service.SavedJobService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/saved-jobs")
public class SavedJobController {

    private final SavedJobService savedJobService;

    public SavedJobController(SavedJobService savedJobService) {
        this.savedJobService = savedJobService;
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<SavedJob>> getSavedJobsByCandidate(
            @PathVariable Long candidateId) {
        return ResponseEntity.ok(
                savedJobService.getSavedJobsByCandidate(candidateId));
    }

    @PostMapping
    public ResponseEntity<SavedJob> saveJob(
            @RequestParam Long candidateId,
            @RequestParam Long jobId) {
        return ResponseEntity.ok(
                savedJobService.saveJob(candidateId, jobId));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeSavedJob(
            @RequestParam Long candidateId,
            @RequestParam Long jobId) {
        savedJobService.removeSavedJob(candidateId, jobId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> isJobSaved(
            @RequestParam Long candidateId,
            @RequestParam Long jobId) {
        return ResponseEntity.ok(
                savedJobService.isJobSaved(candidateId, jobId));
    }
}