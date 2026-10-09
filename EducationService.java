package com.linkedlite.backend.service;

import com.linkedlite.backend.entity.Education;

import java.util.List;

public interface EducationService {

    Education addEducation(
            Education education,
            String loggedInEmail
    );

    List<Education> getEducationByUserId(
            Integer userId
    );

    Education updateEducation(
            Integer educationId,
            Education education,
            String loggedInEmail
    );

    void deleteEducation(
            Integer educationId,
            String loggedInEmail
    );
}