package com.influa.influa.model.socialnetwork;

import com.influa.influa.dtos.socialnetwork.InfluencerSocialNetworkDTO;
import com.influa.influa.model.user.Influencer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("INFLUENCER")
@Getter
@Setter
@NoArgsConstructor
public class InfluencerSocialNetwork extends SocialNetwork{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "influencer_uuid", nullable = false)
    private Influencer influencer;

    public InfluencerSocialNetwork(InfluencerSocialNetworkDTO influencerSocialNetworkDTO) {
        super (influencerSocialNetworkDTO.socialNetworkDTO());

        this.influencer = influencerSocialNetworkDTO.influencer();
    }

}
