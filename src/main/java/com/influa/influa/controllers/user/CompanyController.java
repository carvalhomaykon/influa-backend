package com.influa.influa.controllers.user;

import java.security.Principal;
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

import com.influa.influa.dtos.user.CompanyDTO;
import com.influa.influa.model.user.Company;
import com.influa.influa.services.user.CompanyService;

@Controller
@RequestMapping("/users/contractors/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> registerCompany(
            @RequestBody CompanyDTO companyDTO,
            Principal principal) {

        Company company = companyService.createCompany(companyDTO, principal.getName());

        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Company>> findAllCompany(
        Principal userLogged
    ) {
        List<Company> companies = companyService.findAllByContractorId(userLogged.getName());
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable UUID id) {
        Company company = companyService.findCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable UUID id, @RequestBody CompanyDTO companyDTO) {
        Company updatedCompany = companyService.updatCompany(id, companyDTO);

        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

}
