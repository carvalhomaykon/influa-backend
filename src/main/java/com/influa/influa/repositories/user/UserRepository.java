package com.influa.influa.repositories.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influa.influa.model.user.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByEmail(String email);
}
