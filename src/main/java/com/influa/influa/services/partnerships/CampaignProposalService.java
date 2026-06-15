package com.influa.influa.services.partnerships;

import com.influa.influa.dtos.partnerships.CampaignProposalRequestDTO;
import com.influa.influa.dtos.partnerships.CampaignProposalResponseDTO;
import com.influa.influa.model.influencerrate.InfluencerRate;
import com.influa.influa.model.partnerships.CampaignProposal;
import com.influa.influa.model.partnerships.EnumStatusProposal;
import com.influa.influa.model.user.Company;
import com.influa.influa.repositories.influencerrate.InfluencerRateRepository;
import com.influa.influa.repositories.partnerships.CampaignProposalRepository;
import com.influa.influa.repositories.user.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CampaignProposalService {

    @Autowired
    private CampaignProposalRepository proposalRepository;

    @Autowired
    private InfluencerRateRepository rateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PartnershipAgreementService agreementService;

    @Transactional
    public CampaignProposalResponseDTO createProposal(UUID companyId, CampaignProposalRequestDTO dto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        InfluencerRate rate = rateRepository.findById(dto.influencerRateId())
                .orElseThrow(() -> new RuntimeException("Tabela de preço do influenciador não encontrada"));

        if (dto.bidValue().compareTo(rate.getStartingPrice()) < 0 || dto.bidValue().compareTo(rate.getEndPrice()) > 0) {
            throw new IllegalArgumentException("O valor do lance está fora da faixa estipulada pelo influenciador.");
        }

        CampaignProposal proposal = new CampaignProposal();
        proposal.setTitle(dto.title());
        proposal.setDescription(dto.description());
        proposal.setStatus(EnumStatusProposal.PENDING);
        proposal.setCompany(company);
        proposal.setInfluencerRate(rate);
        proposal.setBidValue(dto.bidValue());
        proposal.setRequestedAmount(dto.requestedAmount());
        
        CampaignProposal savedProposal = proposalRepository.save(proposal);

        return convertToResponseDTO(savedProposal);
    }

    @Transactional
    public CampaignProposalResponseDTO updateStatus(UUID proposalId, EnumStatusProposal newStatus) {
        CampaignProposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));

        // Evita reprocessar propostas já finalizadas
        if (proposal.getStatus() == EnumStatusProposal.ACCEPTED || proposal.getStatus() == EnumStatusProposal.REJECTED) {
            throw new IllegalStateException("Esta proposta já foi finalizada e não pode ser alterada.");
        }

        proposal.setStatus(newStatus);
        CampaignProposal updatedProposal = proposalRepository.save(proposal);

        // GATILHO: Se o influenciador aceitou, cria automaticamente o contrato de parceria
        if (newStatus == EnumStatusProposal.ACCEPTED) {
            agreementService.createAgreement(updatedProposal);
        }

        return convertToResponseDTO(updatedProposal);
    }

    private CampaignProposalResponseDTO convertToResponseDTO(CampaignProposal proposal) {
        return new CampaignProposalResponseDTO(
                proposal.getId(),
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getStatus(),
                proposal.getBidValue(),
                proposal.getRequestedAmount(),

                new CampaignProposalResponseDTO.CompanySummaryDTO(
                        proposal.getCompany().getId(),
                        proposal.getCompany().getCompanyName(),
                        proposal.getCompany().getCnpj()
                ),
                new CampaignProposalResponseDTO.InfluencerRateSummaryDTO(
                        proposal.getInfluencerRate().getId(),
                        proposal.getInfluencerRate().getPlatform().name(),
                        proposal.getInfluencerRate().getStartingPrice(),
                        proposal.getInfluencerRate().getEndPrice()
                )
        );
    }
}