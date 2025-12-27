package com.starter.dtxcollector.infrastructure.persistence.entity;

import com.starter.dtxcollector.infrastructure.persistence.entity.base.EntityMarker;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "project",
        indexes = {
                @Index(name = "idx_team_id", columnList = "team_id")
        })
public class ProjectEntity implements EntityMarker {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "created_by")
    private UUID createdBy;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "environment")
    private String environment;
    @Column(name = "team_id")
    private UUID teamId;

    public ProjectEntity(
            String name,
            String description,
            String environment,
            UUID createdBy,
            boolean enabled, UUID teamId
    ) {
        this.teamId = teamId;
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.environment = environment;
        this.createdBy = createdBy;
        this.createdAt = Instant.now();
        this.enabled = enabled;
    }

    public ProjectEntity() {

    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }
        this.name = newName;
    }

    public void changeDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }
        this.description = description;
    }

    public void disable() {
        this.enabled = false;
    }

    public void enable() {
        this.enabled = true;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getEnvironment() {
        return environment;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UUID getTeamId() {
        return teamId;
    }
}
