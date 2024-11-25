package com.cliente.infrastructure;


import com.cliente.core.gateways.ClienteRepositoryGateway;
import com.cliente.core.gateways.ClienteServiceGateway;
import com.cliente.core.usecases.ClienteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    public ClienteServiceGateway clienteServiceImpl(ClienteRepositoryGateway todoARepositoryPort) {
        return new ClienteUseCase(todoARepositoryPort);
    }

}
