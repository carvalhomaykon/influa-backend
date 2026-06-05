package com.influa.influa.repositories.user;

import com.influa.influa.model.user.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractorRepository extends JpaRepository<Contractor, UUID> {
}
