package com.influa.influa.controllers.partnerships;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.influa.influa.dtos.partnerships.CampaignProposalRequestDTO;
import com.influa.influa.dtos.partnerships.CampaignProposalResponseDTO;
import com.influa.influa.dtos.partnerships.UpdateProposalStatusDTO;
import com.influa.influa.services.partnerships.CampaignProposalService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/proposals")
public class CampaignProposalController {

    @Autowired
    private CampaignProposalService campaignProposalService;

    @PostMapping
    public ResponseEntity<CampaignProposalResponseDTO> createProposal(
            @RequestHeader("X-Company-Id") UUID companyId,
            @RequestBody @Valid CampaignProposalRequestDTO proposalRequestDTO) {
        
        CampaignProposalResponseDTO response = campaignProposalService.createProposal(companyId, proposalRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CampaignProposalResponseDTO> updateProposalStatus(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateProposalStatusDTO updateStatusDTO) {
        
        CampaignProposalResponseDTO response = campaignProposalService.updateStatus(id, updateStatusDTO.status());
        return ResponseEntity.ok(response);
    }
    
}
