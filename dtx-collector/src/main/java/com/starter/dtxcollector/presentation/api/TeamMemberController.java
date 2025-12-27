package com.starter.dtxcollector.presentation.api;

import com.starter.dtxcollector.application.service.teamMember.TeamMemberService;
import com.starter.dtxcollector.domain.model.TeamMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/team-member")
public class TeamMemberController {

    private final TeamMemberService service;

    public TeamMemberController(TeamMemberService service) {
        this.service = service;
    }

    @GetMapping("{teamId}")
    public ResponseEntity<List<TeamMember>> findByTeam(@PathVariable UUID teamId) {
        return ResponseEntity.ok(service.findByTeamId(teamId));
    }
}
