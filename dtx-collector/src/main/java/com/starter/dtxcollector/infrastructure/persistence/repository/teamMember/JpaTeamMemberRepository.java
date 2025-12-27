package com.starter.dtxcollector.infrastructure.persistence.repository.teamMember;

import com.starter.dtxcollector.infrastructure.persistence.entity.TeamMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaTeamMemberRepository extends JpaRepository<TeamMemberEntity, UUID> {
    List<TeamMemberEntity> findByTeam_Id(UUID id);
}
