package com.starter.dtxcollector.infrastructure.persistence.repository.project;

import com.starter.dtxcollector.infrastructure.persistence.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaProjectRepository extends JpaRepository<ProjectEntity, UUID> {
    List<ProjectEntity> findByTeamId(UUID teamId);
}
