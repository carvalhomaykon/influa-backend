package com.influa.influa.repositories.socialnetwork;

import com.influa.influa.model.socialnetwork.InfluencerSocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InfluencerSocialNetworkRepository extends JpaRepository<InfluencerSocialNetwork, UUID> {
}
