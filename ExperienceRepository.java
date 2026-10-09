package com.linkedlite.backend.repository;

import com.linkedlite.backend.entity.experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository
        extends JpaRepository<experience, Integer> {

    List<experience> findByUserId(Integer userId);
}