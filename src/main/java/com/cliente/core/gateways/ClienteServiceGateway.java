package com.cliente.core.gateways;


import com.cliente.core.entities.Cliente;

public interface ClienteServiceGateway {

    Cliente getClienteByCpf(String cpf);

    Cliente getClienteById(Long id);

    Cliente salvar(Cliente cliente);
}
