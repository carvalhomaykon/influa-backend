package com.influa.influa.model.socialnetwork;

import com.influa.influa.dtos.socialnetwork.InfluencerSocialNetworkDTO;
import com.influa.influa.model.user.Influencer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InfluencerSocialNetwork extends SocialNetwork{

    @ManyToOne()
    @JoinColumn(name = "influencer_uuid", nullable = true)
    private Influencer influencer;

    public InfluencerSocialNetwork(InfluencerSocialNetworkDTO influencerSocialNetworkDTO) {
        super (influencerSocialNetworkDTO.socialNetworkDTO());
    }

}
