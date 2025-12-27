package com.starter.dtxcollector.presentation.api;

import com.starter.dtxcollector.application.service.project.ProjectService;
import com.starter.dtxcollector.domain.model.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("{teamId}")
    public ResponseEntity<List<Project>> findByTeam(@PathVariable UUID teamId) {
        return ResponseEntity.ok(service.findByTeam(teamId));
    }

    @PostMapping
    public ResponseEntity<Void> save(Project project) {
        service.save(project);
        return ResponseEntity.status(201).build();
    }
}
