package com.starter.dtxcollector.infrastructure.persistence.entity;

import com.starter.dtxcollector.domain.model.TeamMember;
import com.starter.dtxcollector.infrastructure.persistence.entity.base.EntityMarker;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "team_member")
public class TeamMemberEntity implements EntityMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    private TeamEntity team;

    @ManyToOne(optional = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private TeamMember.TeamRole role;

    private Instant joinedAt;

    public TeamMemberEntity(UUID id, Instant joinedAt, TeamMember.TeamRole role, TeamEntity team, UserEntity user) {
        this.id = id;
        this.joinedAt = joinedAt;
        this.role = role;
        this.team = team;
        this.user = user;
    }

    public TeamMemberEntity() {

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

    public TeamMember.TeamRole getRole() {
        return role;
    }

    public void setRole(TeamMember.TeamRole role) {
        this.role = role;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

