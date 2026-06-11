package com.influa.influa.model.user;

import java.util.UUID;

import com.influa.influa.dtos.user.CompanyDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String companyName;
    private String cnpj;
    private String cep;
    private String description;
    private String niche;
    private String targetAudience;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private Contractor contractor;

    public Company(CompanyDTO companyDTO) {
        this.companyName = companyDTO.companyName();
        this.cnpj = companyDTO.cnpj();
        this.cep = companyDTO.cep();
        this.description = companyDTO.description();
        this.niche = companyDTO.niche();
        this.targetAudience = companyDTO.targetAudience();
    }

}
