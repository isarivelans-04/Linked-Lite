package com.linkedlite.backend.serviceimpl;

import com.linkedlite.backend.entity.experience;
import com.linkedlite.backend.entity.User;
import com.linkedlite.backend.exception.UnauthorizedException;
import com.linkedlite.backend.repository.ExperienceRepository;
import com.linkedlite.backend.repository.UserRepository;
import com.linkedlite.backend.service.ExperienceService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl
        implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    public ExperienceServiceImpl(
            ExperienceRepository experienceRepository,
            UserRepository userRepository) {

        this.experienceRepository =
                experienceRepository;

        this.userRepository =
                userRepository;
    }

    // =========================
    // ADD EXPERIENCE
    // =========================

    @Override
    public experience addExperience(
            experience experience,
            String loggedInEmail) {

        User user =
                userRepository.findByEmail(loggedInEmail)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        experience.setUserId(
                user.getUserId()
        );

        return experienceRepository.save(
                experience
        );
    }


    // =========================
    // GET EXPERIENCE
    // =========================

    @Override
    public List<experience> getExperiencesByUserId(
            Integer userId) {

        return experienceRepository
                .findByUserId(userId);
    }


    // =========================
    // UPDATE EXPERIENCE
    // =========================

    @Override
    public experience updateExperience(
            Integer experienceId,
            experience updatedExperience,
            String loggedInEmail) {

        // Find existing experience

        experience existingExperience =
                experienceRepository
                        .findById(experienceId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Experience not found"
                                )
                        );


        // Find logged-in user

        User user =
                userRepository.findByEmail(loggedInEmail)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );


        // Check ownership

        if (!user.getUserId().equals(
                existingExperience.getUserId())) {

            throw new UnauthorizedException(
                    "You can update only your own experience"
            );
        }


        // Update job title

        existingExperience.setJobTitle(
                updatedExperience.getJobTitle()
        );


        // Update company

        existingExperience.setCompany(
                updatedExperience.getCompany()
        );


        // Update location

        existingExperience.setLocation(
                updatedExperience.getLocation()
        );


        // Update start date

        existingExperience.setStartDate(
                updatedExperience.getStartDate()
        );


        // Update end date

        existingExperience.setEndDate(
                updatedExperience.getEndDate()
        );


        // Save updated experience

        return experienceRepository.save(
                existingExperience
        );
    }


    // =========================
    // DELETE EXPERIENCE
    // =========================

    @Override
    public void deleteExperience(
            Integer experienceId,
            String loggedInEmail) {

        experience experience =
                experienceRepository
                        .findById(experienceId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Experience not found"
                                )
                        );

        User user =
                userRepository.findByEmail(loggedInEmail)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        if (!user.getUserId().equals(
                experience.getUserId())) {

            throw new UnauthorizedException(
                    "You can delete only your own experience"
            );
        }

        experienceRepository.deleteById(
                experienceId
        );
    }
}