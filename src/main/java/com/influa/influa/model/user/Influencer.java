package com.influa.influa.model.user;

import com.influa.influa.dtos.user.InfluencerDTO;
import com.influa.influa.dtos.user.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "influencers")
@Getter
@Setter

public class Influencer extends User{
    
    private String biography;
    private String mainNiche;
    private String profileStatus;
    private String mediaKitUrl;

    public Influencer() {
        super();
    }

    public Influencer(UserDTO userDTO, InfluencerDTO influencerDTO) {
        super(userDTO);

        this.biography = influencerDTO.biography();
        this.mainNiche = influencerDTO.mainNiche();
        this.profileStatus = influencerDTO.profileStatus();
        this.mediaKitUrl = influencerDTO.mediaKitUrl();
        this.profileStatus = EnumStatusUser.ACTIVE.name();
    }

}
