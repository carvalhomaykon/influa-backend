package com.influa.influa.services.influencerrate;

import com.influa.influa.dtos.influencerrate.InfluencerRateDTO;
import com.influa.influa.model.influencerrate.InfluencerRate;
import com.influa.influa.model.user.Influencer;
import com.influa.influa.repositories.influencerrate.InfluencerRateRepository;
import com.influa.influa.repositories.user.InfluencerRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InfluencerRateService {

    @Autowired
    private InfluencerRateRepository influencerRateRepository;

    @Autowired
    private InfluencerRepository influencerRepository;

    @Transactional
    public InfluencerRate createInfluencerRate(
        InfluencerRateDTO influencerRateDTO,
        String influencerUsername
    ) {

        Influencer influencer = influencerRepository.findByEmail(influencerUsername)
                .orElseThrow(() -> new RuntimeException("Influencer não encontrado para o email: " + influencerUsername));
        
        InfluencerRate influencerRate = new InfluencerRate(influencerRateDTO);
        influencerRate.setInfluencer(influencer);

        return influencerRateRepository.save(influencerRate);
    }

    @Transactional()
    public List<InfluencerRate> findByInfluencer(String influencerUsername) {
        Influencer influencer = influencerRepository.findByEmail(influencerUsername)
                .orElseThrow(() -> new RuntimeException("Influencer não encontrado para o email: " + influencerUsername));

        return influencerRateRepository.findByInfluencerId(influencer.getId());
    }

    @Transactional()
    public InfluencerRate findInfluencerRateById(UUID uuid) {
        return influencerRateRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Influencer rate não encontrada para o ID: " + uuid));
    }

    @Transactional
    public List<InfluencerRate> findByInfluencer(Influencer influencer) {
        return influencerRateRepository.findByInfluencerId(influencer.getId());
    }

    @Transactional
    public InfluencerRate updateInfluencerRate(UUID uuid, InfluencerRateDTO influencerRateDTO) {
        InfluencerRate existingInfluencerRate = findInfluencerRateById(uuid);

        existingInfluencerRate.setPlatform(influencerRateDTO.platform());
        existingInfluencerRate.setAmount(influencerRateDTO.amount());
        existingInfluencerRate.setStartingPrice(influencerRateDTO.startingPrice());
        existingInfluencerRate.setEndPrice(influencerRateDTO.endPrice());
        existingInfluencerRate.setDescription(influencerRateDTO.description());

        return influencerRateRepository.save(existingInfluencerRate);
    }


    @Transactional
    public void deleteInfluencerRate(UUID uuid) {
        InfluencerRate influencerRate = findInfluencerRateById(uuid);
        influencerRateRepository.delete(influencerRate);
    }

}
