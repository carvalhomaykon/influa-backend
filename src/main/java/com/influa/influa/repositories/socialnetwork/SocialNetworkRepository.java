package com.influa.influa.repositories.socialnetwork;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influa.influa.model.socialnetwork.SocialNetwork;

import java.util.UUID;

public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, UUID> {
}
