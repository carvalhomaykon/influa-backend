package com.influa.influa.controllers.influencerrate;

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
            @RequestBody InfluencerRateDTO influencerRateDTO) {
        InfluencerRate influencerRate = influencerRateService.create(influencerRateDTO);

        return new ResponseEntity<>(influencerRate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InfluencerRate>> findAll() {
        List<InfluencerRate> influencerRates = influencerRateService.findAll();

        return new ResponseEntity<>(influencerRates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfluencerRate> findById(@PathVariable UUID id) {
        InfluencerRate influencerRate = influencerRateService.findById(id);

        return new ResponseEntity<>(influencerRate, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfluencerRate> update(
            @PathVariable UUID id,
            @RequestBody InfluencerRateDTO influencerRateDTO) {
        InfluencerRate influencerRateEdit = influencerRateService.update(id, influencerRateDTO);

        return new ResponseEntity<>(influencerRateEdit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        influencerRateService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
