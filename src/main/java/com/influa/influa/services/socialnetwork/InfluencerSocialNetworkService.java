package com.influa.influa.services.socialnetwork;

import com.influa.influa.dtos.socialnetwork.InfluencerSocialNetworkDTO;
import com.influa.influa.model.socialnetwork.InfluencerSocialNetwork;
import com.influa.influa.model.user.Influencer;
import com.influa.influa.repositories.socialnetwork.InfluencerSocialNetworkRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfluencerSocialNetworkService {

    @Autowired
    private InfluencerSocialNetworkRepository influencerSocialNetworkRepository;

    @Transactional
    public InfluencerSocialNetwork create(InfluencerSocialNetworkDTO influencerSocialNetworkDTO) {
        InfluencerSocialNetwork socialNetwork = new InfluencerSocialNetwork(influencerSocialNetworkDTO);

        return influencerSocialNetworkRepository.save(socialNetwork);
    }

    @Transactional()
    public List<InfluencerSocialNetwork> findAll() {
        return influencerSocialNetworkRepository.findAll();
    }


    @Transactional()
    public InfluencerSocialNetwork findById(UUID uuid) {
        return influencerSocialNetworkRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Rede social do influenciador não encontrada para o ID: " + uuid));
    }


    public List<InfluencerSocialNetwork> findByInfluencer(Influencer influencer) {
        return influencerSocialNetworkRepository.findByInfluencerId(influencer.getId());
    }


    @Transactional
    public InfluencerSocialNetwork update(UUID uuid, InfluencerSocialNetworkDTO influencerSocialNetworkDTO) {
        InfluencerSocialNetwork existingNetwork = findById(uuid);

        existingNetwork.setPlatform(influencerSocialNetworkDTO.socialNetworkDTO().enumPlatform());
        existingNetwork.setUsername(influencerSocialNetworkDTO.socialNetworkDTO().username());
        existingNetwork.setQtdFollowers(influencerSocialNetworkDTO.socialNetworkDTO().qtdFollowers());
        existingNetwork.setUrl(influencerSocialNetworkDTO.socialNetworkDTO().url());

        return influencerSocialNetworkRepository.save(existingNetwork);
    }


    @Transactional
    public void delete(UUID uuid) {
        InfluencerSocialNetwork socialNetwork = findById(uuid);
        influencerSocialNetworkRepository.delete(socialNetwork);
    }

}
