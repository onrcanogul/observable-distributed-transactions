package com.starter.dtxcollector.presentation.api;

import com.starter.dtxcollector.application.service.team.TeamService;
import com.starter.dtxcollector.domain.model.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/team")
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(Team team) {
        service.save(team);
        return ResponseEntity.status(201).build();
    }
}
