package com.starter.dtxcollector.application.service.project;

import com.starter.dtxcollector.domain.model.Project;
import com.starter.dtxcollector.domain.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> findByTeam(UUID teamId) {
        if (teamId == null) throw new RuntimeException();
        return repository.findByTeamId(teamId);
    }

    public void save(Project project) {
        repository.save(project);
    }
}
