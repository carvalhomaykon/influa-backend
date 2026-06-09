package com.influa.influa.services.socialnetwork;

import com.influa.influa.dtos.socialnetwork.CompanySocialNetworkDTO;
import com.influa.influa.model.socialnetwork.CompanySocialNetwork;
import com.influa.influa.model.user.Company;
import com.influa.influa.repositories.socialnetwork.CompanySocialNetworkRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanySocialNetworkService {

    @Autowired
    private CompanySocialNetworkRepository companySocialNetworkRepository;


    @Transactional
    public CompanySocialNetwork create(CompanySocialNetworkDTO companySocialNetworkDTO) {
        CompanySocialNetwork socialNetwork = new CompanySocialNetwork(companySocialNetworkDTO);

        return companySocialNetworkRepository.save(socialNetwork);
    }

    @Transactional()
    public List<CompanySocialNetwork> findAll() {
        return companySocialNetworkRepository.findAll();
    }


    @Transactional()
    public CompanySocialNetwork findById(UUID uuid) {
        return companySocialNetworkRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Rede social da empresa não encontrada para o ID: " + uuid));
    }


    public List<CompanySocialNetwork> findByCompany(Company company) {
        return companySocialNetworkRepository.findByCompanyId(company.getId());
    }


    @Transactional
    public CompanySocialNetwork update(UUID uuid, CompanySocialNetworkDTO companySocialNetworkDTO) {
        CompanySocialNetwork existingNetwork = findById(uuid);

        existingNetwork.setPlatform(companySocialNetworkDTO.socialNetworkDTO().enumPlatform());
        existingNetwork.setUsername(companySocialNetworkDTO.socialNetworkDTO().username());
        existingNetwork.setQtdFollowers(companySocialNetworkDTO.socialNetworkDTO().qtdFollowers());
        existingNetwork.setUrl(companySocialNetworkDTO.socialNetworkDTO().url());

        return companySocialNetworkRepository.save(existingNetwork);
    }


    @Transactional
    public void delete(UUID uuid) {
        CompanySocialNetwork socialNetwork = findById(uuid);
        companySocialNetworkRepository.delete(socialNetwork);
    }

}
