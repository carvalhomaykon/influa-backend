package com.influa.influa.services.socialnetwork;

import com.influa.influa.dtos.socialnetwork.CompanySocialNetworkDTO;
import com.influa.influa.model.socialnetwork.CompanySocialNetwork;
import com.influa.influa.model.user.Company;
import com.influa.influa.repositories.socialnetwork.CompanySocialNetworkRepository;
import com.influa.influa.repositories.user.CompanyRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanySocialNetworkService {

    @Autowired
    private CompanySocialNetworkRepository companySocialNetworkRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public CompanySocialNetwork createCompanySocialNetwork(CompanySocialNetworkDTO companySocialNetworkDTO, UUID idCompany) {

        Company company = companyRepository.findById(idCompany)
            .orElseThrow(() -> new RuntimeException("Company não encontrado com o ID: " + idCompany)
        );

        CompanySocialNetwork companySocialNetwork = new CompanySocialNetwork(companySocialNetworkDTO);
        companySocialNetwork.setCompany(company);

        return companySocialNetworkRepository.save(companySocialNetwork);
        
    }

    @Transactional()
    public CompanySocialNetwork findCompanySocialNetworkById(UUID id) {
        return companySocialNetworkRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("CompanySocialNetwork não encontrado com o ID: " + id)
        );
    }

    @Transactional
    public List<CompanySocialNetwork> findCompanySocialNetworkByCompanyId(UUID companyId) {
        return companySocialNetworkRepository.findAllByCompanyId(companyId);
    }

    @Transactional
    public CompanySocialNetwork updateCompanySocialNetwork(UUID id, CompanySocialNetworkDTO companySocialNetworkDTO) {
        CompanySocialNetwork existingNetwork = findCompanySocialNetworkById(id);

        existingNetwork.setPlatform(companySocialNetworkDTO.socialNetworkDTO().enumPlatform());
        existingNetwork.setUsername(companySocialNetworkDTO.socialNetworkDTO().username());
        existingNetwork.setQtdFollowers(companySocialNetworkDTO.socialNetworkDTO().qtdFollowers());
        existingNetwork.setUrl(companySocialNetworkDTO.socialNetworkDTO().url());

        return companySocialNetworkRepository.save(existingNetwork);
    }


    @Transactional
    public void deleteCompanySocialNetwork(UUID id) {
        CompanySocialNetwork socialNetwork = findCompanySocialNetworkById(id);
        companySocialNetworkRepository.delete(socialNetwork);
    }

}
