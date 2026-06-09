package com.influa.influa.services.influencerrate;

import com.influa.influa.dtos.influencerrate.InfluencerRateDTO;
import com.influa.influa.model.influencerrate.InfluencerRate;
import com.influa.influa.model.user.Influencer;
import com.influa.influa.repositories.influencerrate.InfluencerRateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InfluencerRateService {

    @Autowired
    private InfluencerRateRepository influencerRateRepository;

    @Transactional
    public InfluencerRate create(InfluencerRateDTO influencerRateDTO) {
        InfluencerRate influencerRate = new InfluencerRate(influencerRateDTO);

        return influencerRateRepository.save(influencerRate);
    }

    @Transactional()
    public List<InfluencerRate> findAll() {
        return influencerRateRepository.findAll();
    }

    @Transactional()
    public InfluencerRate findById(UUID uuid) {
        return influencerRateRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Influencer rate não encontrada para o ID: " + uuid));
    }

    public List<InfluencerRate> findByInfluencer(Influencer influencer) {
        return influencerRateRepository.findByInfluencerId(influencer.getId());
    }

    @Transactional
    public InfluencerRate update(UUID uuid, InfluencerRateDTO influencerRateDTO) {
        InfluencerRate existingInfluencerRate = findById(uuid);

        existingInfluencerRate.setPlatform(influencerRateDTO.platform());
        existingInfluencerRate.setAmount(influencerRateDTO.amount());
        existingInfluencerRate.setStartingPrice(influencerRateDTO.startingPrice());
        existingInfluencerRate.setEndPrice(influencerRateDTO.endPrice());
        existingInfluencerRate.setDescription(influencerRateDTO.description());

        return influencerRateRepository.save(existingInfluencerRate);
    }


    @Transactional
    public void delete(UUID uuid) {
        InfluencerRate influencerRate = findById(uuid);
        influencerRateRepository.delete(influencerRate);
    }

}
