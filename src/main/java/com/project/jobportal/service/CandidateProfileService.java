package com.project.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.jobportal.entity.CandidateProfile;
import com.project.jobportal.entity.Role;
import com.project.jobportal.entity.User;
import com.project.jobportal.repository.CandidateProfileRepository;
import com.project.jobportal.repository.UserRepository;

@Service
public class CandidateProfileService {
	
	private final CandidateProfileRepository profileRepository;
	
	private final UserRepository userRepository;

	public CandidateProfileService(CandidateProfileRepository profileRepository, UserRepository userRepository) {
		this.profileRepository = profileRepository;
		this.userRepository = userRepository;
	}
	
	public List<CandidateProfile> getAllProfiles(){
		return profileRepository.findAll();
	}
	
	public CandidateProfile getProfileById(Long id) {
		return profileRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Canditate profile not found"));
	}
	
	public CandidateProfile getProfileByUserId(Long userId) {
		return profileRepository.findByUserId(userId)
				.orElseThrow(()-> new RuntimeException("Canditate profile not found"));
	}
	
	public CandidateProfile createProfile(Long userId, CandidateProfile profile) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		if(profileRepository.existsByUserId(userId)) {
			throw new RuntimeException("Profile already exists for this user");
		}
		
		user.setRole(Role.CANDIDATE);
		profile.setUser(user);
		
		return profileRepository.save(profile);
	}
	
	public CandidateProfile updateProfile(Long id, CandidateProfile updatedProfile) {
		
		CandidateProfile existingProfile = getProfileById(id);
		
		existingProfile.setHeadline(updatedProfile.getHeadline());
		existingProfile.setLocation(updatedProfile.getLocation());
		existingProfile.setEducation(updatedProfile.getEducation());
		existingProfile.setSkills(updatedProfile.getSkills());
		existingProfile.setExperience(updatedProfile.getExperience());
		existingProfile.setBio(updatedProfile.getBio());
		existingProfile.setResumeUrl(updatedProfile.getResumeUrl());
		existingProfile.setProfileImage(updatedProfile.getProfileImage());
		
		return profileRepository.save(existingProfile);
	}
	
	public void deleteProfile(Long id) {
		CandidateProfile profile = getProfileById(id);
		
		profileRepository.delete(profile);
	}
	
	

}
