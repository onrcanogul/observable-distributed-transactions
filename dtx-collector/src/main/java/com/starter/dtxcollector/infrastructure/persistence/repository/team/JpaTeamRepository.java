package com.starter.dtxcollector.infrastructure.persistence.repository.team;

import com.starter.dtxcollector.infrastructure.persistence.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaTeamRepository extends JpaRepository<TeamEntity, UUID> {
    List<TeamEntity> findByUserId(UUID userId);
}
