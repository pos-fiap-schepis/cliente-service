package com.cliente.core.gateways;


import com.cliente.core.entities.Cliente;

public interface ClienteServiceGateway {

    Cliente getClienteByCpf(String cpf);

    Cliente salvar(Cliente cliente);
}
