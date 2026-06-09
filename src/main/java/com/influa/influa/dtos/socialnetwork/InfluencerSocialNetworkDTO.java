package com.influa.influa.dtos.socialnetwork;

import com.influa.influa.model.user.Influencer;

public record InfluencerSocialNetworkDTO(
        SocialNetworkDTO socialNetworkDTO,
        Influencer influencer
) {
}
