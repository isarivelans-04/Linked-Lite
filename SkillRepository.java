package com.linkedlite.backend.repository;

import com.linkedlite.backend.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

    List<Skill> findByUserId(Integer userId);
}
