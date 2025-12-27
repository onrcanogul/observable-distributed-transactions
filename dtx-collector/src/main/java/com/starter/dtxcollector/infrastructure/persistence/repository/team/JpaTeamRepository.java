package com.starter.dtxcollector.infrastructure.persistence.repository.team;

import com.starter.dtxcollector.infrastructure.persistence.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTeamRepository extends JpaRepository<TeamEntity, UUID> {
}
