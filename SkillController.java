package com.linkedlite.backend.controller;

import com.linkedlite.backend.entity.Skill;
import com.linkedlite.backend.service.SkillService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public Skill addSkill(@RequestBody Skill skill) {

        String loggedInEmail = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return skillService.addSkill(skill, loggedInEmail);
    }

    @GetMapping("/user/{userId}")
    public List<Skill> getSkillsByUserId(@PathVariable Integer userId) {
        return skillService.getSkillsByUserId(userId);
    }

    @DeleteMapping("/{skillId}")
    public String deleteSkill(@PathVariable Integer skillId) {

        String loggedInEmail = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        skillService.deleteSkill(skillId, loggedInEmail);

        return "Skill deleted successfully";
    }
}
