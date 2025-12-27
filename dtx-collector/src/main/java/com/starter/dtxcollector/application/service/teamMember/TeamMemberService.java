package com.starter.dtxcollector.application.service.teamMember;

import com.starter.dtxcollector.domain.model.TeamMember;
import com.starter.dtxcollector.domain.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TeamMemberService {

    private final TeamMemberRepository repository;

    public TeamMemberService(TeamMemberRepository repository) {
        this.repository = repository;
    }

    public List<TeamMember> findByTeamId(UUID teamId) {
        Objects.requireNonNull(teamId);
        return repository.findByTeam_Id(teamId);
    }
}
