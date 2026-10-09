package com.linkedlite.backend.controller;

import com.linkedlite.backend.entity.experience;
import com.linkedlite.backend.service.ExperienceService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(
            ExperienceService experienceService) {

        this.experienceService =
                experienceService;
    }


    // =========================
    // ADD EXPERIENCE
    // =========================

    @PostMapping
    public experience addExperience(
            @RequestBody experience experience) {

        String loggedInEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return experienceService.addExperience(
                experience,
                loggedInEmail
        );
    }


    // =========================
    // GET EXPERIENCE
    // =========================

    @GetMapping("/user/{userId}")
    public List<experience> getExperiencesByUserId(
            @PathVariable Integer userId) {

        return experienceService
                .getExperiencesByUserId(userId);
    }


    // =========================
    // UPDATE EXPERIENCE
    // =========================

    @PutMapping("/{experienceId}")
    public experience updateExperience(
            @PathVariable Integer experienceId,
            @RequestBody experience experience) {

        String loggedInEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return experienceService.updateExperience(
                experienceId,
                experience,
                loggedInEmail
        );
    }


    // =========================
    // DELETE EXPERIENCE
    // =========================

    @DeleteMapping("/{experienceId}")
    public String deleteExperience(
            @PathVariable Integer experienceId) {

        String loggedInEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        experienceService.deleteExperience(
                experienceId,
                loggedInEmail
        );

        return "Experience deleted successfully";
    }
}