package com.influa.influa.repositories.partnerships;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influa.influa.model.partnerships.CampaignProposal;

public interface CampaignProposalRepository extends JpaRepository<CampaignProposal, UUID> {
}
