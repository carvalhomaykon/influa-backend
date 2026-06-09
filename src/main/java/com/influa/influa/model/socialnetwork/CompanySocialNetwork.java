package com.influa.influa.model.socialnetwork;

import com.influa.influa.dtos.socialnetwork.CompanySocialNetworkDTO;
import com.influa.influa.model.user.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("COMPANY")
@Getter
@Setter
@NoArgsConstructor
public class CompanySocialNetwork extends SocialNetwork{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_uuid", nullable = false)
    private Company company;

    public CompanySocialNetwork (CompanySocialNetworkDTO companySocialNetworkDTO) {
        super (companySocialNetworkDTO.socialNetworkDTO());

        this.company = companySocialNetworkDTO.company();
    }

}
