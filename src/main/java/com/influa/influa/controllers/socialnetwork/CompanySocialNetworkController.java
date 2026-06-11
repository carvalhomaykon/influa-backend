package com.influa.influa.controllers.socialnetwork;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.influa.influa.dtos.socialnetwork.CompanySocialNetworkDTO;
import com.influa.influa.model.socialnetwork.CompanySocialNetwork;
import com.influa.influa.services.socialnetwork.CompanySocialNetworkService;

@Controller
@RequestMapping("/socialnetwork/companies")
public class CompanySocialNetworkController {

    @Autowired
    private CompanySocialNetworkService companySocialNetworkService;

    @PostMapping("/{idCompany}")
    public ResponseEntity<CompanySocialNetwork> registerCompanySocialNetwork(
        @RequestBody CompanySocialNetworkDTO companySocialNetworkDTO, 
        @PathVariable UUID idCompany
    ) {

        CompanySocialNetwork companySocialNetwork = companySocialNetworkService.createCompanySocialNetwork(companySocialNetworkDTO, idCompany);

        return new ResponseEntity<>(companySocialNetwork, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanySocialNetwork> findCompanySocialNetworkById(@PathVariable UUID id){
        CompanySocialNetwork companySocialNetwork = companySocialNetworkService.findCompanySocialNetworkById(id);

        return new ResponseEntity<>(companySocialNetwork, HttpStatus.OK);
    }

    @GetMapping("/{idCompany}")
    public ResponseEntity<List<CompanySocialNetwork>> findCompanySocialNetworkByCompanyId(
        @PathVariable UUID idCompany
    ){
        List<CompanySocialNetwork> companySocialNetworks = companySocialNetworkService.findCompanySocialNetworkByCompanyId(idCompany);

        return new ResponseEntity<>(companySocialNetworks, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CompanySocialNetwork> editCompanySocialNetwork(
        @PathVariable UUID id, 
        @RequestBody CompanySocialNetworkDTO companySocialNetworkDTO
    ){
        CompanySocialNetwork companySocialNetworkEdit = companySocialNetworkService.updateCompanySocialNetwork(id, companySocialNetworkDTO);
        
        return new ResponseEntity<>(companySocialNetworkEdit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void removeCompanySocialNetwork(@PathVariable UUID id){
        this.companySocialNetworkService.deleteCompanySocialNetwork(id);
    }
    
}
