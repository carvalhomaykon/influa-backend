package com.influa.influa.model.partnerships;

import com.influa.influa.model.influencerrate.InfluencerRate;

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
@Table(name = "partnership_agreements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnershipAgreement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_proposal_id", nullable = false)
    private CampaignProposal campaignProposal;
    
    @ManyToOne
    @JoinColumn(name = "influencer_rate_id", nullable = false)
    private InfluencerRate influencerRate;
}
