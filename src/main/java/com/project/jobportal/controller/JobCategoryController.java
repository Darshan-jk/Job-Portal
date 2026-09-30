package com.project.jobportal.controller;

import com.project.jobportal.entity.JobCategory;
import com.project.jobportal.service.JobCategoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class JobCategoryController {

    private final JobCategoryService jobCategoryService;

    public JobCategoryController(JobCategoryService jobCategoryService) {
        this.jobCategoryService = jobCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<JobCategory>> getAllCategories() {
        return ResponseEntity.ok(jobCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(jobCategoryService.getCategoryById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<JobCategory> getCategoryByName(
            @RequestParam String name) {
        return ResponseEntity.ok(jobCategoryService.getCategoryByName(name));
    }

    // Create category
    @PostMapping
    public ResponseEntity<JobCategory> createCategory(
            @RequestBody JobCategory category) {
        return ResponseEntity.ok(jobCategoryService.createCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobCategory> updateCategory(
            @PathVariable Long id,
            @RequestBody JobCategory category) {
        return ResponseEntity.ok(
                jobCategoryService.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        jobCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}