package com.starter.dtxcollector.domain.repository;

import com.starter.dtxcollector.domain.model.Team;

import java.util.List;
import java.util.UUID;


public interface TeamRepository {
    List<Team> findByUserId(UUID userId);
    void save(Team team);
}
