package com.cliente.infrastructure.converters;

import com.cliente.core.entities.Cliente;
import com.cliente.infrastructure.controllers.commands.CriarClienteCommand;
import com.cliente.infrastructure.repositories.cliente.ClienteEntity;
import java.time.LocalDateTime;

public class ClienteConverter {

    public static Cliente converterCommandToCliente(CriarClienteCommand command) {
        return new Cliente.Builder()
                .nome(command.getNome())
                .cpf(command.getCpf())
                .email(command.getEmail())
                .build();
    }

    public static ClienteEntity converterClienteToEntity(Cliente cliente) {
        return ClienteEntity.builder()
                .nome(cliente.nome())
                .cpf(cliente.cpf())
                .email(cliente.email())
                .dataInclusao(LocalDateTime.now())
                .build();
    }

    public static Cliente converterEntityToCliente(ClienteEntity cliente) {
        return new Cliente.Builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .build();
    }

}
