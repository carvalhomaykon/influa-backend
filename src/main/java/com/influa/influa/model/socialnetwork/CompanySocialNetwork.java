package com.influa.influa.model.socialnetwork;

import com.influa.influa.dtos.socialnetwork.CompanySocialNetworkDTO;
import com.influa.influa.model.user.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CompanySocialNetwork extends SocialNetwork{

    @ManyToOne()
    @JoinColumn(name = "company_uuid", nullable = true)
    private Company company;

    public CompanySocialNetwork (CompanySocialNetworkDTO companySocialNetworkDTO) {
        super (companySocialNetworkDTO.socialNetworkDTO());
    }

}
