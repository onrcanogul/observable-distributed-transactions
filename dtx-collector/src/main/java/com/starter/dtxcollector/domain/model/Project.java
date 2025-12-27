package com.starter.dtxcollector.domain.model;

import com.starter.dtxcollector.domain.model.base.DomainMarker;

import java.time.Instant;
import java.util.UUID;

public class Project implements DomainMarker {

    private final UUID id;

    private String name;
    private String description;
    private boolean enabled;

    private final UUID createdBy;
    private final Instant createdAt;
    private final String environment;
    private final UUID teamId;

    public Project(
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
