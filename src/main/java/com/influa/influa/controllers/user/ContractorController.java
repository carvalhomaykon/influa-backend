package com.influa.influa.controllers.user;

import com.influa.influa.dtos.user.ContractorDTO;
import com.influa.influa.model.user.Contractor;
import com.influa.influa.services.user.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users/contractors")
public class ContractorController {

    @Autowired
    private ContractorService contractorService;

    @PostMapping
    public ResponseEntity<Contractor> registerContractor(@RequestBody ContractorDTO contractorDTO) {
        Contractor contractor = contractorService.createContractor(contractorDTO);

        return new ResponseEntity<>(contractor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Contractor>> getAllContractors() {
        List<Contractor> contractors = contractorService.findAllContractors();
        return ResponseEntity.ok(contractors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contractor> getContractorById(@PathVariable UUID id) {
        Contractor contractor = contractorService.findContractorById(id);
        return ResponseEntity.ok(contractor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contractor> updateContractor(@PathVariable UUID id, @RequestBody ContractorDTO contractorDTO) {
        Contractor updatedContractor = contractorService.updateContractor(id, contractorDTO);
        return ResponseEntity.ok(updatedContractor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractor(@PathVariable UUID id) {
        contractorService.deleteContractor(id);
        return ResponseEntity.noContent().build();
    }


}
