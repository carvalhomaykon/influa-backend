package com.influa.influa.dtos.partnerships;

import com.influa.influa.model.partnerships.EnumStatusProposal;
import java.math.BigDecimal;
import java.util.UUID;

public record CampaignProposalResponseDTO(
    UUID id,
    String title,
    String description,
    EnumStatusProposal status,
    BigDecimal bidValue,
    Integer requestedAmount,
    CompanySummaryDTO company,
    InfluencerRateSummaryDTO influencerRate
) {
    public record CompanySummaryDTO(
        UUID id, String name, String logoUrl
    ) {}
    
    public record InfluencerRateSummaryDTO(
        UUID id, String platform, BigDecimal startingPrice, BigDecimal endPrice
    ) {}
}