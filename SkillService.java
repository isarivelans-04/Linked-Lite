package com.linkedlite.backend.service;

import com.linkedlite.backend.entity.Skill;
import java.util.List;

public interface SkillService {

    Skill addSkill(Skill skill, String loggedInEmail);

    List<Skill> getSkillsByUserId(Integer userId);

    void deleteSkill(Integer skillId, String loggedInEmail);
}
