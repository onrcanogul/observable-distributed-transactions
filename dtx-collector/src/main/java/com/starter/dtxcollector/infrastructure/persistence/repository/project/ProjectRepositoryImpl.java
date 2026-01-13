package com.starter.dtxcollector.infrastructure.persistence.repository.project;

import com.starter.dtxcollector.domain.model.Project;
import com.starter.dtxcollector.domain.repository.ProjectRepository;
import com.starter.dtxcollector.infrastructure.configuration.mapper.Mapper;
import com.starter.dtxcollector.infrastructure.persistence.entity.ProjectEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private final JpaProjectRepository jpaRepository;
    private final Mapper<ProjectEntity, Project> mapper;

    public ProjectRepositoryImpl(JpaProjectRepository jpaRepository, Mapper<ProjectEntity, Project> mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Project findById(UUID id) {
        return mapper
                .toDomain(jpaRepository
                        .findById(id)
                        .orElseThrow()
                );
    }

    @Override
    public List<Project> findByTeamId(UUID teamId) {
        return jpaRepository.findByTeamId(teamId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void save(Project project) {
        jpaRepository.save(mapper.toEntity(project));
    }
}
