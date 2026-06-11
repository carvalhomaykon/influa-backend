package com.influa.influa.repositories.user;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influa.influa.model.user.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    List<Company> findAllByContractorId(UUID id);
}
