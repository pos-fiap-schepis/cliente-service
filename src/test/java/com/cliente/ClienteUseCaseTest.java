package com.cliente;

import com.cliente.core.entities.Cliente;
import com.cliente.core.exceptions.BusinessException;
import com.cliente.core.gateways.ClienteRepositoryGateway;
import com.cliente.core.usecases.ClienteUseCase;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ClienteUseCaseTest {

    @Mock
    private ClienteRepositoryGateway clienteRepositoryGateway;

    @InjectMocks
    private ClienteUseCase clienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getClienteByCpf_clienteExistente_retornaCliente() {
        String cpf = "12345678900";
        Cliente cliente = new Cliente(1L, "Nome", "email@example.com", cpf);
        when(clienteRepositoryGateway.getClienteByCpf(cpf)).thenReturn(Optional.of(cliente));

        Cliente result = clienteUseCase.getClienteByCpf(cpf);

        assertEquals(cliente, result);
    }

    @Test
    void getClienteByCpf_clienteNaoExistente_lancaExcecao() {
        String cpf = "12345678900";
        when(clienteRepositoryGateway.getClienteByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> clienteUseCase.getClienteByCpf(cpf));
    }

    @Test
    void salvar_clienteValido_retornaClienteSalvo() {
        Cliente cliente = new Cliente(1L, "Nome", "email@exemplo.com", "53690270057");
        when(clienteRepositoryGateway.salvar(cliente)).thenReturn(cliente);
        when(clienteRepositoryGateway.getClienteByCpf(cliente.cpf())).thenReturn(Optional.empty());

        Cliente result = clienteUseCase.salvar(cliente);

        assertEquals(cliente, result);
    }

    @Test
    void salvar_clienteComCpfInvalido_lancaExcecao() {
        Cliente cliente = new Cliente(1L, "Nome", "email@exemplo.com", "cpfInvalido");

        assertThrows(BusinessException.class, () -> clienteUseCase.salvar(cliente));
    }

    @Test
    void salvar_clienteJaCadastrado_lancaExcecao() {
        Cliente cliente = new Cliente(1L, "Nome", "email@exemplo.com", "53690270057");
        when(clienteRepositoryGateway.getClienteByCpf(cliente.cpf())).thenReturn(Optional.of(cliente));

        assertThrows(BusinessException.class, () -> clienteUseCase.salvar(cliente));
    }

    @Test
    void preValidar_nomeNulo_lancaExcecao() {
        Cliente cliente = new Cliente(1L, null, "email@exemplo.com", "12345678909");

        assertThrows(BusinessException.class, () -> clienteUseCase.salvar(cliente), "É necessário informar o nome do cliente");
    }

    @Test
    void preValidar_emailNulo_lancaExcecao() {
        Cliente cliente = new Cliente(1L, "Nome", null, "12345678909");

        assertThrows(BusinessException.class, () -> clienteUseCase.salvar(cliente), "É necessário informar o email do cliente");
    }

    @Test
    void preValidar_cpfNulo_lancaExcecao() {
        Cliente cliente = new Cliente(1L, "Nome", "email@exemplo.com", null);

        assertThrows(BusinessException.class, () -> clienteUseCase.salvar(cliente), "É necessário informar o cpf do cliente");
    }

    @Test
    void preValidar_cpfInvalido_lancaExcecao() {
        Cliente cliente = new Cliente(1L, "Nome", "email@exemplo.com", "cpfInvalido");

        assertThrows(BusinessException.class, () -> clienteUseCase.salvar(cliente), "Cpf inválido");
    }

    @Test
    void preValidar_clienteJaCadastrado_lancaExcecao() {
        Cliente cliente = new Cliente(1L, "Nome", "email@exemplo.com", "12345678909");
        when(clienteRepositoryGateway.getClienteByCpf(cliente.cpf())).thenReturn(Optional.of(cliente));

        assertThrows(BusinessException.class, () -> clienteUseCase.salvar(cliente), "Cliente já cadastrado");
    }
}
