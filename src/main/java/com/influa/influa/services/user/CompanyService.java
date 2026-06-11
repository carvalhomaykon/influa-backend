package com.influa.influa.services.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.influa.influa.dtos.user.CompanyDTO;
import com.influa.influa.model.user.Company;
import com.influa.influa.model.user.Contractor;
import com.influa.influa.repositories.user.CompanyRepository;
import com.influa.influa.repositories.user.ContractorRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Transactional
    public Company createCompany(CompanyDTO companyDTO, String emailLogado) {

        Contractor contractor = contractorRepository.findByEmail(emailLogado).orElseThrow(
            () -> new RuntimeException("Contract não encontrado com o email: " + emailLogado)
        );

        Company company = new Company(companyDTO);
        company.setContractor(contractor);

        return companyRepository.save(company);
    }

    @Transactional
    public Company findCompanyById(UUID id) {
        return this.companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company não encontrado com o ID: " + id));
    }

    @Transactional
    public List<Company> findAllByContractorId(String emailLogado) {

        Contractor contractor = contractorRepository.findByEmail(emailLogado).orElseThrow(
            () -> new RuntimeException("Contract não encontrado com o email: " + emailLogado)
        );

        return companyRepository.findAllByContractorId(contractor.getId());
    }

    @Transactional
    public Company updatCompany(UUID id, CompanyDTO companyDTO) {
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Company não encontrado com o ID: " + id));

        company.setCompanyName(companyDTO.companyName());
        company.setCnpj(companyDTO.cnpj());
        company.setCep(companyDTO.cep());
        company.setDescription(companyDTO.description());
        company.setNiche(companyDTO.niche());
        company.setTargetAudience(companyDTO.targetAudience());

        return companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(UUID id) {
        Company company = findCompanyById(id);
        companyRepository.delete(company);
    }
    
}
