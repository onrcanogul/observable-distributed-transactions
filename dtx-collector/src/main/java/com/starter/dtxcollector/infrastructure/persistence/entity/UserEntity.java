package com.starter.dtxcollector.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    private UUID id;
    private String username;
    private String email;
    private String hashedPassword;

    public static UserEntity create(String username, String email, String hashedPassword) {
        UserEntity user = new UserEntity();
        user.username = username;
        user.email = email;
        user.hashedPassword = hashedPassword;
        return user;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
