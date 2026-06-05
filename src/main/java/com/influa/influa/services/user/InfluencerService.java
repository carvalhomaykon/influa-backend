package com.influa.influa.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.influa.influa.dtos.user.InfluencerDTO;
import com.influa.influa.dtos.user.UserDTO;
import com.influa.influa.model.user.Influencer;
import com.influa.influa.repositories.user.InfluencerRepository;

import jakarta.transaction.Transactional;

@Service
public class InfluencerService {

    @Autowired
    private InfluencerRepository influencerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Influencer createInfluencer(InfluencerDTO influencerDTO) {

        Influencer newInfluencer = new Influencer(influencerDTO);

        newInfluencer.setPassword(passwordEncoder.encode(influencerDTO.userDTO().password()));
        
        return influencerRepository.save(newInfluencer);
    }
    
}
