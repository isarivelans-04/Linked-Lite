package com.linkedlite.backend.service;

import com.linkedlite.backend.entity.experience;

import java.util.List;

public interface ExperienceService {

    experience addExperience(
            experience experience,
            String loggedInEmail
    );

    List<experience> getExperiencesByUserId(
            Integer userId
    );

    void deleteExperience(
            Integer experienceId,
            String loggedInEmail
    );
    experience updateExperience(
            Integer experienceId,
            experience updatedExperience,
            String loggedInEmail
    );
}
