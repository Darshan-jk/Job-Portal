package com.project.jobportal.repository;

import com.project.jobportal.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByCandidateId(Long candidateId);

    List<Application> findByJobId(Long jobId);

    List<Application> findByStatusIgnoreCase(String status);

    Optional<Application> findByCandidateIdAndJobId(Long candidateId, Long jobId);

    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);

    List<Application> findByJobIdAndStatusIgnoreCase(Long jobId, String status);
}