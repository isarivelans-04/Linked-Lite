package com.linkedlite.backend.controller;

import com.linkedlite.backend.entity.Education;
import com.linkedlite.backend.service.EducationService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(
            EducationService educationService) {

        this.educationService = educationService;
    }

    @PostMapping
    public Education addEducation(
            @RequestBody Education education) {

        String loggedInEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return educationService.addEducation(
                education,
                loggedInEmail
        );
    }

    @GetMapping("/user/{userId}")
    public List<Education> getEducationByUserId(
            @PathVariable Integer userId) {

        return educationService
                .getEducationByUserId(userId);
    }

    @PutMapping("/{educationId}")
    public Education updateEducation(
            @PathVariable Integer educationId,
            @RequestBody Education education) {

        String loggedInEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return educationService.updateEducation(
                educationId,
                education,
                loggedInEmail
        );
    }

    @DeleteMapping("/{educationId}")
    public String deleteEducation(
            @PathVariable Integer educationId) {

        String loggedInEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        educationService.deleteEducation(
                educationId,
                loggedInEmail
        );

        return "Education deleted successfully";
    }
}
