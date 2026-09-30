package com.project.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jobportal.entity.CandidateProfile;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Long> {
	Optional<CandidateProfile> findByUserId(Long userId);
	
	boolean existsByUserId(Long userId);
}
