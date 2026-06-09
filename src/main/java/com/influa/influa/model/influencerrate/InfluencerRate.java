package com.influa.influa.model.influencerrate;

import java.math.BigDecimal;
import java.util.UUID;

import com.influa.influa.dtos.influencerrate.InfluencerRateDTO;
import com.influa.influa.model.socialnetwork.EnumPlatform;
import com.influa.influa.model.user.Influencer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "influencer_rate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfluencerRate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private EnumPlatform platform;
    private Integer amount;
    private BigDecimal startingPrice;
    private BigDecimal endPrice;
    private String description;

    @ManyToOne
    @JoinColumn(name = "influencer_id")
    private Influencer influencer;

    public InfluencerRate(InfluencerRateDTO influencerRateDTO) {
        this.platform = influencerRateDTO.platform();
        this.amount = influencerRateDTO.amount();
        this.startingPrice = influencerRateDTO.startingPrice();
        this.endPrice = influencerRateDTO.endPrice();
        this.description = influencerRateDTO.description();
    }

}
