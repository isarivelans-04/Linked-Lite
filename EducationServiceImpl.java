package com.linkedlite.backend.service.impl;

import com.linkedlite.backend.entity.Education;
import com.linkedlite.backend.entity.User;
import com.linkedlite.backend.repository.EducationRepository;
import com.linkedlite.backend.repository.UserRepository;
import com.linkedlite.backend.service.EducationService;
import com.linkedlite.backend.exception.UnauthorizedException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public EducationServiceImpl(
            EducationRepository educationRepository,
            UserRepository userRepository) {

        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Education addEducation(
            Education education,
            String loggedInEmail) {

        User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getUserId().equals(education.getUserId())) {
            throw new UnauthorizedException(
                    "You can add education only to your own profile");
        }

        return educationRepository.save(education);
    }

    @Override
    public List<Education> getEducationByUserId(
            Integer userId) {

        return educationRepository.findByUserId(userId);
    }

    @Override
    public Education updateEducation(
            Integer educationId,
            Education education,
            String loggedInEmail) {

        Education existingEducation =
                educationRepository.findById(educationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Education not found"));

        User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getUserId().equals(existingEducation.getUserId())) {
            throw new UnauthorizedException(
                    "You can update only your own education");
        }

        existingEducation.setInstitution(
                education.getInstitution());

        existingEducation.setDegree(
                education.getDegree());

        existingEducation.setFieldOfStudy(
                education.getFieldOfStudy());

        existingEducation.setStartDate(
                education.getStartDate());

        existingEducation.setEndDate(
                education.getEndDate());

        return educationRepository.save(existingEducation);
    }

    @Override
    public void deleteEducation(
            Integer educationId,
            String loggedInEmail) {

        Education existingEducation =
                educationRepository.findById(educationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Education not found"));

        User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getUserId().equals(existingEducation.getUserId())) {
            throw new UnauthorizedException(
                    "You can delete only your own education");
        }

        educationRepository.deleteById(educationId);
    }
}
