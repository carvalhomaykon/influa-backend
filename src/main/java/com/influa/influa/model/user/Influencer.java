package com.influa.influa.model.user;

import com.influa.influa.dtos.user.InfluencerDTO;
import com.influa.influa.dtos.user.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "influencers")
@Getter
@Setter
@NoArgsConstructor
public class Influencer extends User{
    
    private String biography;
    private String mainNiche;
    private String mediaKitUrl;

    public Influencer(InfluencerDTO influencerDTO) {
        super(influencerDTO.userDTO());

        this.biography = influencerDTO.biography();
        this.mainNiche = influencerDTO.mainNiche();
        this.mediaKitUrl = influencerDTO.mediaKitUrl();
    }

}
