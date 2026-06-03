package com.influa.influa.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.influa.influa.dtos.user.RegisterInfluencerDTO;
import com.influa.influa.model.user.Influencer;
import com.influa.influa.services.user.InfluencerService;

public class InfluencerController {
    
    @Autowired
    private InfluencerService influencerService;

    @PostMapping
    public ResponseEntity<Influencer> register(@RequestBody RegisterInfluencerDTO data) {
        Influencer influencer = influencerService.createInfluencer(data.user(), data.influencer());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(influencer);
    }

}
