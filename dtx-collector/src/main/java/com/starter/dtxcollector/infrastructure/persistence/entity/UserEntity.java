package com.starter.dtxcollector.infrastructure.persistence.entity;

import com.starter.dtxcollector.infrastructure.persistence.entity.base.EntityMarker;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "users", indexes = {
        @Index(name = "idx_trace_id", columnList = "username"),
})
@Entity
public class UserEntity implements EntityMarker {
    @Id
    private UUID id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "hashed_password")
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
