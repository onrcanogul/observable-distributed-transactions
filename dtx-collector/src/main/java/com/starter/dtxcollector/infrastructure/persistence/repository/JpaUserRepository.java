package com.starter.dtxcollector.infrastructure.persistence.repository;

import com.starter.dtxcollector.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@RequestMapping
public interface JpaUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
