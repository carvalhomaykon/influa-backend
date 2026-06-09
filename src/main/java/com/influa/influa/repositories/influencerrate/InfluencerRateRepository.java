package com.influa.influa.repositories.influencerrate;

import com.influa.influa.model.influencerrate.InfluencerRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InfluencerRateRepository extends JpaRepository<InfluencerRate, UUID> {
    List<InfluencerRate> findByInfluencerId(UUID influencerId);
}
