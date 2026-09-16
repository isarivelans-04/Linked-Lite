package com.linkedlite.backend.serviceimpl;

import com.linkedlite.backend.entity.Skill;
import com.linkedlite.backend.entity.User;
import com.linkedlite.backend.exception.UnauthorizedException;
import com.linkedlite.backend.repository.SkillRepository;
import com.linkedlite.backend.service.SkillService;
import org.springframework.stereotype.Service;
import com.linkedlite.backend.repository.UserRepository;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillServiceImpl(
            SkillRepository skillRepository,
            UserRepository userRepository) {

        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Skill addSkill(Skill skill, String loggedInEmail) {

        User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getUserId().equals(skill.getUserId())) {
            throw new UnauthorizedException(
                    "You can add skills only to your own profile"
            );
        }

        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getSkillsByUserId(Integer userId) {
        return skillRepository.findByUserId(userId);
    }

    @Override
    public void deleteSkill(Integer skillId, String loggedInEmail) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getUserId().equals(skill.getUserId())) {
            throw new UnauthorizedException(
                    "You can delete only your own skills"
            );
        }

        skillRepository.deleteById(skillId);
    }
}