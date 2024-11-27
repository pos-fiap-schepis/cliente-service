package com.cliente.infrastructure.repositories.cliente;

import com.cliente.core.entities.Cliente;
import com.cliente.core.gateways.ClienteRepositoryGateway;
import com.cliente.infrastructure.converters.ClienteConverter;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepositoryGatewayImpl implements ClienteRepositoryGateway {

    private final ClienteRepository clienteRepository;

    public ClienteRepositoryGatewayImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity clienteEntity = clienteRepository.save(ClienteConverter.converterClienteToEntity(cliente));

        return ClienteConverter.converterEntityToCliente(clienteEntity);
    }

    @Override
    public Optional<Cliente> getClienteByCpf(String cpf) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findByCpf(cpf);

        return clienteEntity.map(ClienteConverter::converterEntityToCliente);

    }

    @Override
    public Optional<Cliente> getClienteById(Long id) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id);

        return clienteEntity.map(ClienteConverter::converterEntityToCliente);
    }

}
