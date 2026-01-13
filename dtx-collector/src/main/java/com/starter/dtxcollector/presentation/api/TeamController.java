package com.starter.dtxcollector.presentation.api;

import com.starter.dtxcollector.application.service.team.TeamService;
import com.starter.dtxcollector.domain.model.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/team")
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Team>> findByUser(@RequestParam UUID userId) {
        return ResponseEntity.ok(service.findByUser(userId));
    }

    @PostMapping
    public ResponseEntity<Void> create(Team team) {
        service.save(team);
        return ResponseEntity.status(201).build();
    }
}
