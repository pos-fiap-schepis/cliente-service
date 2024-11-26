package com.cliente;

import com.cliente.core.entities.Cliente;
import com.cliente.infrastructure.converters.ClienteConverter;
import com.cliente.infrastructure.repositories.cliente.ClienteEntity;
import com.cliente.infrastructure.repositories.cliente.ClienteRepository;
import com.cliente.infrastructure.repositories.cliente.ClienteRepositoryGatewayImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClienteRepositoryGatewayImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteRepositoryGatewayImpl clienteRepositoryGatewayImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvar_cliente_retornaClienteSalvo() {
        Cliente cliente = new Cliente(1L, "Nome", "email@example.com", "12345678909");
        ClienteEntity clienteEntity = ClienteConverter.converterClienteToEntity(cliente);
        clienteEntity.setId(1L);
        when(clienteRepository.save(any())).thenReturn(clienteEntity);

        Cliente result = clienteRepositoryGatewayImpl.salvar(cliente);

        assertEquals(cliente, result);
    }

    @Test
    void getClienteByCpf_clienteExistente_retornaCliente() {
        String cpf = "12345678909";
        Cliente cliente = new Cliente(1L, "Nome", "email@example.com", cpf);
        ClienteEntity clienteEntity = ClienteConverter.converterClienteToEntity(cliente);
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteEntity));

        Optional<Cliente> result = clienteRepositoryGatewayImpl.getClienteByCpf(cpf);

        assertEquals(Optional.of(cliente).get().email(), result.get().email());
    }

    @Test
    void getClienteByCpf_clienteNaoExistente_retornaVazio() {
        String cpf = "12345678909";
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        Optional<Cliente> result = clienteRepositoryGatewayImpl.getClienteByCpf(cpf);

        assertEquals(Optional.empty(), result);
    }
}
