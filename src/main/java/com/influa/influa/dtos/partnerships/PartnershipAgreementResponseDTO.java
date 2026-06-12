package com.influa.influa.dtos.partnerships;

import com.influa.influa.model.partnerships.EnumStatusProposal;

import java.util.UUID;

public record PartnershipAgreementResponseDTO(
    UUID id,
    EnumStatusProposal status,
    CampaignProposalSummaryDTO campaignProposal
) {
    public record CampaignProposalSummaryDTO(
        UUID id, 
        String title, 
        java.math.BigDecimal finalValue, 
        String influencerName, 
        String companyName
    ) {}
}