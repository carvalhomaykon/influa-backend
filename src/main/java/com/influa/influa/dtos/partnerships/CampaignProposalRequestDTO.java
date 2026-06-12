package com.influa.influa.dtos.partnerships;

import java.math.BigDecimal;
import java.util.UUID;

public record CampaignProposalRequestDTO(
    String title,
    String description,
    UUID influencerRateId,
    Integer requestedAmount,
    BigDecimal bidValue
) {}