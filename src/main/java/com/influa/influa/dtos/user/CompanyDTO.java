package com.influa.influa.dtos.user;

public record CompanyDTO (
        String company,
        String cnpj,
        String cep,
        String description,
        String niche,
        String targetAudience
){
}
