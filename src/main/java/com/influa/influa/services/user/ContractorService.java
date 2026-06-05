package com.influa.influa.services.user;

import com.influa.influa.dtos.user.ContractorDTO;
import com.influa.influa.model.user.Contractor;
import com.influa.influa.repositories.user.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    public Contractor createContractor(ContractorDTO contractorDTO) {
        Contractor contractor = new Contractor(contractorDTO);

        return contractorRepository.save(contractor);
    }

    public Contractor findContractorById(UUID id) {
        return contractorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contratante não encontrado com o ID: " + id));
    }

    public List<Contractor> findAllContractors() {
        return contractorRepository.findAll();
    }

    public Contractor updateContractor(UUID id, ContractorDTO contractorDTO) {
        Contractor contractor = contractorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contratante não encontrado com o ID: " + id));

        contractor.setName(contractorDTO.userDTO().name());
        contractor.setEmail(contractorDTO.userDTO().email());
        contractor.setPassword(contractorDTO.userDTO().password());

        return contractorRepository.save(contractor);
    }

    public void deleteContractor(UUID id) {
        Contractor contractor = findContractorById(id);
        contractorRepository.delete(contractor);
    }

}
