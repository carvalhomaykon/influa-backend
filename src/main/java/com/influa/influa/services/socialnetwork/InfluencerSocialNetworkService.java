package com.influa.influa.services.socialnetwork;

import com.influa.influa.dtos.socialnetwork.InfluencerSocialNetworkDTO;
import com.influa.influa.model.socialnetwork.InfluencerSocialNetwork;
import com.influa.influa.model.user.Influencer;
import com.influa.influa.repositories.socialnetwork.InfluencerSocialNetworkRepository;
import com.influa.influa.repositories.user.InfluencerRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfluencerSocialNetworkService {

    @Autowired
    private InfluencerSocialNetworkRepository influencerSocialNetworkRepository;

    @Autowired
    private InfluencerRepository influencerRepository;

    @Transactional
    public InfluencerSocialNetwork create(InfluencerSocialNetworkDTO influencerSocialNetworkDTO, String influencerUsername) {

        Influencer influencer = influencerRepository.findByEmail(influencerUsername)
            .orElseThrow(() -> new RuntimeException("Influenciador não encontrado com o username: " + influencerUsername)
        );

        InfluencerSocialNetwork socialNetwork = new InfluencerSocialNetwork(influencerSocialNetworkDTO);
        socialNetwork.setInfluencer(influencer);

        return influencerSocialNetworkRepository.save(socialNetwork);
    }

    @Transactional()
    public List<InfluencerSocialNetwork> findAllByInfluencer(String influencerUsername) {

        Influencer influencer = influencerRepository.findByEmail(influencerUsername)
            .orElseThrow(() -> new RuntimeException("Influenciador não encontrado com o username: " + influencerUsername)
        );

        return influencerSocialNetworkRepository.findByInfluencerId(influencer.getId());
    }


    @Transactional()
    public InfluencerSocialNetwork findInfluencerSocialNetworkById(UUID uuid) {
        return influencerSocialNetworkRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Rede social do influenciador não encontrada para o ID: " + uuid));
    }

    @Transactional
    public InfluencerSocialNetwork updateInfluencerSocialNetwork(UUID uuid, InfluencerSocialNetworkDTO influencerSocialNetworkDTO) {
        InfluencerSocialNetwork existingNetwork = findInfluencerSocialNetworkById(uuid);

        existingNetwork.setPlatform(influencerSocialNetworkDTO.socialNetworkDTO().enumPlatform());
        existingNetwork.setUsername(influencerSocialNetworkDTO.socialNetworkDTO().username());
        existingNetwork.setQtdFollowers(influencerSocialNetworkDTO.socialNetworkDTO().qtdFollowers());
        existingNetwork.setUrl(influencerSocialNetworkDTO.socialNetworkDTO().url());

        return influencerSocialNetworkRepository.save(existingNetwork);
    }


    @Transactional
    public void deleteInfluencerSocialNetwork(UUID uuid) {
        InfluencerSocialNetwork socialNetwork = findInfluencerSocialNetworkById(uuid);
        influencerSocialNetworkRepository.delete(socialNetwork);
    }

}
