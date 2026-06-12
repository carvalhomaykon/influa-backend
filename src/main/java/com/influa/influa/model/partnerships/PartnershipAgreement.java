package com.influa.influa.model.partnerships;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "campaign_proposal_id", nullable = false)
    private CampaignProposal campaignProposal;
    
    @Enumerated(EnumType.STRING)
    private EnumStatusProposal status;
}
