package com.starter.dtxcollector.domain.model;

import com.starter.dtxcollector.domain.model.base.DomainMarker;
import java.util.UUID;

public class User implements DomainMarker {
    private UUID id;
    private String username;
    private String email;
    private String hashedPassword;

    public static User create(String username, String email, String hashedPassword) {
        User user = new User();
        user.username = username;
        user.email = email;
        user.hashedPassword = hashedPassword;
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
