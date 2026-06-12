package com.influa.influa.repositories.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influa.influa.model.user.Influencer;


public interface InfluencerRepository extends JpaRepository<Influencer, UUID> {
    Optional<Influencer> findByEmail(String email);
}
