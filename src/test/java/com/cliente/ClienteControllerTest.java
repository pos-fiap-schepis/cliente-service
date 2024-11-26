package com.cliente;

import com.cliente.core.entities.Cliente;
import com.cliente.core.exceptions.BusinessException;
import com.cliente.core.gateways.ClienteServiceGateway;
import com.cliente.infrastructure.controllers.ClienteController;
import com.cliente.infrastructure.controllers.commands.CriarClienteCommand;
import com.cliente.infrastructure.controllers.presenters.ClientePresenter;
import com.cliente.infrastructure.converters.ClienteConverter;
import com.cliente.infrastructure.dtos.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteServiceGateway clienteServiceGateway;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void getClienteByCpf_clienteExistente_retornaCliente() throws Exception {
        String cpf = "12345678900";
        Cliente cliente = new Cliente(1L, "Nome", "email@example.com", cpf);
        ClienteDto clienteDto = ClientePresenter.converterClienteToDto(cliente);
        when(clienteServiceGateway.getClienteByCpf(cpf)).thenReturn(cliente);

        mockMvc.perform(get("/api/clientes/{cpf}", cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clienteDto.id()))
                .andExpect(jsonPath("$.nome").value(clienteDto.nome()))
                .andExpect(jsonPath("$.email").value(clienteDto.email()))
                .andExpect(jsonPath("$.cpf").value(clienteDto.cpf()));
    }

    @Test
    void salvar_clienteValido_retornaClienteSalvo() throws Exception {
        CriarClienteCommand command = new CriarClienteCommand("Nome", "email@example.com", "12345678909");
        Cliente cliente = ClienteConverter.converterCommandToCliente(command);
        ClienteDto clienteDto = ClientePresenter.converterClienteToDto(cliente);
        when(clienteServiceGateway.salvar(cliente)).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Nome\",\"email\":\"email@example.com\",\"cpf\":\"12345678909\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clienteDto.id()))
                .andExpect(jsonPath("$.nome").value(clienteDto.nome()))
                .andExpect(jsonPath("$.email").value(clienteDto.email()))
                .andExpect(jsonPath("$.cpf").value(clienteDto.cpf()));
    }
}
