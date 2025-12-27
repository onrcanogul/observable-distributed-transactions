package com.starter.dtxcollector.infrastructure.persistence.entity;

import com.starter.dtxcollector.infrastructure.persistence.entity.base.EntityMarker;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "team")
@Entity
public class TeamEntity implements EntityMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;

    @ManyToMany(targetEntity = UserEntity.class)
    private List<UserEntity> users = new ArrayList<>();
}
