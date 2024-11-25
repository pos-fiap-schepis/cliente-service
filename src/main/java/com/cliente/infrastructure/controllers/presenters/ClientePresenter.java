package com.cliente.infrastructure.controllers.presenters;

import com.cliente.core.entities.Cliente;
import com.cliente.infrastructure.dtos.ClienteDto;

public class ClientePresenter {


    public static ClienteDto converterClienteToDto(Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.id())
                .nome(cliente.nome())
                .cpf(cliente.cpf())
                .email(cliente.email())
                .build();
    }

}
