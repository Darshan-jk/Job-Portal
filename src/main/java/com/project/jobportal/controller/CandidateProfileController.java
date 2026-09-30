package com.project.jobportal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.jobportal.entity.CandidateProfile;
import com.project.jobportal.service.CandidateProfileService;

@RestController
@RequestMapping("/api/candidate-profiles")
public class CandidateProfileController {

    private final CandidateProfileService profileService;

    public CandidateProfileController(CandidateProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<CandidateProfile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    // Get profile by profile ID
    @GetMapping("/{id}")
    public ResponseEntity<CandidateProfile> getProfileById(
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    // Get profile by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<CandidateProfile> getProfileByUserId(
            @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(profileService.getProfileByUserId(userId));
    }

    // Create profile for a user
    @PostMapping("/user/{userId}")
    public ResponseEntity<CandidateProfile> createProfile(
            @PathVariable("userId") Long userId,
            @RequestBody CandidateProfile profile) {

        CandidateProfile createdProfile =
                profileService.createProfile(userId, profile);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
    }

    // Update profile by profile ID
    @PutMapping("/{id}")
    public ResponseEntity<CandidateProfile> updateProfile(
            @PathVariable("id") Long id,
            @RequestBody CandidateProfile profile) {
        return ResponseEntity.ok(profileService.updateProfile(id, profile));
    }

    // Delete profile by profile ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable("id") Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok("Candidate profile deleted successfully");
    }
}