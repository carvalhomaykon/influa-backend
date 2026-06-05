package com.influa.influa.model.user;

import com.influa.influa.dtos.user.ContractorDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contractors")
@Getter
@Setter
@NoArgsConstructor
public class Contractor extends User{

    public Contractor(ContractorDTO contractorDTO) {
        super(contractorDTO.userDTO());
    }

}
