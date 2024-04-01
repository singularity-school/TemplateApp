package com.kz.secretsanta.db;

import com.kz.secretsanta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDb extends JpaRepository<User, UUID> {
    Optional<User>findUserByEmail(String email);
}
