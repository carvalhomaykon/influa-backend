package com.influa.influa.dtos.user;

public record CompanyDTO (
        String companyName,
        String cnpj,
        String cep,
        String description,
        String niche,
        String targetAudience
){
}
