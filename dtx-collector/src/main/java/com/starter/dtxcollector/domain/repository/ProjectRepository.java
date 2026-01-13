package com.starter.dtxcollector.domain.repository;

import com.starter.dtxcollector.domain.model.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository {
    Project findById(UUID id);
    List<Project> findByTeamId(UUID teamId);
    void save(Project project);
}
