package com.cliente.infrastructure.dtos;

import lombok.Builder;

@Builder
public record ClienteDto(Long id,
                         String nome,
                         String email,
                         String cpf) {
}
