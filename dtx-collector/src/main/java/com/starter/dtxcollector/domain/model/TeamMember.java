package com.starter.dtxcollector.domain.model;

import com.starter.dtxcollector.domain.model.base.DomainMarker;

import java.time.Instant;
import java.util.UUID;

public class TeamMember implements DomainMarker {
    private UUID id;

    private Team team;

    private User user;

    private TeamRole role;

    private Instant joinedAt;

    public enum TeamRole {
        OWNER,
        ADMIN,
        VIEWER
    }

    public TeamMember(UUID id, Instant joinedAt, TeamRole role, Team team, User user) {
        this.id = id;
        this.joinedAt = joinedAt;
        this.role = role;
        this.team = team;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }

    public TeamRole getRole() {
        return role;
    }

    public void setRole(TeamRole role) {
        this.role = role;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
