package com.taskmanager.repository;

import com.taskmanager.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
}