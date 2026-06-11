package com.influa.influa.repositories.socialnetwork;

import com.influa.influa.model.socialnetwork.InfluencerSocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InfluencerSocialNetworkRepository extends JpaRepository<InfluencerSocialNetwork, UUID> {
    List<InfluencerSocialNetwork> findByInfluencerId(UUID influencerId);
}
