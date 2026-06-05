package com.influa.influa.controllers.user;

import com.influa.influa.dtos.user.InfluencerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.influa.influa.model.user.Influencer;
import com.influa.influa.services.user.InfluencerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/influencers")
public class InfluencerController {

    @Autowired
    private InfluencerService influencerService;

    @PostMapping
    public ResponseEntity<Influencer> registerInfluencer(@RequestBody InfluencerDTO data) {
        Influencer influencer = this.influencerService.createInfluencer(data);

        return new ResponseEntity<>(influencer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Influencer>> getAllInfluencers() {
        List<Influencer> influencers = influencerService.findAllInfluencers();
        return new ResponseEntity<>(influencers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Influencer> getInfluencerById(@PathVariable UUID id) {
        Influencer influencer = influencerService.findInfluencerById(id);
        return new ResponseEntity<>(influencer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Influencer> updateInfluencer(@PathVariable UUID id, @RequestBody InfluencerDTO influencerDTO) {
        Influencer updateInfluencer = influencerService.updateInfluencer(id, influencerDTO);
        return new ResponseEntity<>(updateInfluencer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfluencer(@PathVariable UUID id) {
        influencerService.deleteInfluencer(id);
        return ResponseEntity.noContent().build();
    }
}
