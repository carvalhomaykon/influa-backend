package com.influa.influa.services.partnerships;

import com.influa.influa.model.partnerships.CampaignProposal;
import com.influa.influa.model.partnerships.EnumStatusProposal;
import com.influa.influa.model.partnerships.PartnershipAgreement;
import com.influa.influa.repositories.partnerships.PartnershipAgreementRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartnershipAgreementService {

    @Autowired
    private PartnershipAgreementRepository agreementRepository;

    @Transactional
    public void createAgreement(CampaignProposal proposal) {
        PartnershipAgreement agreement = new PartnershipAgreement();
        
        agreement.setCampaignProposal(proposal);
        agreement.setStatus(EnumStatusProposal.ACCEPTED);

        agreementRepository.save(agreement);
    }
    
    // Futuros métodos como fechar contrato, cancelar contrato, etc., entram aqui.
}