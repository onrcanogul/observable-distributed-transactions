package com.starter.dtxcollector.domain.repository;

import com.starter.dtxcollector.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    void save(User user);
}
