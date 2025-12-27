package com.starter.dtxcollector.domain.model;

import com.starter.dtxcollector.domain.model.base.DomainMarker;

import java.util.ArrayList;
import java.util.List;

public class Team implements DomainMarker {
    private String name;
    private String description;
    private List<User> users = new ArrayList<>();

    public Team(String name, String description, List<User> users) {
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
