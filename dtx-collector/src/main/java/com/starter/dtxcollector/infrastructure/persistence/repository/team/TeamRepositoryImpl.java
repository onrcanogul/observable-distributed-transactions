package com.starter.dtxcollector.infrastructure.persistence.repository.team;

import com.starter.dtxcollector.domain.model.Team;
import com.starter.dtxcollector.domain.repository.TeamRepository;
import com.starter.dtxcollector.infrastructure.configuration.mapper.Mapper;
import com.starter.dtxcollector.infrastructure.persistence.entity.TeamEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    private final JpaTeamRepository jpaRepository;
    private final Mapper<TeamEntity, Team> mapper;

    public TeamRepositoryImpl(JpaTeamRepository jpaRepository, Mapper<TeamEntity, Team> mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Team> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void save(Team team) {
        jpaRepository.save(mapper.toEntity(team));
    }
}
