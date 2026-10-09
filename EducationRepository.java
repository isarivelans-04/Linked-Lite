package com.linkedlite.backend.repository;

import com.linkedlite.backend.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository
        extends JpaRepository<Education, Integer> {

    List<Education> findByUserId(Integer userId);
}