package com.influa.influa.controllers.socialnetwork;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.influa.influa.dtos.socialnetwork.InfluencerSocialNetworkDTO;
import com.influa.influa.model.socialnetwork.InfluencerSocialNetwork;
import com.influa.influa.services.socialnetwork.InfluencerSocialNetworkService;

@Controller
@RequestMapping("/socialnetwork/influencers")
public class InfluencerSocialNetworkController {

    @Autowired
    private InfluencerSocialNetworkService influencerSocialNetworkService;

    @PostMapping
    public ResponseEntity<InfluencerSocialNetwork> create(
            @RequestBody InfluencerSocialNetworkDTO influencerSocialNetworkDTO) {
        InfluencerSocialNetwork socialNetwork = influencerSocialNetworkService.create(influencerSocialNetworkDTO);

        return new ResponseEntity<>(socialNetwork, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InfluencerSocialNetwork>> findAll() {
        List<InfluencerSocialNetwork> socialNetworks = influencerSocialNetworkService.findAll();

        return new ResponseEntity<>(socialNetworks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfluencerSocialNetwork> findById(@PathVariable UUID id) {
        InfluencerSocialNetwork socialNetwork = influencerSocialNetworkService.findById(id);

        return new ResponseEntity<>(socialNetwork, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfluencerSocialNetwork> update(
            @PathVariable UUID id,
            @RequestBody InfluencerSocialNetworkDTO influencerSocialNetworkDTO) {
        InfluencerSocialNetwork socialNetworkEdit = influencerSocialNetworkService.update(id,
                influencerSocialNetworkDTO);

        return new ResponseEntity<>(socialNetworkEdit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        influencerSocialNetworkService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
