package com.influa.influa.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.influa.influa.dtos.user.InfluencerDTO;
import com.influa.influa.model.user.Influencer;
import com.influa.influa.repositories.user.InfluencerRepository;

import java.util.List;
import java.util.UUID;

@Service
public class InfluencerService {

    @Autowired
    private InfluencerRepository influencerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Influencer createInfluencer(InfluencerDTO influencerDTO) {
        Influencer newInfluencer = new Influencer(influencerDTO);

        newInfluencer.setPassword(passwordEncoder.encode(influencerDTO.userDTO().password()));
        
        return influencerRepository.save(newInfluencer);
    }

    public Influencer findInfluencerById(UUID id) {
        return influencerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Influencer não encontrado com o ID: " + id));
    }

    public List<Influencer> findAllInfluencers() {
        return influencerRepository.findAll();
    }

    public Influencer updateInfluencer(UUID id, InfluencerDTO influencerDTO) {
        Influencer influencer = influencerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contratante não encontrado com o ID: " + id));

        influencer.setName(influencerDTO.userDTO().name());
        influencer.setEmail(influencerDTO.userDTO().email());
        influencer.setPassword(influencerDTO.userDTO().password());

        return influencerRepository.save(influencer);
    }

    public void deleteInfluencer(UUID id) {
        Influencer influencer = findInfluencerById(id);
        influencerRepository.delete(influencer);
    }
    
}
