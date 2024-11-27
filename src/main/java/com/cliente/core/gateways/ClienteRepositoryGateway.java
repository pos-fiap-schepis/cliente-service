package com.cliente.core.gateways;

import com.cliente.core.entities.Cliente;
import java.util.Optional;

public interface ClienteRepositoryGateway {

    Cliente salvar(Cliente cliente);

    Optional<Cliente> getClienteByCpf(String cpf);

    Optional<Cliente> getClienteById(Long id);
}
