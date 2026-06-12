package com.influa.influa.controllers.influencerrate;

import java.security.Principal;
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

import com.influa.influa.dtos.influencerrate.InfluencerRateDTO;
import com.influa.influa.model.influencerrate.InfluencerRate;
import com.influa.influa.services.influencerrate.InfluencerRateService;

@Controller
@RequestMapping("/influencer-rates")
public class InfluencerRateController {

    @Autowired
    private InfluencerRateService influencerRateService;

    @PostMapping
    public ResponseEntity<InfluencerRate> create(
        @RequestBody InfluencerRateDTO influencerRateDTO,
        Principal principal
    ) {
        InfluencerRate influencerRate = influencerRateService.createInfluencerRate(influencerRateDTO, principal.getName());

        return new ResponseEntity<>(influencerRate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InfluencerRate>> findAllByInfluencer(Principal principal) {
        List<InfluencerRate> influencerRates = influencerRateService.findByInfluencer(principal.getName());

        return new ResponseEntity<>(influencerRates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfluencerRate> findInfluencerRateById(@PathVariable UUID id) {
        InfluencerRate influencerRate = influencerRateService.findInfluencerRateById(id);

        return new ResponseEntity<>(influencerRate, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfluencerRate> updateInfluencerRate(
            @PathVariable UUID id,
            @RequestBody InfluencerRateDTO influencerRateDTO) {
        InfluencerRate influencerRateEdit = influencerRateService.updateInfluencerRate(id, influencerRateDTO);

        return new ResponseEntity<>(influencerRateEdit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfluencerRate(@PathVariable UUID id) {
        influencerRateService.deleteInfluencerRate(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
