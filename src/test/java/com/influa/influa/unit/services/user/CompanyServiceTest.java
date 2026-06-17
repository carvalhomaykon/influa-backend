package com.influa.influa.unit.services.user;

import com.influa.influa.dtos.user.CompanyDTO;
import com.influa.influa.model.user.Company;
import com.influa.influa.model.user.Contractor;
import com.influa.influa.repositories.user.CompanyRepository;
import com.influa.influa.repositories.user.ContractorRepository;
import com.influa.influa.services.user.CompanyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ContractorRepository contractorRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    @DisplayName("Cenário: Cadastro realizado com sucesso")
    void shouldCreateCompanySuccessfully() {
        // Dado que eu sou um usuário e estou autenticado no sistema
        String emailLogado = "user@test.com";
        Contractor contractor = new Contractor();
        contractor.setEmail(emailLogado);

        // E preencher os campos obrigatórios com informações válidas:
        // Nicho: Moda, Localização: Belém - PA, Público-Alvo: Jovens de 18 a 25 anos
        CompanyDTO validCompanyDTO = new CompanyDTO(
                "Minha Loja",
                "12345678000199",
                "Belém - PA", // Localização
                "Loja de roupas",
                "Moda", // Nicho
                "Jovens de 18 a 25 anos" // Público-Alvo
        );

        Company savedCompany = new Company(validCompanyDTO);
        savedCompany.setContractor(contractor);

        when(contractorRepository.findByEmail(emailLogado)).thenReturn(Optional.of(contractor));
        when(companyRepository.save(any(Company.class))).thenReturn(savedCompany);

        // Quando eu solicitar o cadastro do meu negócio e confirmar o cadastro
        Company result = companyService.createCompany(validCompanyDTO, emailLogado);

        // Então o perfil do negócio deve ser salvo e associado à minha conta de usuário
        assertNotNull(result);
        assertEquals("Moda", result.getNiche());
        assertEquals("Belém - PA", result.getCep());
        assertEquals("Jovens de 18 a 25 anos", result.getTargetAudience());
        assertEquals(contractor, result.getContractor());

        // E eu devo visualizar uma mensagem de confirmação de sucesso
        // (A mensagem de sucesso geralmente é verificada no Controller/View, mas garantimos que o save foi invocado)
        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    @DisplayName("Cenário: Tentativa de cadastro com campos obrigatórios ausentes (Fluxo de Exceção)")
    void shouldNotSaveCompanyWhenNicheIsMissing() {
        // Dado que eu sou um usuário e estou autenticado no sistema
        String emailLogado = "user@test.com";

        // E tentar confirmar o cadastro sem preencher o campo "Nicho"
        CompanyDTO invalidCompanyDTO = new CompanyDTO(
                "Minha Loja",
                "12345678000199",
                "Belém - PA",
                "Loja de roupas",
                "", // Nicho ausente/vazio
                "Jovens de 18 a 25 anos"
        );

        // Quando eu solicitar o cadastro do meu negócio
        // Então o sistema não deve salvar o perfil do negócio
        // E deve exibir um alerta informando que o campo "Nicho" é obrigatório
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            companyService.createCompany(invalidCompanyDTO, emailLogado);
        });

        assertEquals("O campo \"Nicho\" é obrigatório", exception.getMessage());
        
        // Verifica que o repositório não foi chamado, logo não salvou
        verify(companyRepository, never()).save(any(Company.class));
    }
}
