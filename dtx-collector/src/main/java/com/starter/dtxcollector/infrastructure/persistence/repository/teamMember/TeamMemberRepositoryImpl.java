package com.starter.dtxcollector.infrastructure.persistence.repository.teamMember;

import com.starter.dtxcollector.domain.model.TeamMember;
import com.starter.dtxcollector.domain.repository.TeamMemberRepository;
import com.starter.dtxcollector.infrastructure.configuration.mapper.Mapper;
import com.starter.dtxcollector.infrastructure.persistence.entity.TeamMemberEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TeamMemberRepositoryImpl implements TeamMemberRepository {
    private final JpaTeamMemberRepository jpaRepository;
    private final Mapper<TeamMemberEntity, TeamMember> mapper;

    public TeamMemberRepositoryImpl(JpaTeamMemberRepository jpaRepository, Mapper<TeamMemberEntity, TeamMember> mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TeamMember> findByTeam_Id(UUID teamId) {
         return jpaRepository.findByTeam_Id(teamId).stream()
                 .map(mapper::toDomain)
                 .toList();
    }
}
