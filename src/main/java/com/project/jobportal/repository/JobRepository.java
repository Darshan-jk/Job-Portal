package com.project.jobportal.repository;

import com.project.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByTitleContainingIgnoreCase(String title);

    List<Job> findByLocationContainingIgnoreCase(String location);

    List<Job> findByJobTypeIgnoreCase(String jobType);

    List<Job> findByExperienceLevelIgnoreCase(String experienceLevel);

    List<Job> findByStatusIgnoreCase(String status);

    List<Job> findByCompanyId(Long companyId);

    List<Job> findByCategoryId(Long categoryId);

    List<Job> findByCompanyIdAndStatusIgnoreCase(Long companyId, String status);
}