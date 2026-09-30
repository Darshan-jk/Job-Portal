package com.project.jobportal.service;

import com.project.jobportal.entity.JobCategory;
import com.project.jobportal.repository.JobCategoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobCategoryService {

    private final JobCategoryRepository jobCategoryRepository;

    public JobCategoryService(JobCategoryRepository jobCategoryRepository) {
        this.jobCategoryRepository = jobCategoryRepository;
    }

    public List<JobCategory> getAllCategories() {
        return jobCategoryRepository.findAll();
    }

    public JobCategory getCategoryById(Long id) {
        return jobCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
    }

    public JobCategory getCategoryByName(String name) {
        return jobCategoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("Category not found: " + name));
    }

    public JobCategory createCategory(JobCategory category) {

        if (jobCategoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new RuntimeException("Category already exists");
        }

        category.setId(null);

        return jobCategoryRepository.save(category);
    }

    public JobCategory updateCategory(Long id, JobCategory updatedCategory) {

        JobCategory existingCategory = getCategoryById(id);

        if (jobCategoryRepository.existsByNameIgnoreCase(updatedCategory.getName())
                && !existingCategory.getName().equalsIgnoreCase(updatedCategory.getName())) {
            throw new RuntimeException("Category name already exists");
        }

        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());

        return jobCategoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        JobCategory category = getCategoryById(id);
        jobCategoryRepository.delete(category);
    }
}