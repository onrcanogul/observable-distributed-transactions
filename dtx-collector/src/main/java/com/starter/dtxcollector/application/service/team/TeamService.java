package com.starter.dtxcollector.application.service.team;

import com.starter.dtxcollector.domain.model.Team;
import com.starter.dtxcollector.domain.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TeamService {

    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public void save(Team team) {
        Objects.requireNonNull(team);
        repository.save(team);
    }
}
