package com.influa.influa.controllers.user;

import com.influa.influa.dtos.user.InfluencerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.influa.influa.model.user.Influencer;
import com.influa.influa.services.user.InfluencerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class InfluencerController {
    
    @Autowired
    private InfluencerService influencerService;

    @PostMapping("/influencer")
    public ResponseEntity<Influencer> registerInfluencer(@RequestBody InfluencerDTO data) {
        Influencer influencer = this.influencerService.createInfluencer(data);

        return new ResponseEntity<>(influencer, HttpStatus.CREATED);
    }

}
