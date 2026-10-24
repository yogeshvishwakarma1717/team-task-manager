package com.taskmanager.controller;

import com.taskmanager.model.TeamMember;
import com.taskmanager.service.TeamMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    public TeamMemberController(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    @GetMapping
    public List<TeamMember> getAllMembers() {
        return teamMemberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public TeamMember getMemberById(@PathVariable Long id) {
        return teamMemberService.getMemberById(id);
    }

    @PostMapping
    public TeamMember createMember(@RequestBody TeamMember member) {
        return teamMemberService.createMember(member);
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Long id) {
        teamMemberService.deleteMember(id);
        return "Team member deleted successfully";
    }
}