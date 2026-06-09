package com.influa.influa.repositories.socialnetwork;

import com.influa.influa.model.socialnetwork.CompanySocialNetwork;
import com.influa.influa.model.user.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanySocialNetworkRepository extends JpaRepository<CompanySocialNetwork, UUID> {
    List<CompanySocialNetwork> findByCompanyId(UUID companyUuid);
}
