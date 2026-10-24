package com.taskmanager.service;

import com.taskmanager.model.TeamMember;
import com.taskmanager.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberService(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    public List<TeamMember> getAllMembers() {
        return teamMemberRepository.findAll();
    }

    public TeamMember getMemberById(Long id) {
        return teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team member not found"));
    }

    public TeamMember createMember(TeamMember member) {
        return teamMemberRepository.save(member);
    }

    public void deleteMember(Long id) {
        teamMemberRepository.deleteById(id);
    }
}