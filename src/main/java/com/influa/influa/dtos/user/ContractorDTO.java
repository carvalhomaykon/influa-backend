package com.influa.influa.dtos.user;

public record ContractorDTO (
    String companyName,
    String cnpj,
    String cep,
    String description,
    String niche,
    String targetAudience
) {
}
