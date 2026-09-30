package com.project.jobportal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="candidate_profiles")
public class CandidateProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = false, unique = true)
	private User user;
	
	@Column(length = 200)
	private String headline;
	
	@Column(length = 100)
	private String location;
	
	@Column(length = 255)
	private String education;
	
	@Column(columnDefinition = "TEXT")
	private String skills;
	
	private Integer experience;
	
	@Column(columnDefinition = "TEXT")
	private String bio;
	
	@Column(length = 500)
	private String resumeUrl;
	
	@Column(length = 500)
	private String profileImage;
	
	public CandidateProfile() {
		
	}

	public CandidateProfile(Long id, User user, String headline, String location, String education, String skills,
			Integer experience, String bio, String resumeUrl, String profileImage) {
		super();
		this.id = id;
		this.user = user;
		this.headline = headline;
		this.location = location;
		this.education = education;
		this.skills = skills;
		this.experience = experience;
		this.bio = bio;
		this.resumeUrl = resumeUrl;
		this.profileImage = profileImage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getResumeUrl() {
		return resumeUrl;
	}

	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	

}
