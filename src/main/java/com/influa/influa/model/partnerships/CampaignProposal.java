package com.influa.influa.model.partnerships;

import java.math.BigDecimal;
import java.util.UUID;

import com.influa.influa.model.influencerrate.InfluencerRate;
import com.influa.influa.model.user.Company;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "campaign_proposals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignProposal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;

    private EnumStatusProposal status;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "influencer_rate_id", nullable = false)
    private InfluencerRate influencerRate;

    private BigDecimal bidValue; 
    
    private Integer requestedAmount; 
}