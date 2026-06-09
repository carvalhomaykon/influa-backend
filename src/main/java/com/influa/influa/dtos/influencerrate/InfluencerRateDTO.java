package com.influa.influa.dtos.influencerrate;

import com.influa.influa.model.socialnetwork.EnumPlatform;

import java.math.BigDecimal;

public record InfluencerRateDTO(
        EnumPlatform platform,
        Integer amount,
        BigDecimal startingPrice,
        BigDecimal endPrice,
        String description
) {
}
